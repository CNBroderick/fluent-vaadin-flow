package org.bklab.flow.base;

import com.vaadin.flow.component.*;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ClickNotifierFactory<T extends Component & ClickNotifier<T>, E extends ClickNotifierFactory<T, E>> extends IFlowFactory<T> {

    default E addClickListener(ComponentEventListener<ClickEvent<T>> listener) {
        get().addClickListener(listener);
        return (E) this;
    }

    default E addClickShortcut(Key key, KeyModifier... keyModifiers) {
        get().addClickShortcut(key, keyModifiers);
        return (E) this;
    }
}
