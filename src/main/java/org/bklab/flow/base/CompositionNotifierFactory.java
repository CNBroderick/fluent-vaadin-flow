package org.bklab.flow.base;

import com.vaadin.flow.component.*;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface CompositionNotifierFactory<T extends Component & CompositionNotifier, E extends CompositionNotifierFactory<T, E>> extends IFlowFactory<T> {
    default E compositionEndListener(ComponentEventListener<CompositionEndEvent> compositionEndListener) {
        get().addCompositionEndListener(compositionEndListener);
        return (E) this;
    }

    default E compositionUpdateListener(ComponentEventListener<CompositionUpdateEvent> compositionUpdateListener) {
        get().addCompositionUpdateListener(compositionUpdateListener);
        return (E) this;
    }

    default E compositionStartListener(ComponentEventListener<CompositionStartEvent> compositionStartListener) {
        get().addCompositionStartListener(compositionStartListener);
        return (E) this;
    }
}
