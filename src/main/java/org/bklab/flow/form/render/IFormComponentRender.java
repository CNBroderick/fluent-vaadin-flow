/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 14:13:43
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.IFormComponentRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import com.vaadin.flow.component.Component;
import org.bklab.flow.form.config.FormConfigurationField;

public interface IFormComponentRender<T, C extends Component> {

    C build(FormConfigurationField field);

    T getValue(C component);

    boolean setValue(FormConfigurationField field, C component, T value);

    default boolean validate(T value) {
        return true;
    }

    default boolean validateStringValue(String value) {
        return true;
    }
}
