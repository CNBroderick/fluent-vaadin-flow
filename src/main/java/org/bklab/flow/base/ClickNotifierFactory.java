/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-17 20:42:39
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.ClickNotifierFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.*;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ClickNotifierFactory<T extends Component & ClickNotifier<T>, E extends ClickNotifierFactory<T, E>> extends IFlowFactory<T> {

    default E clickListener(ComponentEventListener<ClickEvent<T>> listener) {
        get().addClickListener(listener);
        return (E) this;
    }

    default E clickShortcut(Key key, KeyModifier... keyModifiers) {
        get().addClickShortcut(key, keyModifiers);
        return (E) this;
    }
}
