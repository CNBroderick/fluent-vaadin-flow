package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasItemComponents;
import org.bklab.flow.IFlowFactory;

public interface HasItemComponentsFactory<T, C extends Component & HasItemComponents<T>, E extends HasItemComponentsFactory<T, C, E>> extends IFlowFactory<C>, HasReturnThis<E> {

    default E components(T afterItem, Component... components) {
        get().addComponents(afterItem, components);
        return thisObject();
    }

    default E prependComponents(T beforeItem, Component... components) {
        get().prependComponents(beforeItem, components);
        return thisObject();
    }


}
