package org.bklab.flow.base;

import com.vaadin.flow.component.textfield.GeneratedVaadinNumberField;
import org.bklab.flow.IFlowFactory;

public interface GeneratedVaadinNumberFieldFactory<T extends Number, C extends GeneratedVaadinNumberField<C, T>, E extends GeneratedVaadinTextFieldFactory<T, C, E>> extends IFlowFactory<C>,
        GeneratedVaadinTextFieldFactory<T, C, E>, HasStyleFactory<C, E> {
}
