package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasItemComponents;
import org.bklab.flow.IFluentFlowFactory;

public interface HasItemComponentsFactory<T, C extends Component & HasItemComponents<T>, E extends HasItemComponentsFactory<T, C, E>>
        extends HasComponentsFactory<C, E>, IFluentFlowFactory<C, E> {


    default E addComponents(T afterItem, Component... components) {
        get().addComponents(afterItem, components);
        return thisObject();
    }

    default E prependComponents(T beforeItem, Component... components) {
        get().prependComponents(beforeItem, components);
        return thisObject();
    }

}
