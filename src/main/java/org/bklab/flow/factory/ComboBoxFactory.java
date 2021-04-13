package org.bklab.flow.factory;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.GeneratedVaadinComboBox;
import com.vaadin.flow.component.combobox.dataview.ComboBoxDataView;
import com.vaadin.flow.component.combobox.dataview.ComboBoxLazyDataView;
import com.vaadin.flow.component.combobox.dataview.ComboBoxListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.function.SerializableFunction;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.util.Collection;

public class ComboBoxFactory<T> extends FlowFactory<ComboBox<T>, ComboBoxFactory<T>> implements
        GeneratedVaadinComboBoxFactory<T, ComboBox<T>, ComboBoxFactory<T>>,
        HasSizeFactory<ComboBox<T>, ComboBoxFactory<T>>,
        HasValidationFactory<ComboBox<T>, ComboBoxFactory<T>>,
        HasDataViewFactory<T, String, ComboBoxDataView<T>, ComboBox<T>, ComboBoxFactory<T>>,
        HasListDataViewFactory<T, ComboBoxListDataView<T>, ComboBox<T>, ComboBoxFactory<T>>,
        HasLazyDataViewFactory<T, String, ComboBoxLazyDataView<T>, ComboBox<T>, ComboBoxFactory<T>>,
        HasHelperFactory<ComboBox<T>, ComboBoxFactory<T>> {

    public ComboBoxFactory() {
        super(new ComboBox<>());
    }

    public ComboBoxFactory(String label) {
        super(new ComboBox<>(label));
    }

    public ComboBoxFactory(String label, Collection<T> items) {
        super(new ComboBox<>(label, items));
    }

    @SafeVarargs
    public ComboBoxFactory(String label, T... items) {
        super(new ComboBox<>(label, items));
    }

    public ComboBoxFactory(ComboBox<T> component) {
        super(component);
    }

    public ComboBoxFactory<T> value(T value) {
        get().setValue(value);
        return this;
    }

    public ComboBoxFactory<T> opened(boolean opened) {
        get().setOpened(opened);
        return this;
    }

    public ComboBoxFactory<T> required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public ComboBoxFactory<T> placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public ComboBoxFactory<T> pageSize(int pageSize) {
        get().setPageSize(pageSize);
        return this;
    }

    public ComboBoxFactory<T> pattern(String pattern) {
        get().setPattern(pattern);
        return this;
    }

    public ComboBoxFactory<T> autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public ComboBoxFactory<T> label(String label) {
        get().setLabel(label);
        return this;
    }

    @SafeVarargs
    public final ComboBoxFactory<T> items(ComboBox.ItemFilter<T> comboBoxItemFilterT, T... t) {
        get().setItems(comboBoxItemFilterT, t);
        return this;
    }

    public ComboBoxFactory<T> items(ComboBox.ItemFilter<T> comboBoxItemFilterT, Collection<T> collectionT) {
        get().setItems(comboBoxItemFilterT, collectionT);
        return this;
    }

    public ComboBoxFactory<T> items(Collection<T> items) {
        get().setItems(items);
        return this;
    }

    public ComboBoxFactory<T> itemsAndSelectFirst(Collection<T> items) {
        get().setItems(items);
        if (items != null) items.stream().findFirst().ifPresent(get()::setValue);
        return this;
    }

    public ComboBoxFactory<T> dataProvider(ComboBox.FetchItemsCallback<T> fetchItems, SerializableFunction<String, Integer> sizeCallback) {
        get().setDataProvider(fetchItems, sizeCallback);
        return this;
    }

    public ComboBoxFactory<T> dataProvider(ComboBox.ItemFilter<T> itemFilter, ListDataProvider<T> listDataProvider) {
        get().setDataProvider(itemFilter, listDataProvider);
        return this;
    }

    public ComboBoxFactory<T> dataProvider(ListDataProvider<T> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public ComboBoxFactory<T> errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public ComboBoxFactory<T> invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public ComboBoxFactory<T> renderer(Renderer<T> renderer) {
        get().setRenderer(renderer);
        return this;
    }

    public ComboBoxFactory<T> customValueSetListener(ComponentEventListener<GeneratedVaadinComboBox.CustomValueSetEvent<ComboBox<T>>> listener) {
        get().addCustomValueSetListener(listener);
        return this;
    }

    public ComboBoxFactory<T> itemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        get().setItemLabelGenerator(itemLabelGenerator);
        return this;
    }

    public ComboBoxFactory<T> clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public ComboBoxFactory<T> requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public ComboBoxFactory<T> preventInvalidInput(boolean preventInvalidInput) {
        get().setPreventInvalidInput(preventInvalidInput);
        return this;
    }

    public ComboBoxFactory<T> allowCustomValue(boolean allowCustomValue) {
        get().setAllowCustomValue(allowCustomValue);
        return this;
    }

    public ComboBoxFactory<T> lumoSmall() {
        get().getElement().setAttribute("theme", "small");
        get().getStyle().set("margin-top", "auto 0").set("margin-bottom", "auto 0");
        return this;
    }

    public ComboBoxFactory<T> lumoSmall30pxHeight() {
        get().getElement().setAttribute("theme", "small");
        get().getStyle().set("height", "30px")
                .set("margin-top", "auto 0").set("margin-bottom", "auto 0");
        return this;
    }
}
