package org.bklab.flow.base;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface BlurNotifierFactory<T extends Component & BlurNotifier<T>, E extends BlurNotifierFactory<T, E>> extends IFlowFactory<T> {

    default E blurListener(ComponentEventListener<BlurNotifier.BlurEvent<T>> listener) {
        get().addBlurListener(listener);
        return (E) this;
    }
}
