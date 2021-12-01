/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:48
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.factory.TimePickerFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.timepicker.GeneratedVaadinTimePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinTimePickerFactory;
import org.bklab.flow.base.HasEnabledFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.base.HasValidationFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

public class TimePickerFactory extends FlowFactory<TimePicker, TimePickerFactory> implements
        GeneratedVaadinTimePickerFactory<TimePicker, TimePickerFactory>,
        HasSizeFactory<TimePicker, TimePickerFactory>,
        HasValidationFactory<TimePicker, TimePickerFactory>,
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
    public TimePickerFactory minTime(LocalTime minTime) {
        return min(minTime);
    }

    public TimePickerFactory min(LocalTime minTime) {
        get().setMin(minTime);
        return this;
    }

    @Deprecated
    public TimePickerFactory maxTime(LocalTime maxTime) {
        return max(maxTime);
    }

    public TimePickerFactory max(LocalTime maxTime) {
        get().setMax(maxTime);
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
