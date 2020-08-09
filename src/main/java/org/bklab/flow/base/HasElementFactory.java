package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import org.bklab.flow.IFlowFactory;

public interface HasElementFactory<C extends Component & HasElement, E extends HasElementFactory<C, E>> extends IFlowFactory<C> {


}
