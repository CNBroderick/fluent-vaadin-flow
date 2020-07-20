package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.data.provider.HasDataView;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasDataViewFactory<T, V extends DataView<T>,
        C extends Component & HasDataView<T, V>,
        E extends HasDataViewFactory<T, V, C, E>> extends IFlowFactory<C> {

    default E setItems(DataProvider<T, ?> dataProvider) {
        get().setItems(dataProvider);
        return (E) this;
    }

    default E setItems(InMemoryDataProvider<T> dataProvider) {
        //noinspection rawtypes
        this.setItems((DataProvider) dataProvider);
        return (E) this;
    }

}
