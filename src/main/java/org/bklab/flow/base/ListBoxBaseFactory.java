package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.listbox.ListBoxBase;
import com.vaadin.flow.component.listbox.dataview.ListBoxDataView;
import com.vaadin.flow.component.listbox.dataview.ListBoxListDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ListBoxBaseFactory<T, C extends ListBoxBase<C, T, T>, E extends ListBoxBaseFactory<T, C, E>> extends
        IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<T, C, E>,
        HasItemComponentsFactory<T, C, E>,
        HasListDataViewFactory<T, ListBoxListDataView<T>, C, E>,
        HasDataViewFactory<T, Void, ListBoxDataView<T>, C, E>,
        HasSizeFactory<C, E> {

    default E renderer(ComponentRenderer<? extends Component, T> itemRenderer) {
        get().setRenderer(itemRenderer);
        return (E) this;
    }

    @Deprecated
    default E dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return (E) this;
    }

    default E itemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        get().setItemEnabledProvider(itemEnabledProvider);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }
}