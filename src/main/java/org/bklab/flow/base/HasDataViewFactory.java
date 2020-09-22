package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.data.provider.HasDataView;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasDataViewFactory<T, F, V extends DataView<T>,
        C extends Component & HasDataView<T, F, V>,
        E extends HasDataViewFactory<T, F, V, C, E>> extends IFlowFactory<C> {

    default E setItems(DataProvider<T, F> dataProvider) {
        get().setItems(dataProvider);
        return (E) this;
    }

    default E setItems(InMemoryDataProvider<T> dataProvider) {
        //noinspection rawtypes
        this.setItems((DataProvider) dataProvider);
        return (E) this;
    }

}
