package org.bklab.flow;

import com.vaadin.flow.component.Component;

import java.util.function.Supplier;

public interface IFlowFactory<C extends Component> extends Supplier<C> {
}
