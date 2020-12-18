package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import org.bklab.flow.IFlowFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public interface HasDataProviderFactory<T,
        C extends Component & HasDataProvider<T>,
        E extends HasDataProviderFactory<T, C, E>
        > extends IFlowFactory<C>, HasItemsFactory<T, C, E> {

    default E items(List<T> items) {
        get().setItems(items);
        return (E) this;
    }

    default E items(Set<T> items) {
        get().setItems(items);
        return (E) this;
    }

    default E setItems(Collection<T> items) {
        get().setItems(items);
        return (E) this;
    }

    default E dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return (E) this;
    }
}
