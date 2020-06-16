package org.bklab.flow.base;

import com.vaadin.flow.component.select.generated.GeneratedVaadinSelect;
import org.bklab.flow.IFlowFactory;

public interface GeneratedVaadinSelectFactory<T, C extends GeneratedVaadinSelect<C, T>, E extends GeneratedVaadinSelectFactory<T, C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<T, C, E>,
        HasStyleFactory<C, E>,
        FocusableFactory<C, E> {

}
