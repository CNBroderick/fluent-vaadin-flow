/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.base.HasListDataViewFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.HasListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.ListDataView;
import org.bklab.flow.IFlowFactory;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("unchecked")
public interface HasListDataViewFactory<T, V extends ListDataView<T, ?>,
        C extends Component & HasListDataView<T, V>,
        E extends HasListDataViewFactory<T, V, C, E>> extends IFlowFactory<C> {
    default E items(ListDataProvider<T> listDataProvider) {
        get().setItems(listDataProvider == null ? new ListDataProvider<>(Collections.emptyList()) : listDataProvider);
        return (E) this;
    }

    default E items(Collection<T> items) {
        get().setItems(DataProvider.ofCollection(items == null ? Collections.emptyList() : items));
        return (E) this;
    }

    default E items(T... items) {
        get().setItems(items == null ? DataProvider.ofItems() : DataProvider.ofItems(items));
        return (E) this;
    }


}
