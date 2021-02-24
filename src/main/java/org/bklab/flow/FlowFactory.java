package org.bklab.flow;

import com.vaadin.flow.component.Component;
import org.bklab.flow.base.AttachNotifierFactory;
import org.bklab.flow.base.DetachNotifierFactory;
import org.bklab.flow.base.HasElementFactory;
import org.bklab.flow.base.HasReturnThis;

import java.util.function.Consumer;

public abstract class FlowFactory<C extends Component, E extends FlowFactory<C, E>> implements
        IFlowFactory<C>, HasElementFactory<C, E>,
        AttachNotifierFactory<C, FlowFactory<C, E>>,
        DetachNotifierFactory<C, FlowFactory<C, E>>,
        HasReturnThis<E> {

    private final C component;

    public FlowFactory(C component) {
        this.component = component;
    }

    public E onEnabledStateChanged(boolean onEnabledStateChanged) {
        get().onEnabledStateChanged(onEnabledStateChanged);
        return thisObject();
    }

    public E id(String id) {
        get().setId(id);
        return thisObject();
    }

    public E visible(boolean visible) {
        get().setVisible(visible);
        return thisObject();
    }

    public E lumoSmall() {
        get().getElement().setAttribute("theme", "small");
        return thisObject();
    }

    public E attributeTitle(String title) {
        get().getElement().setAttribute("title", title);
        return thisObject();
    }

    public E peek(Consumer<C> consumer) {
        consumer.accept(get());
        return thisObject();
    }

    public E peek(boolean canPeek, Consumer<C> consumer) { 
        return canPeek ? thisObject() : peek(consumer);
    }

    @Override
    public C get() {
        return component;
    }
}
