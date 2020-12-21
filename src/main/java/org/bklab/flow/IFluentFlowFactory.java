package org.bklab.flow;

import com.vaadin.flow.component.Component;
import org.bklab.flow.base.HasReturnThis;

public interface IFluentFlowFactory<C extends Component, E extends IFluentFlowFactory<C,E>> extends HasReturnThis<E>, IFlowFactory<C> {
}
