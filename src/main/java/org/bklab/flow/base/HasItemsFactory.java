package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasItems;
import org.bklab.flow.IFlowFactory;

import java.util.Collection;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public interface HasItemsFactory<T, C extends Component & HasItems<T>, E extends HasItemsFactory<T, C, E>> extends IFlowFactory<C> {
    default E items(Collection<T> items) {
        get().setItems(items);
        return (E) this;
    }

    default E items(T[] items) {
        get().setItems(items);
        return (E) this;
    }

    default E items(Stream<T> items) {
        get().setItems(items);
        return (E) this;
    }
}
