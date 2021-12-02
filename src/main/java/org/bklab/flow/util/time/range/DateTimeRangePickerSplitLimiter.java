/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 17:19:55
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.util.time.range.DateTimeRangePickerSplitLimiter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.time.range;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.shared.Registration;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public record DateTimeRangePickerSplitLimiter(@Nonnull DatePicker minDatePicker,
                                              @Nonnull DatePicker maxDatePicker,
                                              @Nonnull TimePicker minTimePicker,
                                              @Nonnull TimePicker maxTimePicker
) implements IDateTimeRangePickerLimiter {

    @Override
    public Registration limit(Duration globalMaxDuration, LocalDateTime globalMinTime, LocalDateTime globalMaxTime) {
        if (globalMinTime == null && globalMaxTime == null && globalMaxDuration == null) {
            return () -> {

            };
        }

        if (globalMinTime != null && globalMaxTime != null && globalMinTime.isAfter(globalMaxTime)) {
            LocalDateTime t = globalMaxTime;
            globalMaxTime = globalMinTime;
            globalMinTime = t;
        }

        LocalDateTime finalGlobalMinTime = globalMinTime;
        LocalDateTime finalGlobalMaxTime = globalMaxTime;
        List<Registration> r = new ArrayList<>();

        if (globalMinTime != null) {
            minDatePicker.setMin(globalMinTime.toLocalDate());
            maxDatePicker.setMin(globalMinTime.toLocalDate());
        }

        if (globalMaxTime != null) {
            minDatePicker.setMax(globalMaxTime.toLocalDate());
            maxDatePicker.setMax(globalMaxTime.toLocalDate());
        }

        if (globalMaxDuration != null) {
            r.add(minDatePicker.addValueChangeListener(e -> {
                if (e.isFromClient()) checkMaxPickerDuration(globalMaxDuration, finalGlobalMinTime, finalGlobalMaxTime);
            }));

            r.add(minTimePicker.addValueChangeListener(e -> {
                if (e.isFromClient()) checkMaxPickerDuration(globalMaxDuration, finalGlobalMinTime, finalGlobalMaxTime);
            }));

            r.add(maxDatePicker.addValueChangeListener(e -> {
                if (e.isFromClient()) checkMinPickerDuration(globalMaxDuration, finalGlobalMinTime, finalGlobalMaxTime);
            }));

            r.add(maxTimePicker.addValueChangeListener(e -> {
                if (e.isFromClient()) checkMinPickerDuration(globalMaxDuration, finalGlobalMinTime, finalGlobalMaxTime);
            }));
        }

        return () -> r.forEach(Registration::remove);
    }

    private void checkTimePickerMin(LocalDateTime globalMinTime, LocalDate datePickerValue) {
        if (datePickerValue == null) {
            minTimePicker.setMin(LocalTime.MIN);
            maxTimePicker.setMin(LocalTime.MIN);
            return;
        }
        minTimePicker.setMin(globalMinTime.toLocalDate().equals(datePickerValue) ? globalMinTime.toLocalTime() : LocalTime.MIN);
        minTimePicker.getOptionalValue().filter(value -> globalMinTime.isAfter(LocalDateTime.of(datePickerValue, value)))
                .ifPresent(v -> minTimePicker.setValue(globalMinTime.toLocalTime()));
        maxTimePicker.setMin(globalMinTime.toLocalDate().equals(datePickerValue) ? globalMinTime.toLocalTime() : LocalTime.MIN);
        maxTimePicker.getOptionalValue().filter(value -> globalMinTime.isAfter(LocalDateTime.of(datePickerValue, value)))
                .ifPresent(v -> maxTimePicker.setValue(globalMinTime.toLocalTime()));
    }

    private void checkTimePickerMax(LocalDateTime globalMaxTime, LocalDate datePickerValue) {
        if (datePickerValue == null) {
            minTimePicker.setMin(LocalTime.MAX);
            maxTimePicker.setMin(LocalTime.MAX);
            return;
        }
        minTimePicker.setMax(globalMaxTime.toLocalDate().equals(datePickerValue) ? globalMaxTime.toLocalTime() : LocalTime.MAX);
        minTimePicker.getOptionalValue().filter(value -> globalMaxTime.isBefore(LocalDateTime.of(datePickerValue, value)))
                .ifPresent(v -> minTimePicker.setValue(globalMaxTime.toLocalTime()));
        maxTimePicker.setMax(globalMaxTime.toLocalDate().equals(datePickerValue) ? globalMaxTime.toLocalTime() : LocalTime.MAX);
        maxTimePicker.getOptionalValue().filter(value -> globalMaxTime.isBefore(LocalDateTime.of(datePickerValue, value)))
                .ifPresent(v -> maxTimePicker.setValue(globalMaxTime.toLocalTime()));
    }

    private void checkMaxPickerDuration(Duration globalMaxDuration, LocalDateTime globalMinTime, LocalDateTime globalMaxTime) {
        LocalDate minDatePickerValue = minDatePicker.getValue();
        LocalTime minTimePickerValue = minTimePicker.getValue();
        if (minDatePickerValue == null) return;
        if (minTimePickerValue == null) {
            minTimePickerValue = LocalTime.MIN;
            minTimePicker.setValue(LocalTime.MIN);
        }

        LocalDateTime min = LocalDateTime.of(minDatePickerValue, minTimePickerValue);

        LocalDate maxDatePickerValue = maxDatePicker.getValue();
        LocalTime maxTimePickerValue = maxTimePicker.getValue();
        if (maxDatePickerValue == null) {
            maxDatePickerValue = min.plus(globalMaxDuration).toLocalDate();
            maxDatePicker.setValue(maxDatePickerValue);
        }
        if (maxTimePickerValue == null) {
            maxTimePickerValue = LocalTime.MAX;
            maxTimePicker.setValue(maxTimePickerValue);
        }

        LocalDateTime max = LocalDateTime.of(maxDatePickerValue, maxTimePickerValue);
        if (globalMaxDuration != null && Duration.between(min, max).abs().compareTo(globalMaxDuration) > 0) {
            max = min.plus(globalMaxDuration);
        } else if (min.isAfter(max)) {
            max = min.plus(globalMaxDuration);
        }
        if (globalMaxTime != null && max.isAfter(globalMaxTime)) {
            max = globalMaxTime;
        }

        if (max != null) {
            maxDatePicker.setValue(max.toLocalDate());
            maxTimePicker.setValue(max.toLocalTime());
        }

        if (minTimePicker.getValue() == null) minTimePicker.setValue(min.toLocalTime());
    }

    private void checkMinPickerDuration(Duration globalMaxDuration, LocalDateTime globalMinTime, LocalDateTime globalMaxTime) {
        LocalDate maxDatePickerValue = maxDatePicker.getValue();
        LocalTime maxTimePickerValue = maxTimePicker.getValue();
        if (maxDatePickerValue == null) return;
        if (maxTimePickerValue == null) {
            maxTimePickerValue = LocalTime.MAX;
            maxTimePicker.setValue(LocalTime.MAX);
        }

        LocalDateTime max = LocalDateTime.of(maxDatePickerValue, maxTimePickerValue);

        LocalDate minDatePickerValue = minDatePicker.getValue();
        LocalTime minTimePickerValue = minTimePicker.getValue();
        if (minDatePickerValue == null) {
            minDatePickerValue = max.minus(globalMaxDuration).toLocalDate();
            minDatePicker.setValue(minDatePickerValue);
        }
        if (minTimePickerValue == null) {
            minTimePickerValue = LocalTime.MIN;
            minTimePicker.setValue(minTimePickerValue);
        }

        LocalDateTime min = LocalDateTime.of(minDatePickerValue, minTimePickerValue);
        if (globalMaxDuration != null && Duration.between(min, max).abs().compareTo(globalMaxDuration) > 0) {
            min = max.minus(globalMaxDuration);
        } else if (min.isAfter(max)) {
            min = max.minus(globalMaxDuration);
        }

        if (globalMinTime != null && globalMinTime.compareTo(min) > 0) {
            min = globalMinTime;
        }
        if (globalMaxTime != null && min.isBefore(globalMinTime)) {
            min = globalMinTime;
        }

        if (min != null) {
            minDatePicker.setValue(min.toLocalDate());
            minTimePicker.setValue(min.toLocalTime());
        }

        if (maxTimePicker.getValue() == null) maxTimePicker.setValue(max.toLocalTime());
    }
}
