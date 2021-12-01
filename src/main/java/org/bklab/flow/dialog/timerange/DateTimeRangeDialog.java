/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 17:28:24
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.DateTimeRangeDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;
import lombok.Setter;
import org.bklab.flow.components.badge.BadgeTag;
import org.bklab.flow.components.badge.BadgeTagStyle;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.dialog.crud.Buildable;
import org.bklab.flow.dialog.timerange.data.TimeRangeDataProvider;
import org.bklab.flow.factory.*;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.tab.FluentTabView;
import org.bklab.flow.util.element.HasSaveListeners;
import org.bklab.flow.util.lumo.FontSize;
import org.bklab.flow.util.lumo.FontWeight;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

@Getter
@Setter
public class DateTimeRangeDialog extends ModalDialog implements Buildable<DateTimeRangeDialog>, HasValue<TimeRangeValueChangeEvent,
        ITimeRangeSupplier>, HasSaveListeners<ITimeRangeSupplier, DateTimeRangeDialog> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private TimeRangeDataProvider dataProvider;
    private ITimeRangeSupplier value;
    private List<ValueChangeListener<? super TimeRangeValueChangeEvent>> valueChangeListeners;
    private boolean readOnly = false;
    private boolean requiredIndicatorVisible = false;
    private final Map<ITimeRangeSupplier, BadgeTag> badgeTagMap = new LinkedHashMap<>();

    private DatePicker minDatePicker;
    private DatePicker maxDatePicker;
    private TimePicker minTimePicker;
    private TimePicker maxTimePicker;
    private final List<Consumer<ITimeRangeSupplier>> saveListeners = new ArrayList<>();
    private BadgeTag currentBadgeTag;
    private FluentTabView fluentTabView;

    public DateTimeRangeDialog() {
        width("500px").height("315px");
    }

    @Override
    public DateTimeRangeDialog build() {
        if (dataProvider == null) dataProvider = TimeRangeDataProvider.getDefaultProvider();
        title("选择时间范围[%s]".formatted(value == null ? "全部" : value.getLabel()));

        minDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).widthMinimal().get();
        maxDatePicker = new DatePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).widthMinimal().get();
        minTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("10em").step(Duration.ofMinutes(15)).get();
        maxTimePicker = new TimePickerFactory().locale(Locale.CHINA).lumoSmall().clearButtonVisible(true).width("10em").step(Duration.ofMinutes(15)).get();

        fluentTabView = new FluentTabView()
                .addTab("relative", "相对时间", this::createRelativeListTab)
                .addTab("absolute", "绝对时间", this::createAbsoluteListTab)
                .addTab("custom", "自定义", this::createCustomFormTab)
                .addTab("resent", "最近时间", this::createResentUsages);
        fluentTabView.asFactory().width("20em", "90vw", "30em").height("20em");
        content(fluentTabView);
        fluentTabView.getTabs().addThemeVariants(TabsVariant.LUMO_MINIMAL);
        addCancelButton().addSaveButton(e -> doSave());
        fluentTabView.selectFirst();
        return this;
    }

    private HorizontalLayout createSingleSelectBadgeTags(Collection<ITimeRangeSupplier> rangeSuppliers) {
        HorizontalLayoutFactory div = new HorizontalLayoutFactory().padding(true)
                .alignItemsCenter().alignItemsStretch().displayFlex().flexWrap();
        for (ITimeRangeSupplier rangeSupplier : rangeSuppliers) {
            Button button = new ButtonFactory(rangeSupplier.getName())
                    .lumoSmall().lumoTertiaryInline().lumoContrast().get();
            BadgeTag badgeTag = new BadgeTag(button, BadgeTagStyle.GREY);
            badgeTagMap.put(rangeSupplier, badgeTag);
            div.add(badgeTag);

            badgeTag.setWidth("8em");
            badgeTag.addClassNames(FontSize.L.getValue());
            badgeTag.getStyle().set("font-weight", FontWeight._400.getValue());

            badgeTag.addClickListener(event -> select(rangeSupplier, badgeTag));
            button.addClickListener(event -> select(rangeSupplier, badgeTag));
        }
        return div.get();
    }

    private void select(ITimeRangeSupplier rangeSupplier, BadgeTag badgeTag) {
        if (value == rangeSupplier) {
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

    private Component createRelativeListTab() {
        return createSingleSelectBadgeTags(dataProvider.relativeTimeRanges());
    }

    private Component createAbsoluteListTab() {
        return createSingleSelectBadgeTags(dataProvider.absoluteTimeRanges());
    }

    private void doSave() {
        callSaveListeners(value);
        if (value != null) dataProvider.addToStore(value);
        close();
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
            title("选择时间范围[全部]");
            return;
        }

        if (min != null && max != null && min.isAfter(max)) {
            LocalDateTime t0 = max;
            max = min;
            min = t0;
        }

        String label;
        if (min != null && max != null) {
            label = "[%s至%s]".formatted(DATE_TIME_FORMATTER.format(min), DATE_TIME_FORMATTER.format(max));
        } else if (min != null) {
            label = "[从%s开始]".formatted(DATE_TIME_FORMATTER.format(min));
        } else {
            label = "[至%s结束]".formatted(DATE_TIME_FORMATTER.format(max));
        }

        value = TimeRangeSupplier.appoint(min, max, "自定义", "自定义" + label);
        title("选择时间范围" + label);
    }

    public String getRangeLabel() {
        return null;
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
                .initFormLayout().fitModalDialogWidth()
                .get()
                ;
    }

    private FluentTabView createRelativeTab() {
        FluentTabView tabView = new FluentTabView();
        tabView.addTab("relative", "相对时间", () -> new ListBoxFactory<ITimeRangeSupplier>().lumoSmall().sizeFull()
                .renderer(new TextRenderer<>(ITimeRangeSupplier::getLabel)).items(dataProvider.relativeTimeRanges()).get());
        tabView.addTab("absolute", "绝对时间", () -> new ListBoxFactory<ITimeRangeSupplier>().lumoSmall().sizeFull()
                .renderer(new TextRenderer<>(ITimeRangeSupplier::getLabel)).items(dataProvider.absoluteTimeRanges()).get());
        tabView.selectFirst();
        tabView.getContent().setHeightFull();
        return tabView;
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

    public void setValue(ITimeRangeSupplier value) {
        this.value = value;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {

    }

    @Override
    public List<Consumer<ITimeRangeSupplier>> getSaveListeners() {
        return saveListeners;
    }
}
