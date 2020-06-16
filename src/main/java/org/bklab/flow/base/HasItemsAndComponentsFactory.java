package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasItemsAndComponents;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasItemsAndComponentsFactory<T, C extends Component & HasItemsAndComponents<T>, E extends HasItemsAndComponentsFactory<T, C, E>> extends IFlowFactory<C>,
        HasComponentsFactory<C, E>,
        HasItemsFactory<T, C, E> {

    default E components(T afterItem, Component... components) {
        get().addComponents(afterItem, components);
        return (E) this;
    }

    default E prependComponents(T beforeItem, Component... components) {
        get().prependComponents(beforeItem, components);
        return (E) this;
    }
}
