package org.bklab.flow.base;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.selection.SingleSelect;
import org.bklab.flow.IFlowFactory;

public interface SingleSelectFactory<T, C extends Component & SingleSelect<C, T>, E extends SingleSelectFactory<T, C, E>> extends IFlowFactory<C>
        , HasValueAndElementFactory<T, AbstractField.ComponentValueChangeEvent<C, T>, C, E> {
}
