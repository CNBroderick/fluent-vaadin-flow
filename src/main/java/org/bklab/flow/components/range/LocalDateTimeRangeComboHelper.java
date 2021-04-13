package org.bklab.flow.components.range;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.factory.ButtonFactory;
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
        start.setWidth(100, Unit.PERCENTAGE);
        end.setWidth(100, Unit.PERCENTAGE);
        setWidth(100, Unit.PERCENTAGE);

        Div div = new DivFactory(
                new ButtonFactory("本日", e -> setToCurrentDay()).lumoSmall().className(LumoStyles.Padding.Right.S).get(),
                new ButtonFactory("本周", e -> setToCurrentWeek()).lumoSmall().className(LumoStyles.Padding.Right.S).get(),
                new ButtonFactory("本月", e -> setToCurrentMonth()).lumoSmall().className(LumoStyles.Padding.Right.S).get()
        ).displayFlex().className(LumoStyles.Padding.Left.M).get();

        add(start, end, div);
        setWidth(100, Unit.PERCENTAGE);
        setMargin(false);
    }

    private static DateTimePicker createMinimalDateTimePicker( String placeholder) {
        return new DateTimePickerFactory().lumoSmall()
                .datePlaceholder(placeholder + "日期")
                .timePlaceholder(placeholder + "时间")
                .widthMinimal().get();
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
