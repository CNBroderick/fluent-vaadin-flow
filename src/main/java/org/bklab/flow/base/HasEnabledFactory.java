package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import org.bklab.flow.IFlowFactory;

public interface HasEnabledFactory<T extends Component & HasEnabled, E extends HasEnabledFactory<T, E>> extends IFlowFactory<T> {
    default void enabled(boolean enabled) {
        get().setEnabled(enabled);
    }

    default void enabled() {
        get().setEnabled(true);
    }
}
