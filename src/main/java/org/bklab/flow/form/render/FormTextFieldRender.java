/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 14:08:16
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.FormTextFieldRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.extern.slf4j.Slf4j;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.factory.TextFieldFactory;
import org.bklab.flow.form.config.FormConfigurationConfig;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.config.FormConfigurationRegList;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public class FormTextFieldRender implements IFormComponentRender<String, TextField> {

    @Override
    public TextField build(FormConfigurationField field) {
        TextFieldFactory textField = new TextFieldFactory().widthFull().lumoSmall()
                .clearButtonVisible(field.isClearable())
                .readOnly(field.isReadonly())
                .enabled(!field.isDisabled())
                .placeholder(field.getPlaceholder());

        if (field.getMaxLength() > 0) {
            textField.maxLength(field.getMaxLength());
        }

        Optional.ofNullable(field.getConfig()).ifPresent(config -> {
            textField.required(config.isRequired());
            List<FormConfigurationRegList> regList = config.getRegList();
            if (regList != null && !regList.isEmpty()) {
                textField.valueChangeMode(ValueChangeMode.EAGER);
                textField.valueChangeListener(valueChangeEvent -> checkValueRegex(field, config, valueChangeEvent));
            }
            Optional.ofNullable(config.getRenderKey()).ifPresent(textField::id);
        });


        Optional.ofNullable(field.getSlot()).ifPresent(slot -> {
            prependComponent(textField.get(), new Span(slot.getPrepend()));
            appendComponent(textField.get(), new Span(slot.getAppend()));
        });

        Optional.ofNullable(field.getStyle()).ifPresent(style -> style.forEach(textField::style));

        Optional.ofNullable(field.getPrefixIcon()).ifPresent(name -> {
            for (VaadinIcon icon : VaadinIcon.values()) {
                if (name.toUpperCase(Locale.ROOT).contains(icon.name())) {
                    prependComponent(textField.get(), icon.create());
                }
                return;
            }
        });

        Optional.ofNullable(field.getSuffixIcon()).ifPresent(name -> {
            for (VaadinIcon icon : VaadinIcon.values()) {
                if (name.toUpperCase(Locale.ROOT).contains(icon.name())) {
                    appendComponent(textField.get(), icon.create());
                }
                return;
            }
        });

        return textField.get();
    }

    private void prependComponent(TextField textField, Component prepend) {
        if (prepend != null) {
            appendComponent(textField.getPrefixComponent(), prepend, textField::setPrefixComponent);
        }
    }

    private void appendComponent(TextField textField, Component append) {
        if (append != null) {
            appendComponent(textField.getSuffixComponent(), append, textField::setSuffixComponent);
        }
    }

    private void appendComponent(Component container, Component append, Consumer<Div> setter) {
        if (append != null) {
            if (container == null) {
                container = new DivFactory().displayFlex().peek(setter).get();
            }
            if (!(container instanceof Div)) {
                Div div = new DivFactory().displayFlex().peek(setter).get();
                div.add(container);
            }
            container.getElement().appendChild(new Span(append).getElement());
        }
    }

    private void checkValueRegex(FormConfigurationField field, FormConfigurationConfig config,
                                 AbstractField.ComponentValueChangeEvent<TextField, String> valueChangeEvent) {

        TextField source = valueChangeEvent.getSource();
        String value = valueChangeEvent.getValue();
        if (value == null || value.isBlank()) {
            source.setErrorMessage(config.isRequired() ? "请填写“" + config.getLabel() + "”" : null);
            return;
        }

        for (FormConfigurationRegList list : config.getRegList()) {
            try {
                String pattern = list.getPattern();
                if (pattern != null && !pattern.isBlank()) {
                    if (value.matches(pattern)) continue;
                    source.setErrorMessage(list.getMessage());
                    return;
                }
            } catch (Exception e) {
                log.error("check value regex [%s] for form [%s] field [%s] throw an error."
                        .formatted(list.getPattern(), field.getName(), config.getLabel()), e);
            }
        }
        source.setErrorMessage(null);
    }

    @Override
    public String getValue(TextField component) {
        return component.getValue();
    }

    @Override
    public boolean setValue(FormConfigurationField field, TextField component, String value) {
        component.setValue(value);
        return true;
    }

}
