package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.selection.MultiSelect;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import org.bklab.flow.IFluentFlowFactory;

import java.util.Set;

public interface MultiSelectFactory<T, C extends Component & MultiSelect<C, T>, E extends MultiSelectFactory<T, C, E>>
        extends IFluentFlowFactory<C, E> {

    default E value(Set<T> value) {
        get().setValue(value);
        return thisObject();
    }

    default E updateSelection(Set<T> addedItems, Set<T> removedItems) {
        get().updateSelection(addedItems, removedItems);
        return thisObject();
    }

    default E deselectAll() {
        get().deselectAll();
        return thisObject();
    }

    default E select(T... select) {
        get().select(select);
        return thisObject();
    }

    default E select(Iterable<T> select) {
        get().select(select);
        return thisObject();
    }

    default E deselect(T[] deselect) {
        get().deselect(deselect);
        return thisObject();
    }

    default E deselect(Iterable<T> deselect) {
        get().deselect(deselect);
        return thisObject();
    }

    default E selectionListener(MultiSelectionListener<C, T> selectionListener) {
        get().addSelectionListener(selectionListener);
        return thisObject();
    }

}
