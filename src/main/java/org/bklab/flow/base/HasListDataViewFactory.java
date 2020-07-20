package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.HasListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.ListDataView;
import org.bklab.flow.IFlowFactory;

import java.util.Collection;

@SuppressWarnings("unchecked")
public interface HasListDataViewFactory<T, V extends ListDataView<T, ?>,
        C extends Component & HasListDataView<T, V>,
        E extends HasListDataViewFactory<T, V, C, E>> extends IFlowFactory<C> {
    default E setItems(ListDataProvider<T> listDataProvider) {
        get().setItems(listDataProvider);
        return (E) this;
    }

    default E setItems(Collection<T> items) {
        get().setItems(DataProvider.ofCollection(items));
        return (E) this;
    }

    default E setItems(T... items) {
        get().setItems(DataProvider.ofItems(items));
        return (E) this;
    }


}
