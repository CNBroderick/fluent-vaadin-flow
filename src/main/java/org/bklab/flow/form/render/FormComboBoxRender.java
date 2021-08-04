/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.form.render.FormComboBoxRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.vaadin.flow.component.combobox.ComboBox;
import org.bklab.flow.factory.ComboBoxFactory;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.config.FormConfigurationSlot.FormComponentSlotOption;

import java.util.Objects;
import java.util.Optional;

public class FormComboBoxRender implements IFormComponentRender<String, ComboBox<FormComponentSlotOption>> {

    @Override
    public ComboBox<FormComponentSlotOption> build(FormConfigurationField field) {
        ComboBoxFactory<FormComponentSlotOption> comboBox = new ComboBoxFactory<FormComponentSlotOption>()
                .lumoSmall().widthFull().itemLabelGenerator(FormComponentSlotOption::getLabel)
                .clearButtonVisible(field.isClearable())
                .readOnly(field.isReadonly())
                .enabled(!field.isDisabled())
                .placeholder(field.getPlaceholder());

        Optional.ofNullable(field.getSlot()).ifPresent(slot -> comboBox.items(slot.getOptions()));

        Optional.ofNullable(field.getConfig()).ifPresent(config -> {
            comboBox.required(config.isRequired());

            Optional.ofNullable(config.getRenderKey()).ifPresent(comboBox::id);

            Optional.ofNullable(config.getDefaultValue()).flatMap(value -> Optional.ofNullable(field.getSlot())
                    .flatMap(slot -> Optional.ofNullable(slot.getOptions())
                            .flatMap(options -> options.stream()
                                    .filter(s -> Objects.equals(value, s.getValue()))
                                    .findFirst())
                    )
            ).ifPresent(comboBox::value);

        });

        return comboBox.get();
    }

    @Override
    public String getValue(ComboBox<FormComponentSlotOption> component) {
        return component.getOptionalValue().map(FormComponentSlotOption::getValue).orElse(null);
    }

    @Override
    public boolean setValue(FormConfigurationField field, ComboBox<FormComponentSlotOption> component, String value) {
        for (FormComponentSlotOption option : field.getSlot().getOptions()) {
            if (Objects.equals(option.getValue(), value)) {
                component.setValue(option);
                return true;
            }
        }
        component.clear();
        return false;
    }
}
