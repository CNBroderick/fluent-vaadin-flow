package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasEnabledFactory<T extends Component & HasEnabled, E extends HasEnabledFactory<T, E>> extends IFlowFactory<T> {
    default E enabled(boolean enabled) {
        get().setEnabled(enabled);
        return (E) this;
    }

    default E enabled() {
        get().setEnabled(true);
        return (E) this;
    }

    default E disabled() {
        get().setEnabled(false);
        return (E) this;
    }
}
