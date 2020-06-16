package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValueAndElement;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasValueAndElementFactory<T, A extends HasValue.ValueChangeEvent<T>, C extends Component & HasValueAndElement<A, T>,
        E extends HasValueAndElementFactory<T, A, C, E>> extends IFlowFactory<C>,
        HasValueFactory<T, A, C, E>,
        HasEnabledFactory<C, E> {

    default E readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }

}
