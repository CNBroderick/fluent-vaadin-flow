/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-06 09:39:17
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.DateTimePickerFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;
import org.bklab.flow.components.time.ChineseDatePickerI18n;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.function.Consumer;

public class DateTimePickerFactory extends FlowFactory<DateTimePicker, DateTimePickerFactory> implements
        AbstractFieldFactory<LocalDateTime, DateTimePicker, DateTimePickerFactory>,
        HasStyleFactory<DateTimePicker, DateTimePickerFactory>,
        HasSizeFactory<DateTimePicker, DateTimePickerFactory>,
        HasThemeFactory<DateTimePicker, DateTimePickerFactory>,
        HasValidationFactory<DateTimePicker, DateTimePickerFactory>,
        FocusableFactory<DateTimePicker, DateTimePickerFactory> {

    public DateTimePickerFactory() {
        this(new DateTimePicker());
    }

    public DateTimePickerFactory(DateTimePicker component) {
        super(component);
        component.setLocale(Locale.CHINA);
        get().setDatePickerI18n(ChineseDatePickerI18n.getInstance());
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

    @Override
    public DateTimePickerFactory lumoSmall() {
        super.lumoSmall().get().getElement().getChildren()
                .forEach(a -> a.setAttribute("theme", "small"));
        return this;
    }

    public DateTimePickerFactory startTimePlaceholder() {
        datePlaceholder("开始日期");
        timePlaceholder("开始时间");
        return this;
    }

    public DateTimePickerFactory endTimePlaceholder() {
        datePlaceholder("结束日期");
        timePlaceholder("结束时间");
        return this;
    }

    public DateTimePickerFactory clearButtonVisible(boolean clearButtonVisible) {
        get().getElement().getChildren().forEach(element -> element.setProperty("clearButtonVisible", clearButtonVisible));
        return this;
    }

    public DateTimePickerFactory widthMinimal() {
        get().getElement().getChildren().forEach(element -> element.getStyle().set("width", "10em"));
        return this;
    }

    public DateTimePickerFactory peekDatePicker(Consumer<DatePicker> datePickerConsumer) {
        datePickerConsumer.accept(getDatePicker());
        return this;
    }

    public DateTimePickerFactory peekTimePicker(Consumer<TimePicker> timePickerConsumer) {
        timePickerConsumer.accept(getTimePicker());
        return this;
    }

    public DatePicker getDatePicker() {
        try {
            Class<? extends Component> asSubclass = Class.forName(
                    "com.vaadin.flow.component.datetimepicker.DateTimePickerDatePicker"
            ).asSubclass(Component.class);
            return (DatePicker) get().getElement().getChild(0).as(asSubclass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("转换失败: " + e.getMessage());
        }
    }

    public TimePicker getTimePicker() {
        try {
            Class<? extends Component> asSubclass = Class.forName(
                    "com.vaadin.flow.component.datetimepicker.DateTimePickerTimePicker"
            ).asSubclass(Component.class);
            return (TimePicker) get().getElement().getChild(1).as(asSubclass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("转换失败: " + e.getMessage());
        }
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
