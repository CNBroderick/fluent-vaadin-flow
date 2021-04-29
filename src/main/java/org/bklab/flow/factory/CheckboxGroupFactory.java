/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-25 15:20:54
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.CheckboxGroupFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupDataView;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupListDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.util.Set;
import java.util.stream.Stream;

public class CheckboxGroupFactory<T> extends FlowFactory<CheckboxGroup<T>, CheckboxGroupFactory<T>> implements
        GeneratedVaadinCheckboxGroupFactory<T, CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasItemComponentsFactory<T, CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasSizeFactory<CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasValidationFactory<CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        MultiSelectFactory<T, CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasListDataViewFactory<T, CheckboxGroupListDataView<T>, CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasDataViewFactory<T, Void, CheckboxGroupDataView<T>, CheckboxGroup<T>, CheckboxGroupFactory<T>>,
        HasHelperFactory<CheckboxGroup<T>, CheckboxGroupFactory<T>> {

    public CheckboxGroupFactory() {
        this(new CheckboxGroup<>());
    }

    public CheckboxGroupFactory(CheckboxGroup<T> checkbox) {
        super(checkbox);
        checkbox.getStyle().set("align-items", "flex-end");
    }

    public CheckboxGroupFactory<T> readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return this;
    }

    public CheckboxGroupFactory<T> value(Set<T> value) {
        get().setValue(value);
        return this;
    }

    public CheckboxGroupFactory<T> items(ListDataProvider<T> items) {
        get().setItems(items);
        return this;
    }

    public CheckboxGroupFactory<T> items(InMemoryDataProvider<T> items) {
        get().setItems(items);
        return this;
    }

    @Deprecated
    public CheckboxGroupFactory<T> items(Stream<T> items) {
        get().setItems(items);
        return this;
    }

    @SafeVarargs
    public final CheckboxGroupFactory<T> items(T... items) {
        get().setItems(items);
        return this;
    }

    public CheckboxGroupFactory<T> label(String label) {
        get().setLabel(label);
        return this;
    }

    public CheckboxGroupFactory<T> updateSelection(Set<T> addedItems, Set<T> removedItems) {
        get().updateSelection(addedItems, removedItems);
        return this;
    }

    public CheckboxGroupFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public CheckboxGroupFactory<T> invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public CheckboxGroupFactory<T> required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public CheckboxGroupFactory<T> errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public CheckboxGroupFactory<T> selectionListener(MultiSelectionListener<CheckboxGroup<T>, T> listener) {
        get().addSelectionListener(listener);
        return this;
    }

    public CheckboxGroupFactory<T> itemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        get().setItemEnabledProvider(itemEnabledProvider);
        return this;
    }

    public CheckboxGroupFactory<T> itemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        get().setItemLabelGenerator(itemLabelGenerator);
        return this;
    }

}
