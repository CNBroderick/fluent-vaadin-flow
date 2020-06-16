package org.bklab.flow.factory;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ListBoxBaseFactory;
import org.bklab.flow.base.SingleSelectFactory;

public class ListBoxFactory<T> extends FlowFactory<ListBox<T>, ListBoxFactory<T>> implements
        ListBoxBaseFactory<T, ListBox<T>, ListBoxFactory<T>>,
        SingleSelectFactory<T, ListBox<T>, ListBoxFactory<T>> {
    public ListBoxFactory() {
        this(new ListBox<>());
    }

    public ListBoxFactory(ListBox<T> component) {
        super(component);
    }

    public ListBoxFactory<T> detachListener(ComponentEventListener<DetachEvent> detachListener) {
        get().addDetachListener(detachListener);
        return this;
    }

    public ListBoxFactory<T> attachListener(ComponentEventListener<AttachEvent> attachListener) {
        get().addAttachListener(attachListener);
        return this;
    }

    @Override
    public ListBoxFactory<T> value(T value) {
        get().setValue(value);
        return this;
    }

    @Override
    public ListBoxFactory<T> valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<ListBox<T>, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return this;
    }

    @Override
    public FlowFactory<ListBox<T>, ListBoxFactory<T>> addAttachListener(ComponentEventListener<AttachEvent> listener) {
        get().addAttachListener(listener);
        return this;
    }

    @Override
    public FlowFactory<ListBox<T>, ListBoxFactory<T>> addDetachListener(ComponentEventListener<DetachEvent> listener) {
        get().addDetachListener(listener);
        return this;
    }

    @Override
    public ListBoxFactory<T> renderer(ComponentRenderer<? extends Component, T> itemRenderer) {
        get().setRenderer(itemRenderer);
        return this;
    }

    @Override
    public ListBoxFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    @Override
    public ListBoxFactory<T> itemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        get().setItemEnabledProvider(itemEnabledProvider);
        return this;
    }

    @Override
    public ListBoxFactory<T> requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }
}
