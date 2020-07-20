package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import org.bklab.flow.FlowFactory;

public class ComponentFactory<C extends Component> extends FlowFactory<C, ComponentFactory<C>> {
    public ComponentFactory(C component) {
        super(component);
    }
}
