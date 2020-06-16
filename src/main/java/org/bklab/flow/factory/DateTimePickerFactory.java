package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

public class DateTimePickerFactory extends FlowFactory<DateTimePicker, DateTimePickerFactory> implements
        AbstractFieldFactory<LocalDateTime, DateTimePicker, DateTimePickerFactory>,
        HasStyleFactory<DateTimePicker, DateTimePickerFactory>,
        HasSizeFactory<DateTimePicker, DateTimePickerFactory>,
        HasThemeFactory<DateTimePicker, DateTimePickerFactory>,
        HasValidationFactory<DateTimePicker, DateTimePickerFactory>,
        FocusableFactory<DateTimePicker, DateTimePickerFactory> {

    public DateTimePickerFactory() {
        super(new DateTimePicker());
    }

    public DateTimePickerFactory(DateTimePicker component) {
        super(component);
        component.setLocale(Locale.CHINA);
    }

    public DateTimePickerFactory(String label) {
        this(new DateTimePicker(label));
    }

    public DateTimePickerFactory(String label, LocalDateTime initialDateTime) {
        this(new DateTimePicker(label, initialDateTime));
    }

    public DateTimePickerFactory(LocalDateTime initialDateTime) {
        this(new DateTimePicker(initialDateTime));
    }

    public DateTimePickerFactory(HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>> listener) {
        this(new DateTimePicker(listener));
    }

    public DateTimePickerFactory(String label, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>> listener) {
        this(new DateTimePicker(label, listener));
    }

    public DateTimePickerFactory(LocalDateTime initialDateTime, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>> listener) {
        this(new DateTimePicker(initialDateTime, listener));
    }

    public DateTimePickerFactory(String label, LocalDateTime initialDateTime, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime>> listener) {
        this(new DateTimePicker(label, initialDateTime, listener));
    }

    public DateTimePickerFactory(LocalDateTime initialDateTime, Locale locale) {
        this(new DateTimePicker(initialDateTime, locale));
    }

    public DateTimePickerFactory readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return this;
    }

    public DateTimePickerFactory value(LocalDateTime value) {
        get().setValue(value);
        return this;
    }

    public DateTimePickerFactory locale(Locale locale) {
        get().setLocale(locale);
        return this;
    }

    public DateTimePickerFactory removeThemeNames(String... removeThemeNames) {
        get().removeThemeNames(removeThemeNames);
        return this;
    }

    public DateTimePickerFactory themeName(String string, boolean set) {
        get().setThemeName(string, set);
        return this;
    }

    public DateTimePickerFactory setThemeName(String themeName) {
        get().setThemeName(themeName);
        return this;
    }

    public DateTimePickerFactory removeThemeName(String removeThemeName) {
        get().removeThemeName(removeThemeName);
        return this;
    }

    public DateTimePickerFactory themeName(String themeName) {
        get().addThemeName(themeName);
        return this;
    }

    public DateTimePickerFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public DateTimePickerFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public DateTimePickerFactory themeNames(String... themeNames) {
        get().addThemeNames(themeNames);
        return this;
    }

    public DateTimePickerFactory weekNumbersVisible(boolean weekNumbersVisible) {
        get().setWeekNumbersVisible(weekNumbersVisible);
        return this;
    }

    public DateTimePickerFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public DateTimePickerFactory min(LocalDateTime min) {
        get().setMin(min);
        return this;
    }

    public DateTimePickerFactory timePlaceholder(String timePlaceholder) {
        get().setTimePlaceholder(timePlaceholder);
        return this;
    }

    public DateTimePickerFactory max(LocalDateTime max) {
        get().setMax(max);
        return this;
    }

    public DateTimePickerFactory datePickerI18n(DatePicker.DatePickerI18n datePickerI18n) {
        get().setDatePickerI18n(datePickerI18n);
        return this;
    }

    public DateTimePickerFactory datePlaceholder(String datePlaceholder) {
        get().setDatePlaceholder(datePlaceholder);
        return this;
    }

    public DateTimePickerFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public DateTimePickerFactory step(Duration step) {
        get().setStep(step);
        return this;
    }

}
