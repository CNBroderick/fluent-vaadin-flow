package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.InputEvent;
import com.vaadin.flow.component.InputNotifier;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface InputNotifierFactory<T extends Component & InputNotifier, E extends InputNotifierFactory<T, E>> extends IFlowFactory<T> {
    default E inputListener(ComponentEventListener<InputEvent> listener) {
        get().addInputListener(listener);
        return (E) this;
    }
}
