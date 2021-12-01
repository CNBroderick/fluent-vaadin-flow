/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:48
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.DateTimeRangeDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;
import lombok.Setter;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.dialog.crud.Buildable;
import org.bklab.flow.dialog.timerange.data.TimeRangeDataProvider;
import org.bklab.flow.factory.*;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.TitleLayout;
import org.bklab.flow.layout.tab.FluentTabView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Tag("date-time-range-select")
@Getter
@Setter
public class DateTimeRangeDialog extends ModalDialog implements Buildable<DateTimeRangeDialog>, HasValue<TimeRangeValueChangeEvent, ITimeRangeSupplier> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private TimeRangeDataProvider dataProvider;
    private ITimeRangeSupplier value;
    private List<ValueChangeListener<? super TimeRangeValueChangeEvent>> valueChangeListeners;
    private boolean readOnly = false;
    private boolean requiredIndicatorVisible = false;
    private H5 selectValueName = new H5();

    private DatePicker minDatePicker;
    private DatePicker maxDatePicker;
    private TimePicker minTimePicker;
    private TimePicker maxTimePicker;

    public DateTimeRangeDialog() {
        title("选择时间范围");
        width("560px").height("470px");
    }

    @Override
    public DateTimeRangeDialog build() {
        if (dataProvider == null) dataProvider = TimeRangeDataProvider.getDefaultProvider();

        minDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).widthMinimal().placeholder("起始日期").get();
        maxDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).widthMinimal().placeholder("起始日期").get();
        minTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("10em").step(Duration.ofMinutes(15)).get();
        maxTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("10em").step(Duration.ofMinutes(15)).get();

        FormLayout absoluteForm = createAbsoluteForm();
        FluentTabView relativeTab = createRelativeTab();
        TitleLayout resentUsages = createResentUsages();

        content(new DivFactory(new Div(absoluteForm, resentUsages), relativeTab).displayFlex().width("550px").height("430px").get());
        footerLeft(selectValueName);
        addCancelButton().addSaveButton(e -> doSave());
        return this;
    }

    private void doSave() {

    }

    private void handleSaveAbsoluteEvent() {
        LocalDate minDate = minDatePicker.getValue();
        LocalDate maxDate = maxDatePicker.getValue();
        LocalTime minTime = minTimePicker.getValue();
        LocalTime maxTime = maxTimePicker.getValue();

        LocalDateTime min = null;
        if (minDate != null) {
            min = LocalDateTime.of(minDate, minTime == null ? LocalTime.MIN : minTime);
        }
        LocalDateTime max = null;
        if (maxDate != null) {
            max = LocalDateTime.of(maxDate, maxTime == null ? LocalTime.MAX : maxTime);
        }

        if (min == null && max == null) {
            value = null;
            return;
        }

        if (min != null && max != null && min.isAfter(max)) {
            LocalDateTime t0 = max;
            max = min;
            min = t0;
        }

        String label = "自定义";
        if (min != null && max != null) {
            label += "[%s至%s]".formatted(DATE_TIME_FORMATTER.format(min), DATE_TIME_FORMATTER.format(max));
        } else if (min != null) {
            label += "[从%s开始]".formatted(DATE_TIME_FORMATTER.format(min));
        } else {
            label += "[至%s结束]".formatted(DATE_TIME_FORMATTER.format(max));
        }

        value = TimeRangeSupplier.appoint(min, max, "自定义", label);
    }

    private void handleClearAbsoluteEvent() {
        minDatePicker.clear();
        minTimePicker.clear();
        maxDatePicker.clear();
        maxTimePicker.clear();
    }

    private FormLayout createAbsoluteForm() {
        Button select = FluentButton.saveButton().clickListener(e -> handleSaveAbsoluteEvent()).asFactory().text("应用").get();
        Button clear = FluentButton.cancelButton().clickListener(e -> handleClearAbsoluteEvent()).asFactory().text("清除").get();

        return new FormLayoutFactory()
                .formItem(new DivFactory(minDatePicker, minTimePicker).displayFlex().get(), "开始时间：")
                .formItem(new DivFactory(maxDatePicker, maxTimePicker).displayFlex().get(), "结束时间：")
                .formItem(new DivFactory(select, clear).displayFlex().get(), "")
                .fitModalDialogWidth().formItemAlignVerticalCenter().warpWhenOverflow()
                .get();
    }

    private FluentTabView createRelativeTab() {
        FluentTabView tabView = new FluentTabView();
        tabView.addTab("relative", "相对时间", () ->
                new SelectFactory<ITimeRangeSupplier>().lumoSmall().itemLabelGenerator(ITimeRangeSupplier::getLabel)
                        .items(dataProvider.relativeTimeRanges()).get()
        );
        tabView.addTab("absolute", "绝对时间", () ->
                new SelectFactory<ITimeRangeSupplier>().lumoSmall().itemLabelGenerator(ITimeRangeSupplier::getLabel)
                        .items(dataProvider.absoluteTimeRanges()).get()
        );
        tabView.selectFirst();

        return tabView;
    }

    private TitleLayout createResentUsages() {
        List<ITimeRangeSupplier> storeCache = dataProvider.getStoreCache();
        TitleLayout titleLayout = new TitleLayout("最近选择：");
        if (storeCache.isEmpty()) {
            titleLayout.content(new SelectFactory<ITimeRangeSupplier>()
                    .lumoSmall().itemLabelGenerator(ITimeRangeSupplier::getLabel).items(storeCache).get());
        } else {
            titleLayout.content(new EmptyLayout("从未选择时间范围。"));
        }
        return titleLayout;
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super TimeRangeValueChangeEvent> valueChangeListener) {
        valueChangeListeners.add(valueChangeListener);
        return () -> valueChangeListeners.remove(valueChangeListener);
    }

    public void setValue(ITimeRangeSupplier value) {
        this.value = value;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {

    }

}
