package org.bklab.flow.components.range;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DatePickerFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.util.lumo.LumoStyles;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class LocalDateRangeComboHelper extends HorizontalLayout {

    private final DatePicker start;
    private final DatePicker end;

    {
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    }

    public LocalDateRangeComboHelper() {
        this(createMinimalDatePicker("开始日期"), createMinimalDatePicker("结束日期"));
    }

    public LocalDateRangeComboHelper(DatePicker startField, DatePicker endField) {
        this.start = startField;
        this.end = endField;
        start.setWidth(100, Unit.PERCENTAGE);
        end.setWidth(100, Unit.PERCENTAGE);
        setWidth(100, Unit.PERCENTAGE);

        Div div = new DivFactory(
                new ButtonFactory("本月", e -> setToCurrentMonth()).lumoSmall().className(LumoStyles.Padding.Right.S).get(),
                new ButtonFactory("本季", e -> setToCurrentQuarter()).lumoSmall().className(LumoStyles.Padding.Right.S).get(),
                new ButtonFactory("本年", e -> setToCurrentYear()).lumoSmall().className(LumoStyles.Padding.Right.S).get()
        ).displayFlex().className(LumoStyles.Padding.Left.M).get();

        add(start, end, div);
        setWidth(100, Unit.PERCENTAGE);
        setMargin(false);
    }

    private static DatePicker createMinimalDatePicker(String placeholder) {
        return new DatePickerFactory().lumoSmall().placeholder(placeholder).widthFull().get();
    }

    private Button minimalWidth(Button b) {
        b.setWidth(50, Unit.PIXELS);
        return b;
    }

    public void setToCurrentMonth() {
        start.setValue(getCurrentFirstDayOfMonth());
        end.setValue(start.getValue().plusMonths(1).minusDays(1));
    }

    public void setToCurrentQuarter() {
        start.setValue(getCurrentFirstDayOfQuarter());
        end.setValue(start.getValue().plusMonths(3).minusDays(1));
    }

    public void setToCurrentYear() {
        start.setValue(getCurrentFirstDayOfYear());
        end.setValue(start.getValue().plusYears(1).minusDays(1));
    }

    public LocalDate getStartDate() {
        return start.getValue();
    }

    public LocalDateRangeComboHelper setStartDate(LocalDate startDate) {
        start.setValue(startDate);
        return this;
    }

    public LocalDate getEndDate() {
        return end.getValue();
    }

    public LocalDateRangeComboHelper setEndDate(LocalDate endDate) {
        end.setValue(endDate);
        return this;
    }

    public LocalDateRangeComboHelper setValue(LocalDate startDate, LocalDate endDate) {
        start.setValue(startDate);
        end.setValue(endDate);
        return this;
    }

    public LocalDateRangeComboHelper withDescription(String description) {
        Tooltips.getCurrent().setTooltip(this, description);
        return this;
    }

    public DatePicker getStart() {
        return start;
    }

    public DatePicker getEnd() {
        return end;
    }

    public LocalDate getCurrentFirstDayOfMonth() {
        return getFirstDayOfMonth(LocalDate.now());
    }

    public LocalDate getFirstDayOfMonth(LocalDate date) {
        if (date == null) return null;
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public LocalDate getCurrentFirstDayOfQuarter() {
        return getFirstDayOfQuarter(LocalDate.now());
    }

    public LocalDate getFirstDayOfQuarter(LocalDate date) {
        if (date == null) return null;
        return date.with(date.getMonth().firstMonthOfQuarter()).with(TemporalAdjusters.firstDayOfMonth());
    }

    public LocalDate getCurrentFirstDayOfHalfYear() {
        return getFirstDayOfHalfYear(LocalDate.now());
    }

    public LocalDate getFirstDayOfHalfYear(LocalDate date) {
        if (date == null) return null;
        return LocalDate.of(date.getYear(), (date.getMonthValue() > 6 ? 7 : 1), 1);
    }

    public LocalDate getCurrentFirstDayOfYear() {
        return getFirstDayOfYear(LocalDate.now());
    }

    public LocalDate getFirstDayOfYear(LocalDate date) {
        if (date == null) return null;
        return LocalDate.of(date.getYear(), 1, 1);
    }
}
