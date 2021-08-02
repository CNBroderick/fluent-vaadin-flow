/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.form.render.IFormComponentRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.alibaba.fastjson.JSON;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.HasValue;
import org.bklab.flow.form.config.FormConfigurationConfig;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.config.FormConfigurationRegList;
import org.slf4j.LoggerFactory;

@SuppressWarnings("UnusedReturnValue")
public interface IFormComponentRender<T, C extends Component> {

    C build(FormConfigurationField field);

    T getValue(C component);

    default T getObjectValue(Object component) {
        if (component == null) return null;
        try {
            //noinspection unchecked
            return getValue((C) component);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("getting [%s] value throw an error.".formatted(component.getClass().getName()), e);
            return null;
        }
    }

    boolean setValue(FormConfigurationField field, C component, T value);

    default boolean setObjectValue(FormConfigurationField field, Object component, Object value) {
        if (value == null) {
            if (component instanceof HasValue) {
                ((HasValue<?, ?>) component).clear();
                return true;
            }
            return false;
        }
        try {
            //noinspection unchecked
            return setValue(field, (C) component, (T) value);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("setting field [%s] value throw an error, value type is [%s] and its value is:\n%s\n"
                    .formatted(field.getModel(), value.getClass().getName(), JSON.toJSONString(value, true)), e);
        }
        return false;
    }

    default boolean validate(FormConfigurationField field, Object source) {
        //noinspection unchecked
        return validate(field, field.getConfig(), (C) source);
    }

    default boolean validate(FormConfigurationField field, FormConfigurationConfig config, C source) {
        return true;
    }

    default boolean validateStringValue(FormConfigurationField field, FormConfigurationConfig config, String value) {
        return true;
    }

    default <FIELD extends Component & HasValidation & HasValue<?, String>>
    boolean validateText(FormConfigurationField field, FormConfigurationConfig config, FIELD source) {
        String value = source.getValue();
        if (value == null || value.isBlank()) {
            source.setErrorMessage(config.isRequired() ? "请填写“" + config.getLabel() + "”" : null);
            return false;
        }

        if (field.getMaxLength() > 0 && value.length() > field.getMaxLength()) {
            source.setErrorMessage(config.isRequired() ? "超过最大长度[ " + field.getMaxLength() + " ]个字节，当前为[ " + value.length() + " ]个字节。" : null);
            return false;
        }

        if (field.getMinLength() > 0 && value.length() < field.getMinLength()) {
            source.setErrorMessage(config.isRequired() ? "低于最小长度[ " + field.getMaxLength() + " ]个字节，当前为[ " + value.length() + " ]个字节。" : null);
            return false;
        }

        for (FormConfigurationRegList list : config.getRegList()) {
            try {
                String pattern = list.getPattern();
                if (pattern != null && !pattern.isBlank()) {
                    if (value.matches(pattern)) continue;
                    source.setErrorMessage(list.getMessage());
                    return false;
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error("check value regex [%s] for form [%s] field [%s] throw an error."
                        .formatted(list.getPattern(), field.getName(), config.getLabel()), e);
            }
        }

        source.setErrorMessage(null);
        return true;
    }
}
