/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-06 09:39:17
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.DatePickerFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.GeneratedVaadinDatePicker;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinDatePickerFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.base.HasValidationFactory;
import org.bklab.flow.components.time.ChineseDatePickerI18n;

import java.time.LocalDate;
import java.util.Locale;

public class DatePickerFactory extends FlowFactory<DatePicker, DatePickerFactory> implements
        GeneratedVaadinDatePickerFactory<LocalDate, DatePicker, DatePickerFactory>,
        HasSizeFactory<DatePicker, DatePickerFactory>,
        HasValidationFactory<DatePicker, DatePickerFactory> {

    public DatePickerFactory() {
        this(new DatePicker());
    }

    public DatePickerFactory(LocalDate initLocalDate) {
        this(new DatePicker(initLocalDate));
    }

    public DatePickerFactory(String label) {
        this(new DatePicker(label));
    }

    public DatePickerFactory(String label, LocalDate initialDate) {
        this(new DatePicker(label, initialDate));
    }

    public DatePickerFactory(HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>> listener) {
        this(new DatePicker(listener));
    }

    public DatePickerFactory(String label, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>> listener) {
        this(new DatePicker(label, listener));
    }

    public DatePickerFactory(LocalDate initialDate, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>> listener) {
        this(new DatePicker(initialDate, listener));
    }

    public DatePickerFactory(String label, LocalDate initialDate, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>> listener) {
        this(new DatePicker(label, initialDate, listener));
    }

    public DatePickerFactory(LocalDate initialDate, Locale locale) {
        this(new DatePicker(initialDate, locale));
        get().setLocale(locale);
    }

    public DatePickerFactory(DatePicker component) {
        this(component, Locale.CHINA, ChineseDatePickerI18n.getInstance());
    }

    public DatePickerFactory(DatePicker component, Locale locale) {
        this(component, locale, locale == null || locale == Locale.CHINA ? ChineseDatePickerI18n.getInstance() : null);
    }

    public DatePickerFactory(DatePicker component, Locale locale, DatePicker.DatePickerI18n datePickerI18n) {
        super(component);
        if (locale != null) get().setLocale(locale);
        if (datePickerI18n != null) get().setI18n(datePickerI18n);
    }

    public DatePickerFactory name(String name) {
        get().setName(name);
        return this;
    }

    public DatePickerFactory open() {
        get().open();
        return this;
    }

    public DatePickerFactory locale(Locale locale) {
        get().setLocale(locale);
        return this;
    }

    public DatePickerFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public DatePickerFactory openedChangeListener(ComponentEventListener<GeneratedVaadinDatePicker.OpenedChangeEvent<DatePicker>> listener) {
        get().addOpenedChangeListener(listener);
        return this;
    }

    public DatePickerFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public DatePickerFactory weekNumbersVisible(boolean weekNumbersVisible) {
        get().setWeekNumbersVisible(weekNumbersVisible);
        return this;
    }

    public DatePickerFactory invalidChangeListener(ComponentEventListener<GeneratedVaadinDatePicker.InvalidChangeEvent<DatePicker>> listener) {
        get().addInvalidChangeListener(listener);
        return this;
    }

    public DatePickerFactory min(LocalDate min) {
        get().setMin(min);
        return this;
    }

    public DatePickerFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public DatePickerFactory initialPosition(LocalDate initialPosition) {
        get().setInitialPosition(initialPosition);
        return this;
    }

    public DatePickerFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public DatePickerFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public DatePickerFactory required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public DatePickerFactory max(LocalDate max) {
        get().setMax(max);
        return this;
    }

    public DatePickerFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public DatePickerFactory i18n(DatePicker.DatePickerI18n i18n) {
        get().setI18n(i18n);
        return this;
    }

    public DatePickerFactory opened(boolean opened) {
        get().setOpened(opened);
        return this;
    }
}
