package org.bklab.flow.components.selector.button.entity;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.html.Span;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.IFluentFlowFactory;
import org.bklab.flow.base.HasListDataViewFactory;
import org.bklab.flow.base.HasValueFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class EntityButtonSelectorFactory<T> extends FlowFactory<EntityButtonSelector<T>, EntityButtonSelectorFactory<T>> implements
        HtmlContainerFactory<EntityButtonSelector<T>, EntityButtonSelectorFactory<T>>,
        IFluentFlowFactory<EntityButtonSelector<T>, EntityButtonSelectorFactory<T>>,
        HasValueFactory<T, EntityButtonSelectorValueChangeEvent<EntityButtonSelector<T>, T>, EntityButtonSelector<T>, EntityButtonSelectorFactory<T>>,
        HasListDataViewFactory<T, EntityButtonSelectorListDataView<T>, EntityButtonSelector<T>, EntityButtonSelectorFactory<T>> {

    public EntityButtonSelectorFactory() {
        this(new EntityButtonSelector<>());
    }

    public EntityButtonSelectorFactory(EntityButtonSelector<T> component) {
        super(component);
    }

    public EntityButtonSelectorFactory<T> clickListener(ComponentEventListener<ClickEvent<Span>> listener) {
        get().addClickListener(listener);
        return this;
    }

    public EntityButtonSelectorFactory<T> clickShortcut(Key key, KeyModifier... keyModifiers) {
        get().addClickShortcut(key, keyModifiers);
        return this;
    }

    public EntityButtonSelectorFactory<T> active(T entity) {
        get().active(entity);
        return this;
    }

}
