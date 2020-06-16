package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.NumberField;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.AbstractNumberFieldFactory;

public class NumberFieldFactory extends FlowFactory<NumberField, NumberFieldFactory> implements AbstractNumberFieldFactory<Double, NumberField, NumberFieldFactory> {
    public NumberFieldFactory() {
        this(new NumberField());
    }

    public NumberFieldFactory(String label) {
        this(new NumberField(label));
    }

    public NumberFieldFactory(String label, String placeholder) {
        this(new NumberField(label, placeholder));
    }

    public NumberFieldFactory(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<NumberField, Double>> listener) {
        this(new NumberField(listener));
    }

    public NumberFieldFactory(String label, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<NumberField, Double>> listener) {
        this(new NumberField(label, listener));
    }

    public NumberFieldFactory(String label, Double initialValue, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<NumberField, Double>> listener) {
        this(new NumberField(label, initialValue, listener));
    }

    public NumberFieldFactory(NumberField component) {
        super(component);
    }

    public NumberFieldFactory min(double min) {
        get().setMin(min);
        return this;
    }

    @Deprecated
    public NumberFieldFactory pattern(String pattern) {
        get().setPattern(pattern);
        return this;
    }

    @Deprecated
    public NumberFieldFactory minLength(int minLength) {
        get().setMinLength(minLength);
        return this;
    }

    @Deprecated
    public NumberFieldFactory maxLength(int maxLength) {
        get().setMaxLength(maxLength);
        return this;
    }

    public NumberFieldFactory step(double step) {
        get().setStep(step);
        return this;
    }

    public NumberFieldFactory max(double max) {
        get().setMax(max);
        return this;
    }

    @Deprecated
    public NumberFieldFactory preventInvalidInput(boolean preventInvalidInput) {
        get().setPreventInvalidInput(preventInvalidInput);
        return this;
    }

}
