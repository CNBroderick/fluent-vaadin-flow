package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasValueFactory<T, A extends HasValue.ValueChangeEvent<T>, C extends Component & HasValue<A, T>, E extends HasValueFactory<T, A, C, E>> extends IFlowFactory<C> {
    default E clear() {
        get().clear();
        return (E) this;
    }

    default E readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return (E) this;
    }

    default E value(T value) {
        get().setValue(value);
        return (E) this;
    }

    default E valueChangeListener(HasValue.ValueChangeListener<? super A> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }
}
