/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 15:54:00
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.FormCheckBoxGroupRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.alibaba.fastjson.JSONArray;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import org.bklab.flow.factory.CheckboxGroupFactory;
import org.bklab.flow.form.config.FormConfigurationConfig;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.config.FormConfigurationRegList;
import org.bklab.flow.form.config.FormConfigurationSlot.FormComponentSlotOption;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FormCheckBoxGroupRender implements IFormComponentRender<JSONArray, CheckboxGroup<FormComponentSlotOption>> {

    @Override
    public CheckboxGroup<FormComponentSlotOption> build(FormConfigurationField field) {
        CheckboxGroupFactory<FormComponentSlotOption> checkboxGroup = new CheckboxGroupFactory<FormComponentSlotOption>()
                .lumoSmall().widthFull()
                .readOnly(field.isReadonly())
                .enabled(!field.isDisabled());

        Optional.ofNullable(field.getSlot()).flatMap(slot -> Optional.ofNullable(slot.getOptions())).ifPresent(checkboxGroup::items);

        Optional.ofNullable(field.getConfig()).ifPresent(config -> {
            checkboxGroup.required(config.isRequired());
            List<FormConfigurationRegList> regList = config.getRegList();

            Optional.ofNullable(config.getDefaultValue())
                    .filter(com.alibaba.fastjson.JSONArray.class::isInstance)
                    .map(com.alibaba.fastjson.JSONArray.class::cast)
                    .ifPresent(jsonArray -> setValue(field, checkboxGroup.get(), jsonArray));

            Optional.ofNullable(config.getRenderKey()).ifPresent(checkboxGroup::id);
        });

        Optional.ofNullable(field.getStyle()).ifPresent(style -> style.forEach(checkboxGroup::style));

        checkboxGroup.valueChangeListener(valueChangeEvent -> validate(field, valueChangeEvent));

        return checkboxGroup.get();
    }

    @Override
    public boolean validate(FormConfigurationField field, FormConfigurationConfig config, CheckboxGroup<FormComponentSlotOption> source) {
        Set<FormComponentSlotOption> value = source.getValue();
        int size = value.size();
        if (source.isRequired() && size < 1) {
            source.setErrorMessage("请选择[" + config.getLabel() + "]。");
            return false;
        }

        if (field.getMin() > 0 && size < field.getMin()) {
            source.setErrorMessage("最低选择[%d]个，当前选择[%d]个。".formatted(field.getMin(), size));
            return false;
        }

        if (field.getMax() > 0 && size > field.getMax()) {
            source.setErrorMessage("最多选择[%d]个，当前选择[%d]个。".formatted(field.getMax(), size));
            return false;
        }

        return true;
    }

    private boolean validate(FormConfigurationField field, AbstractField.ComponentValueChangeEvent<CheckboxGroup
            <FormComponentSlotOption>, Set<FormComponentSlotOption>> valueChangeEvent) {
        return validate(field, field.getConfig(), valueChangeEvent.getSource());
    }

    @Override
    public JSONArray getValue(CheckboxGroup<FormComponentSlotOption> component) {
        return component.getValue() == null ? null : component
                .getValue().stream().map(FormComponentSlotOption::getValue)
                .collect(JSONArray::new, JSONArray::add, JSONArray::addAll);
    }

    @Override
    public boolean setValue(FormConfigurationField field, CheckboxGroup<FormComponentSlotOption> component, JSONArray value) {
        Optional.ofNullable(value)
                .flatMap(jsonArray -> Optional.ofNullable(field.getSlot())
                        .flatMap(f -> Optional.ofNullable(f.getOptions()))
                        .map(options -> options.stream().filter(o -> jsonArray.contains(o.getValue())).collect(Collectors.toSet())))
                .ifPresent(component::setValue);
        return false;
    }
}
