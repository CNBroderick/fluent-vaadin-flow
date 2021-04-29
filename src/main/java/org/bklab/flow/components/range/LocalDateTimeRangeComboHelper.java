/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-29 09:15:16
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.range.LocalDateTimeRangeComboHelper
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.range;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.DateTimePickerFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.util.lumo.LumoStyles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeRangeComboHelper extends HorizontalLayout {

    private final DateTimePicker start;
    private final DateTimePicker end;

    {
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    }

    public LocalDateTimeRangeComboHelper() {
        this(createMinimalDateTimePicker("开始"), createMinimalDateTimePicker("结束"));
    }

    public LocalDateTimeRangeComboHelper(DateTimePicker startField, DateTimePicker endField) {
        this.start = startField;
        this.end = endField;

        Div div = new DivFactory(
                new FluentButton("本日", e -> setToCurrentDay()),
                new FluentButton("本周", e -> setToCurrentWeek()),
                new FluentButton("本月", e -> setToCurrentMonth())
        ).displayFlex().alignSelfCenter().className("container-right-items-no-border-right", LumoStyles.Padding.Left.S).get();

        add(start, end, div);
        setMargin(false);
    }

    private static DateTimePicker createMinimalDateTimePicker(String placeholder) {
        return new DateTimePickerFactory().lumoSmall()
                .clearButtonVisible(true)
                .datePlaceholder(placeholder + "日期")
                .timePlaceholder(placeholder + "时间")
                .widthMinimal().get();
    }

    public LocalDateTimeRangeComboHelper limit(LocalDateTime start, LocalDateTime end) {
        this.start.setMin(start);
        this.end.setMin(start);
        this.start.setMin(end);
        this.end.setMin(end);
        return this;
    }

    private Button minimalWidth(Button b) {
        b.setWidth(50, Unit.PIXELS);
        return b;
    }

    public void setToCurrentDay() {
        start.setValue(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        end.setValue(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }

    public void setToCurrentWeek() {
        start.setValue(LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1), LocalTime.MIN));
        end.setValue(LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1), LocalTime.MAX));
    }

    public void setToCurrentMonth() {
        start.setValue(LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN));
        end.setValue(LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX));
    }

    public LocalDateTime getStartDateTime() {
        return start.getValue();
    }

    public LocalDateTimeRangeComboHelper setStartDate(LocalDateTime startDate) {
        start.setValue(startDate);
        return this;
    }

    public LocalDateTime getEndDateTime() {
        return end.getValue();
    }

    public LocalDateTimeRangeComboHelper setEndDate(LocalDateTime endDate) {
        end.setValue(endDate);
        return this;
    }

    public LocalDateTimeRangeComboHelper setValue(LocalDateTime startDate, LocalDateTime endDate) {
        start.setValue(startDate);
        end.setValue(endDate);
        return this;
    }

    public LocalDateTimeRangeComboHelper withDescription(String description) {
        Tooltips.getCurrent().setTooltip(this, description);
        return this;
    }

    public DateTimePicker getStart() {
        return start;
    }

    public DateTimePicker getEnd() {
        return end;
    }

}
