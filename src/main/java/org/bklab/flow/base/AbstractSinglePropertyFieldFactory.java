package org.bklab.flow.base;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import org.bklab.flow.IFlowFactory;

public interface AbstractSinglePropertyFieldFactory<T, C extends AbstractSinglePropertyField<C, T>, E extends AbstractSinglePropertyFieldFactory<T, C, E>> extends IFlowFactory<C>, AbstractFieldFactory<T,C,E> {
}
