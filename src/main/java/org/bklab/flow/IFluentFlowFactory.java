/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 15:05:18
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.IFluentFlowFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow;

import com.vaadin.flow.component.Component;
import org.bklab.flow.base.AttachNotifierFactory;
import org.bklab.flow.base.DetachNotifierFactory;
import org.bklab.flow.base.HasElementFactory;
import org.bklab.flow.base.HasReturnThis;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IFluentFlowFactory<C extends Component, E extends IFluentFlowFactory<C, E>> extends
        HasElementFactory<C, E>, AttachNotifierFactory<C, IFluentFlowFactory<C, E>>,
        DetachNotifierFactory<C, IFluentFlowFactory<C, E>>, HasReturnThis<E>, IFlowFactory<C> {

    default E enabledStateChanged(boolean onEnabledStateChanged) {
        get().onEnabledStateChanged(onEnabledStateChanged);
        return thisObject();
    }

    default E id(String id) {
        get().setId(id);
        return thisObject();
    }

    default E visible(boolean visible) {
        get().setVisible(visible);
        return thisObject();
    }

    default E attributeTitle(String title) {
        get().getElement().setAttribute("title", title);
        return thisObject();
    }

    default E peek(Consumer<C> consumer) {
        if (consumer != null) consumer.accept(get());
        return thisObject();
    }

    default E peek(boolean canPeek, Consumer<C> consumer) {
        return canPeek ? thisObject() : peek(consumer);
    }

    default E peekThis(boolean canPeek, Consumer<E> consumer) {
        return canPeek ? thisObject() : peekThis(consumer);
    }

    default E peekThisWhenItNotNull(Object checkObject, Consumer<E> consumer) {
        return checkObject == null ? thisObject() : peekThis(consumer);
    }

    default E peekThis(Consumer<E> consumer) {
        if (consumer != null) consumer.accept(thisObject());
        return thisObject();
    }

    default E peek(Predicate<C> canPeek, Consumer<C> consumer) {
        return canPeek.test(get()) ? thisObject() : peek(consumer);
    }

}
