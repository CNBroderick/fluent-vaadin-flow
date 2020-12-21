package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.HasLazyDataView;
import com.vaadin.flow.data.provider.LazyDataView;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasLazyDataViewFactory<T, F, V extends LazyDataView<T>,
        C extends Component & HasLazyDataView<T, F, V>,
        E extends HasLazyDataViewFactory<T, F, V, C, E>> extends IFlowFactory<C> {

    default E items(CallbackDataProvider.FetchCallback<T, F> fetchCallback) {
        get().setItems(fetchCallback);
        return (E) this;
    }

    default E items(CallbackDataProvider.FetchCallback<T, F> fetchCallback,
                    CallbackDataProvider.CountCallback<T, F> countCallback) {
        get().setItems(fetchCallback, countCallback);
        return (E) this;
    }

    default E items(BackEndDataProvider<T, F> backEndDataProvider) {
        get().setItems(backEndDataProvider);
        return (E) this;
    }


}
