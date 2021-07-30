/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 14:44:40
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.IFormComponentRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

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

    boolean setValue(FormConfigurationField field, C component, T value);

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
