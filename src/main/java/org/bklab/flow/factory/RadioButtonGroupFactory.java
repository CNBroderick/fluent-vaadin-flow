/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.factory.RadioButtonGroupFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupDataView;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupListDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

public class RadioButtonGroupFactory<T> extends FlowFactory<RadioButtonGroup<T>, RadioButtonGroupFactory<T>> implements
        GeneratedVaadinRadioGroupFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasItemComponentsFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasListDataViewFactory<T, RadioButtonGroupListDataView<T>, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasDataViewFactory<T, Void, RadioButtonGroupDataView<T>, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        SingleSelectFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasHelperFactory<RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasValidationFactory<RadioButtonGroup<T>, RadioButtonGroupFactory<T>> {

    public RadioButtonGroupFactory() {
        super(new RadioButtonGroup<>());
    }

    public RadioButtonGroupFactory(RadioButtonGroup<T> component) {
        super(component);
    }

    public RadioButtonGroupFactory<T> readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return this;
    }

    public RadioButtonGroupFactory<T> itemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        get().setItemEnabledProvider(itemEnabledProvider);
        return this;
    }

    public RadioButtonGroupFactory<T> errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public RadioButtonGroupFactory<T> label(String label) {
        get().setLabel(label);
        return this;
    }

    public RadioButtonGroupFactory<T> invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public RadioButtonGroupFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public RadioButtonGroupFactory<T> required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public RadioButtonGroupFactory<T> renderer(ComponentRenderer<? extends Component, T> renderer) {
        get().setRenderer(renderer);
        return this;
    }

    @Override
    public RadioButtonGroupFactory<T> value(T value) {
        if (value == null) get().clear();
        else get().setValue(value);
        return this;
    }

    @Override
    public RadioButtonGroupFactory<T> valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<RadioButtonGroup<T>, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return this;
    }
}
