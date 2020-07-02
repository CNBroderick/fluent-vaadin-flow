/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.AbstractFieldFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface AbstractFieldFactory<T, C extends AbstractField<C, T>, E extends AbstractFieldFactory<T, C, E>>
        extends IFlowFactory<C>, HasValueAndElementFactory<T, AbstractField.ComponentValueChangeEvent<C, T>, C, E> {
    default E value(T value) {
        get().setValue(value);
        return (E) this;
    }

    default E valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<C, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return (E) this;
    }
}
