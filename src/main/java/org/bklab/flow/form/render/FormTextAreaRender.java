/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.form.render.FormTextAreaRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.extern.slf4j.Slf4j;
import org.bklab.flow.factory.TextAreaFactory;
import org.bklab.flow.form.config.FormConfigurationConfig;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.config.FormConfigurationRegList;

import java.util.List;
import java.util.Optional;

@Slf4j
public class FormTextAreaRender implements IFormComponentRender<String, TextArea> {

    @Override
    public TextArea build(FormConfigurationField field) {
        TextAreaFactory textArea = new TextAreaFactory().widthFull().lumoSmall()
                .clearButtonVisible(field.isClearable())
                .readOnly(field.isReadonly())
                .enabled(!field.isDisabled())
                .placeholder(field.getPlaceholder());

        if (field.getMaxLength() > 0) {
            textArea.maxLength(field.getMaxLength());
        }

        if (field.getMinLength() > 0) {
            textArea.minLength(field.getMinLength());
        }

        Optional.ofNullable(field.getConfig()).ifPresent(config -> {
            textArea.required(config.isRequired());
            List<FormConfigurationRegList> regList = config.getRegList();
            if (regList != null && !regList.isEmpty()) {
                textArea.valueChangeMode(ValueChangeMode.EAGER);
                textArea.valueChangeListener(valueChangeEvent -> validate(field, config, valueChangeEvent.getSource()));
            }
            Optional.ofNullable(config.getRenderKey()).ifPresent(textArea::id);
        });

        Optional.ofNullable(field.getStyle()).ifPresent(style -> style.forEach(textArea::style));

        Optional.ofNullable(field.getAutosize()).ifPresent(autosize -> {
            if (autosize.getMinRows() > 0) textArea.minHeight(autosize.getMinRows() + 1, Unit.REM);
            if (autosize.getMaxRows() > 0) textArea.maxHeight(autosize.getMaxRows() + 1, Unit.REM);
        });

        return textArea.get();
    }

    @Override
    public boolean validate(FormConfigurationField field, FormConfigurationConfig config, TextArea source) {
        return validateText(field, config, source);
    }

    @Override
    public String getValue(TextArea component) {
        return component.getValue();
    }

    @Override
    public boolean setValue(FormConfigurationField field, TextArea component, String value) {
        component.setValue(value);
        return true;
    }
}
