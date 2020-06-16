package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.HasFilterableDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.function.SerializableFunction;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasFilterableDataProviderFactory<T, F,
        M extends Component & HasFilterableDataProvider<T, F>,
        E extends HasFilterableDataProviderFactory<T, F, M, E>> extends
        IFlowFactory<M>, HasItemsFactory<T, M, E> {

    default E dataProvider(DataProvider<T, F> dataProvider) {
        get().setDataProvider(dataProvider);
        return (E) this;
    }

    default <C> E dataProvider(DataProvider<T, C> dataProvider, SerializableFunction<F, C> serializableFunction) {
        get().setDataProvider(dataProvider, serializableFunction);
        return (E) this;
    }
}
