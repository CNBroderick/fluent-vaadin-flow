package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.data.event.SortEvent;
import com.vaadin.flow.data.provider.SortOrder;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface SortEventSortNotifierFactory<C extends Component & SortEvent.SortNotifier<C, S>,
        S extends SortOrder<?>, E extends SortEventSortNotifierFactory<C, S, E>> extends IFlowFactory<C> {

    default E sortListener(ComponentEventListener<SortEvent<C, S>> sortListener) {
        get().addSortListener(sortListener);
        return (E) this;
    }
}
