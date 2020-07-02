/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:28:45
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.HasValueAndElementFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValueAndElement;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasValueAndElementFactory<T, A extends HasValue.ValueChangeEvent<T>, C extends Component & HasValueAndElement<A, T>,
        E extends HasValueAndElementFactory<T, A, C, E>> extends IFlowFactory<C>,
        HasValueFactory<T, A, C, E>,
        HasEnabledFactory<C, E> {

    default E readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }

    default E readOnly() {
        get().setReadOnly(true);
        return (E) this;
    }

    default E requiredIndicatorVisible() {
        get().setRequiredIndicatorVisible(true);
        return (E) this;
    }

}
