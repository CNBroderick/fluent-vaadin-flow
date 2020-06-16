package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasDataProviderFactory;
import org.bklab.flow.base.HasItemsAndComponentsFactory;
import org.bklab.flow.base.HasValidationFactory;
import org.bklab.flow.base.SingleSelectFactory;

public class RadioButtonGroupFactory<T> extends FlowFactory<RadioButtonGroup<T>, RadioButtonGroupFactory<T>> implements
        HasItemsAndComponentsFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        SingleSelectFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
        HasDataProviderFactory<T, RadioButtonGroup<T>, RadioButtonGroupFactory<T>>,
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

}
