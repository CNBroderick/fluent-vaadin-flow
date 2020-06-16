package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValidation;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasValidationFactory<T extends Component & HasValidation, E extends HasValidationFactory<T, E>> extends IFlowFactory<T> {
    default E errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return (E) this;
    }

    default E invalid(boolean invalid) {
        get().setInvalid(invalid);
        return (E) this;
    }
}
