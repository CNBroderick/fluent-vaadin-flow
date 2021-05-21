/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-21 13:12:38
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.AbstractFieldFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface AbstractFieldFactory<T, C extends AbstractField<C, T>, E extends AbstractFieldFactory<T, C, E>>
        extends IFlowFactory<C>, HasValueAndElementFactory<T, AbstractField.ComponentValueChangeEvent<C, T>, C, E> {
    default E value(T value) {
        if (value == null) get().clear();
        else get().setValue(value);
        return (E) this;
    }

    default E valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<C, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return (E) this;
    }
}
