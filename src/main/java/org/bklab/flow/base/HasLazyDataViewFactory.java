package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.HasLazyDataView;
import com.vaadin.flow.data.provider.LazyDataView;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasLazyDataViewFactory<T, V extends LazyDataView<T>,
        C extends Component & HasLazyDataView<T, V>,
        E extends HasLazyDataViewFactory<T, V, C, E>> extends IFlowFactory<C> {

    default E setItems(CallbackDataProvider.FetchCallback<T, Void> fetchCallback) {
        get().setItems(fetchCallback);
        return (E) this;
    }

    default E setItems(CallbackDataProvider.FetchCallback<T, Void> fetchCallback, CallbackDataProvider.CountCallback<T, Void> countCallback) {
        get().setItems(fetchCallback, countCallback);
        return (E) this;
    }

    default E setItems(BackEndDataProvider<T, Void> backEndDataProvider) {
        get().setItems(backEndDataProvider);
        return (E) this;
    }


}
