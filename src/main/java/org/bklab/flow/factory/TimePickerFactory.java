package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.timepicker.GeneratedVaadinTimePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

public class TimePickerFactory extends FlowFactory<TimePicker, TimePickerFactory> implements
        GeneratedVaadinTimePickerFactory<TimePicker, TimePickerFactory>,
        HasSizeFactory<TimePicker, TimePickerFactory>,
        HasValidationFactory<TimePicker, TimePickerFactory>,
        HasHelperFactory<TimePicker, TimePickerFactory>,
        HasEnabledFactory<TimePicker, TimePickerFactory> {

    public TimePickerFactory() {
        this(new TimePicker());
    }

    public TimePickerFactory(LocalTime time) {
        this(new TimePicker(time));
    }

    public TimePickerFactory(String label) {
        this(new TimePicker(label));
    }

    public TimePickerFactory(String label, LocalTime time) {
        this(new TimePicker(label, time));
    }

    public TimePickerFactory(HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<TimePicker, LocalTime>> listener) {
        this(new TimePicker(listener));
    }

    public TimePickerFactory(TimePicker component) {
        super(component);
        get().setLocale(Locale.CHINA);
    }

    public TimePickerFactory value(LocalTime value) {
        get().setValue(value);
        return this;
    }

    public TimePickerFactory locale(Locale locale) {
        get().setLocale(locale);
        return this;
    }

    public TimePickerFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public TimePickerFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public TimePickerFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public TimePickerFactory required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public TimePickerFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public TimePickerFactory step(Duration step) {
        get().setStep(step);
        return this;
    }

    @Deprecated
    public TimePickerFactory max(String max) {
        get().setMax(max);
        return this;
    }

    public TimePickerFactory minTime(LocalTime minTime) {
        get().setMinTime(minTime);
        return this;
    }

    @Deprecated
    public TimePickerFactory min(String min) {
        get().setMin(min);
        return this;
    }

    public TimePickerFactory maxTime(LocalTime maxTime) {
        get().setMaxTime(maxTime);
        return this;
    }

    public TimePickerFactory invalidChangeListener(ComponentEventListener<GeneratedVaadinTimePicker.InvalidChangeEvent<TimePicker>> invalidChangeListener) {
        get().addInvalidChangeListener(invalidChangeListener);
        return this;
    }

    public TimePickerFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public TimePickerFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

}
