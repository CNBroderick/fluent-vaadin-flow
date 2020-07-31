package org.bklab.flow;

import com.vaadin.flow.component.Component;
import org.bklab.flow.base.AttachNotifierFactory;
import org.bklab.flow.base.DetachNotifierFactory;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public abstract class FlowFactory<C extends Component, E extends FlowFactory<C, E>> implements IFlowFactory<C>,
        AttachNotifierFactory<C, FlowFactory<C, E>>,
        DetachNotifierFactory<C, FlowFactory<C, E>> {

    private final C component;

    public FlowFactory(C component) {
        this.component = component;
    }

    public E onEnabledStateChanged(boolean onEnabledStateChanged) {
        get().onEnabledStateChanged(onEnabledStateChanged);
        return (E) this;
    }

    public E id(String id) {
        get().setId(id);
        return (E) this;
    }

    public E visible(boolean visible) {
        get().setVisible(visible);
        return (E) this;
    }

    public E lumoSmall() {
        get().getElement().setAttribute("theme", "small");
        return (E) this;
    }

    public E attributeTitle(String title) {
        get().getElement().setAttribute("title", title);
        return (E) this;
    }

    public E peek(Consumer<C> consumer) {
        consumer.accept(get());
        return (E) this;
    }

    @Override
    public C get() {
        return component;
    }
}
