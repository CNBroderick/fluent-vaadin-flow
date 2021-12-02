/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 17:19:53
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.DateTimeRangeDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bklab.flow.components.badge.BadgeTag;
import org.bklab.flow.components.badge.BadgeTagStyle;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.dialog.AskDialog;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.dialog.crud.Buildable;
import org.bklab.flow.dialog.timerange.data.TimeRangeDataProvider;
import org.bklab.flow.factory.*;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.tab.FluentTabView;
import org.bklab.flow.util.time.range.DateTimeRangePickerSplitLimiter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
@Accessors(fluent = true)
public class DateTimeRangeDialog extends ModalDialog implements Buildable<DateTimeRangeDialog>, HasValue<TimeRangeValueChangeEvent, ITimeRangeSupplier>, AttachNotifier {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private TimeRangeDataProvider dataProvider;
    private ITimeRangeSupplier value;
    private final List<ValueChangeListener<? super TimeRangeValueChangeEvent>> valueChangeListeners = new ArrayList<>();
    private boolean readOnly = false;
    private boolean requiredIndicatorVisible = false;
    private final Map<ITimeRangeSupplier, BadgeTag> badgeTagMap = new LinkedHashMap<>();

    private DatePicker minDatePicker;
    private DatePicker maxDatePicker;
    private TimePicker minTimePicker;
    private TimePicker maxTimePicker;
    private BadgeTag currentBadgeTag;
    private FluentTabView fluentTabView;

    private final List<DateTimeRangeDialogField> dateTimeRangeDialogFields = new ArrayList<>();
    private int nextDateTimeRangeDialogFieldsId = 0;
    private ITimeRangeSupplier oldValue;

    private Duration globalMaxDuration;
    private LocalDateTime globalMinTime;
    private LocalDateTime globalMaxTime;
    private Registration pickerLimiterRegistration;

    public DateTimeRangeDialog() {
        width("40em", "40em", "90vw");
        height("25em");
    }

    public static DateTimeRangeDialog getCurrentOrCreateDefault() {
        return getCurrent().orElse(new DateTimeRangeDialog().build());
    }

    public static Optional<DateTimeRangeDialog> getCurrent() {
        return Optional.ofNullable(UI.getCurrent())
                .map(ui -> ui.getSession().getAttribute(DateTimeRangeDialog.class.getName() + "-for-ui-" + ui.getUIId()))
                .filter(DateTimeRangeDialog.class::isInstance).map(DateTimeRangeDialog.class::cast);
    }

    @Override
    public DateTimeRangeDialog build() {
        if (dataProvider == null) dataProvider = TimeRangeDataProvider.getDefaultProvider();
        title("选择时间范围[%s]".formatted(value == null ? "全部" : value.getLabel()));

        buildPickers();

        fluentTabView = new FluentTabView()
                .addTab("relative", "相对时间", this::createRelativeListTab)
                .addTab("absolute", "绝对时间", this::createAbsoluteListTab)
                .addTab("custom", "自定义", this::createCustomFormTab)
                .addTab("resent", "最近时间", false, this::createResentUsages);
        fluentTabView.asFactory().width("20em", "90vw", "30em").height("20em");
        fluentTabView.setHeightFull();
        fluentTabView.setHeight("250px");
        content(fluentTabView);
        fluentTabView.getTabs().addThemeVariants(TabsVariant.LUMO_MINIMAL);
        addCancelButton().addPrimaryButton(VaadinIcon.CHECK, "确定", e -> {
            if (checkValid()) doSave();
        });
        fluentTabView.selectFirst();
        setCloseOnOutsideClick(false);
        setCloseOnEsc(true);
        setResizable(false);

        footerLeft(new FluentButton(VaadinIcon.CALENDAR_CLOCK, "最大时间范围", e -> {
            setToGlobalValue();
            doSave();
        }).primary());

        return this;
    }

    private void buildPickers() {
        if (minDatePicker == null) {
            minDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("12em").get();
        }

        if (maxDatePicker == null) {
            maxDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("12em").get();
        }

        if (minTimePicker == null) {
            minTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("12em").step(Duration.ofMinutes(15)).get();
        }

        if (maxTimePicker == null) {
            maxTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("12em").step(Duration.ofMinutes(15)).get();
        }

        if (globalMinTime != null) {
            minDatePicker.setMin(globalMinTime.toLocalDate());
            maxDatePicker.setMin(globalMinTime.toLocalDate());
        }

        if (globalMaxTime != null) {
            minDatePicker.setMax(globalMaxTime.toLocalDate());
            maxDatePicker.setMax(globalMaxTime.toLocalDate());
        }

        pickerLimiterRegistration = new DateTimeRangePickerSplitLimiter(minDatePicker, maxDatePicker,
                minTimePicker, maxTimePicker).limit(globalMaxDuration, globalMinTime, globalMaxTime);
    }

    public DateTimeRangeDialog rebuild() {
        fluentTabView.removeCache();
        title("选择时间范围[%s]".formatted(value == null ? "全部" : value.getLabel()));
        if (pickerLimiterRegistration != null) pickerLimiterRegistration.remove();
        pickerLimiterRegistration = new DateTimeRangePickerSplitLimiter(minDatePicker, maxDatePicker,
                minTimePicker, maxTimePicker).limit(globalMaxDuration, globalMinTime, globalMaxTime);
        return this;
    }

    private Component createRelativeListTab() {
        return createSingleSelectBadgeTags(dataProvider.relativeTimeRanges());
    }

    private Component createAbsoluteListTab() {
        return createSingleSelectBadgeTags(dataProvider.absoluteTimeRanges());
    }

    private void doSave() {
        TimeRangeValueChangeEvent valueChangeEvent = new TimeRangeValueChangeEvent(this, false, oldValue, value);
        valueChangeListeners.forEach(listener -> listener.valueChanged(valueChangeEvent));
        if (value != null) dataProvider.addToStore(value);
        close();
    }

    private boolean checkValid() {
        if (globalMinTime == null && globalMaxTime == null && globalMaxDuration == null) return true;

        if (value == null && globalMaxDuration != null) {
            ITimeRangeSupplier globalValue = createGlobalValue();
            new AskDialog("是否选择最大时间范围：" + getRangeLabel(globalValue.getMin(), globalValue.getMax()) + "？", y -> {
                Optional.ofNullable(globalValue.getMin()).ifPresent(min -> {
                    minDatePicker.setValue(min.toLocalDate());
                    minTimePicker.setValue(min.toLocalTime());
                });
                Optional.ofNullable(globalValue.getMax()).ifPresent(max -> {
                    maxDatePicker.setValue(max.toLocalDate());
                    maxTimePicker.setValue(max.toLocalTime());
                });
                fluentTabView.select("custom");
                this.value = globalValue;
                checkValid();
            }).build().open();
            return false;
        }

        if (value != null && globalMaxTime != null && Optional.ofNullable(value.getMax()).orElse(LocalDateTime.MAX).compareTo(globalMaxTime) > 0) {
            new ErrorDialog("结束时间不能超过：" + DATE_TIME_FORMATTER.format(globalMaxTime));
            return false;
        }

        if (value != null && globalMinTime != null && globalMinTime.compareTo(Optional.ofNullable(value.getMin()).orElse(LocalDateTime.MIN)) > 0) {
            new ErrorDialog("开始时间不能超过：" + DATE_TIME_FORMATTER.format(globalMaxTime));
            return false;
        }

        return true;
    }

    public void setToGlobalValue() {
        value = createGlobalValue();
    }

    private ITimeRangeSupplier createGlobalValue() {
        if (globalMinTime == null && globalMaxTime == null && globalMaxDuration == null) {
            return null;
        }
        LocalDateTime min = globalMinTime;
        LocalDateTime max = globalMaxTime;
        if (globalMaxDuration != null) {
            if (min == null && max == null) {
                max = LocalDateTime.now();
                min = max.minus(globalMaxDuration);
            } else if (min != null && max != null) {
                max = min.plus(globalMaxDuration);
            } else if (min != null) {
                max = min.plus(globalMaxDuration);
            } else {
                min = max.minus(globalMaxDuration);
            }
        }
        return TimeRangeSupplier.appoint(min, max, "最大时间范围", getRangeLabel(min, max));
    }

    private HorizontalLayout createSingleSelectBadgeTags(Collection<ITimeRangeSupplier> rangeSuppliers) {
        HorizontalLayoutFactory div = new HorizontalLayoutFactory().padding(true)
                .alignItemsCenter().alignItemsStretch().displayFlex().flexWrap();
        for (ITimeRangeSupplier rangeSupplier : rangeSuppliers) {
            if (!isValidRangeSupplier(rangeSupplier)) continue;

            BadgeTag badgeTag = new BadgeTag(new SpanFactory(rangeSupplier.getLabel()).style("word-break", "break-all").marginAuto().get(), BadgeTagStyle.GREY);
            badgeTagMap.put(rangeSupplier, badgeTag);
            div.add(badgeTag);
            badgeTag.asFactory().width("10.5em").fontWeight(400).cursor("pointer").displayFlex().clickListener(event -> select(rangeSupplier, badgeTag));
        }
        return div.get();
    }

    private boolean isValidRangeSupplier(ITimeRangeSupplier rangeSupplier) {
        LocalDateTime max = rangeSupplier.getMax();
        LocalDateTime min = rangeSupplier.getMin();
        if (globalMaxDuration != null && Duration.between(min, max).abs().compareTo(globalMaxDuration) > 0) return false;
        if (globalMinTime != null && (min.isBefore(globalMinTime) || max.isBefore(globalMinTime))) return false;
        return globalMaxTime == null || (!min.isAfter(globalMaxTime) && !max.isAfter(globalMaxTime));
    }

    private void select(ITimeRangeSupplier rangeSupplier, BadgeTag badgeTag) {
        if (readOnly) return;
        if (value == rangeSupplier) {
            setToGlobalValue();
            title("选择时间范围[全部]");
            this.currentBadgeTag.removeClassName(BadgeTagStyle.BLUE.style);
            this.currentBadgeTag.addClassName(BadgeTagStyle.GREY.style);
            return;
        }

        this.value = rangeSupplier;
        title("选择时间范围[" + value.getLabel() + "]");

        if (this.currentBadgeTag != null) {
            this.currentBadgeTag.removeClassName(BadgeTagStyle.BLUE.style);
            this.currentBadgeTag.addClassName(BadgeTagStyle.GREY.style);
        }

        badgeTag.removeClassName(BadgeTagStyle.GREY.style);
        badgeTag.addClassName(BadgeTagStyle.BLUE.style);

        this.currentBadgeTag = badgeTag;
    }

    private void handleClearAbsoluteEvent() {
        minDatePicker.clear();
        minTimePicker.clear();
        maxDatePicker.clear();
        maxTimePicker.clear();
    }

    private FormLayout createCustomFormTab() {
        minDatePicker.addValueChangeListener(e -> handleSaveAbsoluteEvent());
        minTimePicker.addValueChangeListener(e -> handleSaveAbsoluteEvent());
        maxDatePicker.addValueChangeListener(e -> handleSaveAbsoluteEvent());
        maxTimePicker.addValueChangeListener(e -> handleSaveAbsoluteEvent());
        return new FormLayoutFactory()
                .formItem(new HorizontalLayoutFactory(minDatePicker, minTimePicker).alignSelfCenter().get(), "开始时间：")
                .formItem(new HorizontalLayoutFactory(maxDatePicker, maxTimePicker).alignSelfCenter().get(), "结束时间：")
/*                .formItem(new HorizontalLayoutFactory(
                        new ButtonFactory().text("应用").clickListener(event -> handleSaveAbsoluteEvent()).lumoPrimary().lumoSmall().lumoTertiaryInline().get(),
                        new ButtonFactory().text("清除").clickListener(event -> {
                            minDatePicker.clear();
                            minTimePicker.clear();
                            maxDatePicker.clear();
                            maxTimePicker.clear();
                        }).lumoError().lumoSmall().lumoTertiaryInline().get()

                ).alignItemsCenter().get(), "操作：")*/
                .initFormLayout().fitModalDialogWidth()
                .get()
                ;
    }

    private Component createResentUsages() {
        List<ITimeRangeSupplier> storeCache = dataProvider.getStoreCache();
        return storeCache.isEmpty() ? new EmptyLayout("从未选择任何时间范围。") : createSingleSelectBadgeTags(storeCache);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super TimeRangeValueChangeEvent> valueChangeListener) {
        valueChangeListeners.add(valueChangeListener);
        return () -> valueChangeListeners.remove(valueChangeListener);
    }

    @Override
    public ITimeRangeSupplier getValue() {
        return value;
    }

    public void setValue(ITimeRangeSupplier value) {
        this.oldValue = this.value;
        this.value = value;
        TimeRangeValueChangeEvent valueChangeEvent = new TimeRangeValueChangeEvent(this, false, oldValue, value);
        this.valueChangeListeners.forEach(listener -> listener.valueChanged(valueChangeEvent));
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        minDatePicker.setReadOnly(readOnly);
        maxDatePicker.setReadOnly(readOnly);
        minTimePicker.setReadOnly(readOnly);
        maxTimePicker.setReadOnly(readOnly);
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {

    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return false;
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
            setToGlobalValue();
            title("选择时间范围[全部]");
            return;
        }

        if (min != null && max != null && min.isAfter(max)) {
            LocalDateTime t0 = max;
            max = min;
            min = t0;
        }

        String label = getRangeLabel(min, max);
        value = TimeRangeSupplier.appoint(min, max, "自定义", label);
        title("选择时间范围[" + label + "]");
    }

    public String getRangeLabel() {
        return value == null ? "全部" : getRangeLabel(value.getMin(), value.getMax());
    }

    public String getRangeLabel(LocalDateTime min, LocalDateTime max) {
        if (min != null && max != null) {
            return "%s至%s".formatted(DATE_TIME_FORMATTER.format(min), DATE_TIME_FORMATTER.format(max));
        } else if (min != null) {
            return "从%s开始".formatted(DATE_TIME_FORMATTER.format(min));
        } else if (max != null) {
            return "至%s结束".formatted(DATE_TIME_FORMATTER.format(max));
        } else {
            return "全部";
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        UI ui = attachEvent.getUI();
        VaadinSession session = ui.getSession();
        session.setAttribute(DateTimeRangeDialog.class.getName() + "-for-ui-" + ui.getUIId(), this);
    }

    public DateTimeRangeDialogField createFiled() {
        DateTimeRangeDialogField field = new DateTimeRangeDialogField(this).build();
        dateTimeRangeDialogFields.add(field);
        field.addDetachListener(detachEvent -> dateTimeRangeDialogFields.remove(field));
        field.setId("date-time-range-dialog-field-" + Optional.ofNullable(UI.getCurrent()).map(UI::getUIId).orElse(0) + "-" + (++nextDateTimeRangeDialogFieldsId));
        return field;
    }
}
