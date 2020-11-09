package org.bklab.flow.components.time;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import dev.mett.vaadin.tooltip.Tooltips;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.*;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.util.url.QueryParameterBuilder;
import org.bklab.flow.util.url.QueryParameterUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_PRIMARY;

public class TimeRangeDialog extends ModalDialog implements ITimeRangeSupplier, HasSaveListeners<TimeRangeDialog, TimeRangeDialog> {

    private final DateTimePicker minDatetimePicker;
    private final DateTimePicker maxDatetimePicker;
    private final TextField statusField;
    private final Map<String, Button> buttonMap = new LinkedHashMap<>();
    private final List<Consumer<TimeRangeDialog>> saveListeners = new ArrayList<>();
    private int defaultMaxDurationDay = 30;
    private ITimeRangeSupplier timeRangeSupplier;

    public TimeRangeDialog() {
        this(30);
    }

    public TimeRangeDialog(int defaultMaxDurationDay) {
        this.defaultMaxDurationDay = defaultMaxDurationDay;

        this.minDatetimePicker = new DateTimePickerFactory().startTimePlaceholder().lumoSmall()
                .widthFull().clearButtonVisible(false).step(Duration.ofMinutes(5)).get();
        this.maxDatetimePicker = new DateTimePickerFactory().endTimePlaceholder().lumoSmall()
                .widthFull().clearButtonVisible(false).step(Duration.ofMinutes(5)).get();
        this.statusField = new TextFieldFactory().lumoSmall().width("12em").readOnly()
                .suffixComponent(new ButtonFactory().clickListener(e -> {
                    if (!isOpened()) open();
                }).icon(VaadinIcon.CLOCK).lumoIcon().lumoSmall().lumoTertiaryInline().get()).get();
        this.timeRangeSupplier = TimeRangeSupplier.lastDays(30);

        FormLayout appointForm = new FormLayoutFactory().visible(false).style("padding-top", "1em")
                .formItem(minDatetimePicker, "开始时间：").formItem(maxDatetimePicker, "结束时间：")
                .widthFull().componentFullWidth().formItemAlignEnd().formItemAlignVerticalCenter().get();

        content(createTimeSelector(appointForm), appointForm);

        addCancelButton().addButton(new FluentButton(VaadinIcon.CHECK, "确定", this::processSave).primary());


        title("选择时间").width("720px", "720px", "90vw").setMinHeight("350px");
        refreshStatusField();
    }

    public static String extend(QueryParameterUtil util, Map<String, Supplier<Object>> map) {
        ITimeRangeSupplier timeRangeSupplier = createTimeRangeSupplier(util);
        if (map != null && !(map instanceof UnmodifiableMap)) {
            map.put("minDatetime", timeRangeSupplier::getMin);
            map.put("maxDatetime", timeRangeSupplier::getMax);
        }
        return timeRangeSupplier.getLabel();
    }

    public static void write(Map<String, Supplier<Object>> parameterMap, QueryParameterBuilder builder) {
        Optional.ofNullable(parameterMap.get("minDatetime")).map(Supplier::get)
                .map(d -> ((LocalDateTime) d).toEpochSecond(ZoneOffset.ofHours(8)))
                .ifPresent(s -> builder.add("minDatetime", s));
        Optional.ofNullable(parameterMap.get("maxDatetime")).map(Supplier::get)
                .map(d -> ((LocalDateTime) d).toEpochSecond(ZoneOffset.ofHours(8)))
                .ifPresent(s -> builder.add("maxDatetime", s));
    }

    public static ITimeRangeSupplier createTimeRangeSupplier(QueryParameterUtil util) {
        return createTimeRangeSupplier(30, util);
    }

    public static ITimeRangeSupplier createTimeRangeSupplier(int defaultMaxDurationDay, QueryParameterUtil util) {
        long minDatetime = util.getLong("minDatetime");
        long maxDatetime = util.getLong("maxDatetime");
        int maxDuration = defaultMaxDurationDay * 24 * 60 * 60;
        if (maxDatetime > 0 && minDatetime <= 0 || maxDatetime - minDatetime > maxDuration)
            minDatetime = maxDatetime - maxDuration;
        if (minDatetime > 0 && maxDatetime <= 0 || maxDatetime > minDatetime + maxDuration)
            maxDatetime = minDatetime + maxDuration;

        return TimeRangeSupplier.appoint(
                LocalDateTime.ofEpochSecond(minDatetime, 0, ZoneOffset.ofHours(8)),
                LocalDateTime.ofEpochSecond(maxDatetime, 0, ZoneOffset.ofHours(8))
        );
    }

    public TimeRangeDialog setDefaultMaxDurationDay(int defaultMaxDurationDay) {
        this.defaultMaxDurationDay = defaultMaxDurationDay;
        return this;
    }

    public TimeRangeDialog init(QueryParameterUtil util) {
        this.timeRangeSupplier = createTimeRangeSupplier(util);
        refreshStatusField();
        return this;
    }

    private void processSave(ClickEvent<Button> buttonClickEvent) {

        LocalDateTime min = getMin();
        LocalDateTime max = getMax();

        if (min == null) {
            new ErrorDialog("请设置开始时间").build().open();
            return;
        }

        if (max == null) {
            new ErrorDialog("请设置结束时间").build().open();
            return;
        }

        long duration = Duration.between(min, max).getSeconds() / 60 / 60 / 24;
        if (duration > defaultMaxDurationDay) {
            new ErrorDialog("暂不支持单次查询时间大于：" + defaultMaxDurationDay + "天，当前为：" + duration + "天。").build().open();
            return;
        }

        refreshStatusField();
        callSaveListeners(this);
        close();
    }

    private void refreshStatusField() {
        String label = timeRangeSupplier.getLabel();
        statusField.setValue(label);
        Tooltips.getCurrent().setTooltip(statusField, "查询 " + label + " 的性能数据。");
    }


    public TimeRangeDialog extend(Map<String, Supplier<Object>> parameterMap, ToolBar header, Consumer<TimeRangeDialog> saveListener) {
        parameterMap.put("minDatetime", this::getMin);
        parameterMap.put("maxDatetime", this::getMax);
        this.addSaveListeners(saveListener);
        header.right(statusField);
        return this;
    }

    public TextField getStatusField() {
        return statusField;
    }

    private HorizontalLayout createTimeSelector(FormLayout appointForm) {
        HorizontalLayout main = new HorizontalLayoutFactory().flexWrap().widthFull()
                .justifyContentModeEnd().alignItemsBaseline().displayFlex().get();

        main.add(createRecently(appointForm, TimeRangeSupplier.lastMinutes(5)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastMinutes(15)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastMinutes(30)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastHours(1)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastHours(6)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastHours(12)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastDays(1)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastDays(3)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastDays(7)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastDays(15)));
        main.add(createRecently(appointForm, TimeRangeSupplier.lastDays(30)));
        main.add(createAppointButton(appointForm));
        return main;
    }

    private ITimeRangeSupplier createAppointTimeRangeSupplier() {
        return new TimeRangeSupplier(minDatetimePicker::getValue, maxDatetimePicker::getValue);
    }

    private Button createRecently(FormLayout formLayout, ITimeRangeSupplier timeRangeSupplier) {
        Button button = createButton(timeRangeSupplier.getName());

        button.addClickListener(e -> {
            setTimeRangeSupplier(timeRangeSupplier);
            formLayout.setVisible(false);
        });

        return button;
    }

    private Button createAppointButton(FormLayout formLayout) {
        Button button = createButton("自定义");
        button.addClickListener(e -> {
            formLayout.setVisible(true);
            minDatetimePicker.setValue(getMin());
            maxDatetimePicker.setValue(getMax());
            setTimeRangeSupplier(new TimeRangeSupplier(minDatetimePicker::getValue, maxDatetimePicker::getValue));
        });
        return button;
    }

    private Button createButton(String text) {
        return new ButtonFactory().text(text).height("4em").minWidth("7em").clickListener(e -> {
            buttonMap.values().forEach(a -> a.removeThemeVariants(LUMO_PRIMARY));
            e.getSource().addThemeVariants(LUMO_PRIMARY);
        }).peek(b -> buttonMap.put(text, b)).get();
    }

    private void setTimeRangeSupplier(ITimeRangeSupplier timeRangeSupplier) {
        this.timeRangeSupplier = timeRangeSupplier;
    }

    @Override
    public String getName() {
        return timeRangeSupplier.getName();
    }

    @Override
    public LocalDateTime getMin() {
        return timeRangeSupplier.getMin();
    }

    @Override
    public LocalDateTime getMax() {
        return timeRangeSupplier.getMax();
    }

    @Override
    public String getLabel() {
        return timeRangeSupplier.getLabel();
    }

    @Override
    public List<Consumer<TimeRangeDialog>> getSaveListeners() {
        return saveListeners;
    }
}
