package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface FocusableFactory<T extends Component & Focusable<T>, E extends FocusableFactory<T, E>> extends
        IFlowFactory<T>,
        BlurNotifierFactory<T, E>,
        FocusNotifierFactory<T, E>,
        HasEnabledFactory<T, E> {

    default E focusShortcut(Key key, KeyModifier... keyModifiers) {
        get().addFocusShortcut(key, keyModifiers);
        return (E) this;
    }

    default E tabIndex(int tabIndex) {
        get().setTabIndex(tabIndex);
        return (E) this;
    }

    default E blur() {
        get().blur();
        return (E) this;
    }

    default E focus() {
        get().focus();
        return (E) this;
    }

}
