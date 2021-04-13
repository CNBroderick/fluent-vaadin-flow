package org.bklab.flow;

import com.vaadin.flow.component.Component;

public abstract class FlowFactory<C extends Component, E extends FlowFactory<C, E>> implements IFluentFlowFactory<C, E> {

    private final C component;

    public FlowFactory(C component) {
        this.component = component;
    }


    public E lumoSmall() {
        get().getElement().setAttribute("theme", "small");
        return thisObject();
    }

    @Override
    public C get() {
        return component;
    }
}
