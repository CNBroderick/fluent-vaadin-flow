package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasOrderedComponents;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasOrderedComponentsFactory<T extends Component & HasOrderedComponents, E extends HasOrderedComponentsFactory<T, E>> extends
        IFlowFactory<T>, HasComponentsFactory<T, E> {
    default E indexOf(Component indexOf) {
        get().indexOf(indexOf);
        return (E) this;
    }

    default E replace(Component oldComponent, Component newComponent) {
        get().replace(oldComponent, newComponent);
        return (E) this;
    }

    default E remove(Component... remove) {
        get().remove(remove);
        return (E) this;
    }

    default E removeAll() {
        get().removeAll();
        return (E) this;
    }

    default E componentAsFirst(Component componentAsFirst) {
        get().addComponentAsFirst(componentAsFirst);
        return (E) this;
    }

    default E componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return (E) this;
    }
}
