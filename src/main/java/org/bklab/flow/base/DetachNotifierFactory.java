package org.bklab.flow.base;

import com.vaadin.flow.component.AttachNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface DetachNotifierFactory<T extends Component & AttachNotifier, E extends DetachNotifierFactory<T, E>> extends IFlowFactory<T> {
    default E addDetachListener(ComponentEventListener<DetachEvent> listener) {
        get().addDetachListener(listener);
        return (E) this;
    }
}
