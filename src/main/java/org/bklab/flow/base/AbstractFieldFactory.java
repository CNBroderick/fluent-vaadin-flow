package org.bklab.flow.base;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface AbstractFieldFactory<T, C extends AbstractField<C, T>, E extends AbstractFieldFactory<T, C, E>> extends IFlowFactory<C> {
    default E value(T value) {
        get().setValue(value);
        return (E) this;
    }

    default E valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<C, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return (E) this;
    }
}
