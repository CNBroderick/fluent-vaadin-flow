package org.bklab.flow.base;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.AttachNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface AttachNotifierFactory<T extends Component & AttachNotifier, E extends AttachNotifierFactory<T, E>> extends IFlowFactory<T> {
    default E addAttachListener(ComponentEventListener<AttachEvent> listener) {
        get().addAttachListener(listener);
        return (E) this;
    }
}
