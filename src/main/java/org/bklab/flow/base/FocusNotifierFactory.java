package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.FocusNotifier;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface FocusNotifierFactory<T extends Component & FocusNotifier<T>, E extends FocusNotifierFactory<T, E>> extends IFlowFactory<T> {

    default E focusListener(ComponentEventListener<FocusNotifier.FocusEvent<T>> listener) {
        get().addFocusListener(listener);
        return (E) this;
    }
}
