package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasComponentsFactory<T extends Component & HasComponents, E extends HasComponentsFactory<T, E>>
        extends IFlowFactory<T>, HasEnabledFactory<T, E> {

    default E add (Component[] add) {
        get().add(add);
        return  (E) this;
    }
    default E add (String add) {
        get().add(add);
        return  (E) this;
    }

    default E remove(Component[] remove) {
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
