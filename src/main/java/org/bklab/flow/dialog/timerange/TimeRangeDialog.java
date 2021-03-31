package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import dev.mett.vaadin.tooltip.Tooltips;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.*;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.util.element.HasSaveListeners;
import org.bklab.flow.util.url.QueryParameterBuilder;
import org.bklab.flow.util.url.QueryParameterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_PRIMARY;

public class TimeRangeDialog extends ModalDialog implements ITimeRangeSupplier, HasSaveListeners<TimeRangeDialog, TimeRangeDialog> {

    private static final Logger logger = LoggerFactory.getLogger(TimeRangeDialog.class);
    private final DateTimePicker minDatetimePicker;
    private final DateTimePicker maxDatetimePicker;
    private final TextField statusField;
    private final Map<String, Button> buttonMap = new LinkedHashMap<>();
    private final Map<String, ITimeRangeSupplier> timeRangeSupplierMap = new LinkedHashMap<>();
    private final List<Consumer<TimeRangeDialog>> saveListeners = new ArrayList<>();
    private ITimeRangeSupplier timeRangeSupplier;
    private ITimeRangeSupplier globalTimeRangeSupplier;
    private boolean memoryMode = true;

    private final int defaultMaxDurationDay;

    private ITimeRangeSupplier[] initTimeRangeSupplier;

    {
        this.minDatetimePicker = new DateTimePickerFactory().startTimePlaceholder().lumoSmall()
                .widthFull().clearButtonVisible(false).step(Duration.ofMinutes(5)).get();
        this.maxDatetimePicker = new DateTimePickerFactory().endTimePlaceholder().lumoSmall()
                .widthFull().clearButtonVisible(false).step(Duration.ofMinutes(5)).get();
        this.statusField = new TextFieldFactory().lumoSmall().width("12em").readOnly()
                .suffixComponent(new ButtonFactory().clickListener(e -> {
                    if (!isOpened()) open();
                }).icon(VaadinIcon.CLOCK).lumoIcon().lumoSmall().lumoTertiaryInline().get()).get();
        defaultMaxDurationDay = 366;
    }

    public TimeRangeDialog() {
        build();
    }

    public TimeRangeDialog(ITimeRangeSupplier... initTimeRangeSupplier) {
        initTimeRangeSupplier(initTimeRangeSupplier);
    }

    public TimeRangeDialog initTimeRangeSupplier(ITimeRangeSupplier... initTimeRangeSupplier) {
        this.initTimeRangeSupplier = initTimeRangeSupplier;
        return this;
    }

    public TimeRangeDialog defaultInitTimeRangeSupplier() {
        return this.initTimeRangeSupplier(
                TimeRangeSupplier.lastMinutes(5),
                TimeRangeSupplier.lastMinutes(15),
                TimeRangeSupplier.lastMinutes(30),
                TimeRangeSupplier.lastHours(1),
                TimeRangeSupplier.lastHours(6),
                TimeRangeSupplier.lastHours(12),
                TimeRangeSupplier.lastDays(1),
                TimeRangeSupplier.lastDays(3),
                TimeRangeSupplier.lastDays(7),
                TimeRangeSupplier.lastDays(15),
                TimeRangeSupplier.lastDays(30)
        );
    }

    public TimeRangeDialog globalTimeRangeSupplier(ITimeRangeSupplier globalTimeRangeSupplier) {
        this.globalTimeRangeSupplier = globalTimeRangeSupplier;
        return this;
    }

    public TimeRangeDialog globalTimeRangeSupplierToday() {
        this.globalTimeRangeSupplier = new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return "今天";
            }

            @Override
            public LocalDateTime getMin() {
                return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            }
        };
        return this;
    }

    public static ITimeRangeSupplier createTimeRangeSupplier(QueryParameterUtil util) {
        long minDatetime = util.getLong("minDatetime");
        long maxDatetime = util.getLong("maxDatetime");
        int maxDuration = 366 * 24 * 60 * 60;
        if (maxDatetime > 0 && minDatetime <= 0 || maxDatetime - minDatetime > maxDuration)
            minDatetime = maxDatetime - maxDuration;
        if (minDatetime > 0 && maxDatetime <= 0 || maxDatetime > minDatetime + maxDuration)
            maxDatetime = minDatetime + maxDuration;

        return TimeRangeSupplier.appoint(
                LocalDateTime.ofEpochSecond(minDatetime, 0, ZoneOffset.ofHours(8)),
                LocalDateTime.ofEpochSecond(maxDatetime, 0, ZoneOffset.ofHours(8))
        );
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

    public ITimeRangeSupplier getDefaultGlobalTimeRangeSupplier() {
        return new ITimeRangeSupplier() {
            private LocalDateTime globalMin = null;

            @Override
            public String getName() {
                return "最近1年";
            }

            @Override
            public LocalDateTime getMin() {
                if (globalMin == null) {
                    globalMin = LocalDateTime.MIN;
                }
                return globalMin;
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            }
        };
    }

    public TimeRangeDialog build() {
        if (this.timeRangeSupplier == null) this.timeRangeSupplier = TimeRangeSupplier.lastDays(30);
        if (this.globalTimeRangeSupplier == null) this.globalTimeRangeSupplier = getDefaultGlobalTimeRangeSupplier();
        if (initTimeRangeSupplier == null) defaultInitTimeRangeSupplier();

        minDatetimePicker.setMin(globalTimeRangeSupplier.getMin());
        maxDatetimePicker.setMin(globalTimeRangeSupplier.getMin());
        minDatetimePicker.setMax(globalTimeRangeSupplier.getMax());
        maxDatetimePicker.setMax(globalTimeRangeSupplier.getMax());

        minDatetimePicker.addValueChangeListener(e -> {
            LocalDateTime minDateTime = e.getValue();
            if (minDateTime == null) return;
            LocalDateTime maxDateTime = minDateTime.plusDays(defaultMaxDurationDay);
            maxDatetimePicker.getOptionalValue().ifPresent(value -> {
                if (maxDateTime.isBefore(value)) {
                    maxDatetimePicker.setValue(maxDateTime);
                }
            });
        });

        maxDatetimePicker.addValueChangeListener(e -> {
            LocalDateTime maxDateTime = e.getValue();
            if (maxDateTime == null) return;
            LocalDateTime minDateTime = maxDateTime.minusDays(defaultMaxDurationDay);
            minDatetimePicker.getOptionalValue().ifPresent(value -> {
                if (minDateTime.isAfter(value)) {
                    minDatetimePicker.setValue(minDateTime);
                }
            });
        });

        FormLayout appointForm = new FormLayoutFactory().visible(false).style("padding-top", "1em")
                .formItem(minDatetimePicker, "开始时间：").formItem(maxDatetimePicker, "结束时间：")
                .widthFull().componentFullWidth().formItemAlignEnd().formItemAlignVerticalCenter().get();

        content(createTimeSelector(appointForm), appointForm);

        AtomicReference<ITimeRangeSupplier> lastSelectRangeSupplier = new AtomicReference<>(timeRangeSupplier);

        addButton(FluentButton.cancelButton().asFactory().clickListener(event -> {
            close();
            ITimeRangeSupplier timeRangeSupplier = lastSelectRangeSupplier.get();
            minDatetimePicker.setValue(timeRangeSupplier.getMin());
            maxDatetimePicker.setValue(timeRangeSupplier.getMax());
            selectButton(timeRangeSupplier.getName());
            appointForm.setVisible("自定义".equals(timeRangeSupplier.getName()));
        }).get());
        addButton(new FluentButton(VaadinIcon.CHECK, "确定", this::processSave).primary());

        title("选择时间").width("720px", "720px", "90vw").setMinHeight("350px");

        addSaveListeners(e -> doMemory());
        addSaveListeners(e -> lastSelectRangeSupplier.set(getTimeRangeSupplier()));
        doRestore();

        refreshStatusField();

        return this;
    }

    public TimeRangeDialog write(QueryParameterBuilder builder) {
        Function<LocalDateTime, Long> function = d -> d.toEpochSecond(ZoneOffset.ofHours(8));
        builder.add("minDatetime", function.apply(getMin()));
        builder.add("maxDatetime", function.apply(getMax()));
        builder.add("time-range", URLEncoder.encode(getTimeRangeSupplier().getName(), StandardCharsets.UTF_8));
        return this;
    }

    public TimeRangeDialog read(QueryParameterUtil util) {
        if (util.decode("time-range") != null || (util.get("minDatetime") != null && util.get("maxDatetime") != null)) {
            setValue(Optional.ofNullable(util.decode("time-range"))
                    .map(timeRange -> "自定义".equals(timeRange) ? null : timeRange)
                    .map(timeRangeSupplierMap::get).orElse(createTimeRangeSupplier(util)));
        }
        refreshStatusField();
        return this;
    }

    public TimeRangeDialog setValue(ITimeRangeSupplier timeRangeSupplier) {
        if (timeRangeSupplier == null) return this;
        this.timeRangeSupplier = timeRangeSupplier;
        refreshStatusField();
        selectButton(timeRangeSupplier.getName());
        return this;
    }

    public TimeRangeDialog setValue(String timeRangeSupplier) {
        try {
            timeRangeSupplier = URLDecoder.decode(timeRangeSupplier, StandardCharsets.UTF_8);
        } catch (Exception ignore) {
        }
        return this.setValue(timeRangeSupplierMap.get(timeRangeSupplier));
    }

    private void selectButton(String name) {
        buttonMap.values().forEach(a -> a.removeThemeVariants(LUMO_PRIMARY));
        Optional.ofNullable(buttonMap.getOrDefault(name, buttonMap.get("自定义")))
                .ifPresent(a -> a.addThemeVariants(LUMO_PRIMARY));
    }

    public TimeRangeDialog today() {
        this.timeRangeSupplier = TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX), "今天");
        minDatetimePicker.setValue(timeRangeSupplier.getMin());
        maxDatetimePicker.setValue(timeRangeSupplier.getMax());
        refreshStatusField();
        return this;
    }

    public TimeRangeDialog recentOneDay() {
        Optional.ofNullable(buttonMap.get("最近一天")).ifPresent(Button::clickInClient);
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
        return extend("minDatetime", "maxDatetime", parameterMap, header, saveListener);
    }

    public TimeRangeDialog extend(String minParameterName, String maxParameterName, Map<String,
            Supplier<Object>> parameterMap, ToolBar header, Consumer<TimeRangeDialog> saveListener) {
        parameterMap.put(minParameterName, this::getMin);
        parameterMap.put(maxParameterName, this::getMax);
        this.addSaveListeners(saveListener);
        header.right(statusField);
        doRestore();
        return this;
    }

    private HorizontalLayout createTimeSelector(FormLayout appointForm) {
        HorizontalLayout main = new HorizontalLayoutFactory().flexWrap().widthFull()
                .justifyContentModeEnd().alignItemsBaseline().displayFlex().get();
        for (ITimeRangeSupplier iTimeRangeSupplier : initTimeRangeSupplier) {
            main.add(createRecently(appointForm, iTimeRangeSupplier));
        }

        main.add(createAppointButton(appointForm));
        return main;
    }

    private void doMemory() {
        if (!memoryMode) return;

        VaadinSession session = VaadinSession.getCurrent();
        session.setAttribute(ITimeRangeSupplier.class, timeRangeSupplier);
        session.setAttribute(getClass().getName() + ".minDatetime", getMin());
        session.setAttribute(getClass().getName() + ".maxDatetime", getMax());
        buttonMap.values().stream().filter(b -> b.hasThemeName(LUMO_PRIMARY.getVariantName())).findFirst()
                .ifPresent(b -> session.setAttribute(getClass().getName() + ".select.button", b.getText()));
    }

    private void doRestore() {
        if (!memoryMode) return;
        VaadinSession session = VaadinSession.getCurrent();

        ITimeRangeSupplier supplier = session.getAttribute(ITimeRangeSupplier.class);
        if (supplier == null) return;

        if ("自定义".equals(supplier.getName())) {
            Optional.ofNullable(session.getAttribute(getClass().getName() + ".minDatetime"))
                    .filter(LocalDateTime.class::isInstance).map(LocalDateTime.class::cast)
                    .ifPresent(minDatetimePicker::setValue);
            Optional.ofNullable(session.getAttribute(getClass().getName() + ".maxDatetime"))
                    .filter(LocalDateTime.class::isInstance).map(LocalDateTime.class::cast)
                    .ifPresent(maxDatetimePicker::setValue);
        }

        this.timeRangeSupplier = supplier;
        Optional.ofNullable(buttonMap.get(supplier.getName())).ifPresent(Button::clickInClient);
    }

    private ITimeRangeSupplier createAppointTimeRangeSupplier() {
        return new TimeRangeSupplier(minDatetimePicker::getValue, maxDatetimePicker::getValue);
    }

    private Button createRecently(FormLayout formLayout, ITimeRangeSupplier timeRangeSupplier) {
        String timeRangeSupplierName = timeRangeSupplier.getName();
        Button button = createButton(timeRangeSupplierName);

        button.addClickListener(e -> {
            setTimeRangeSupplier(timeRangeSupplier);
            formLayout.setVisible(false);
        });
        timeRangeSupplierMap.put(timeRangeSupplierName, timeRangeSupplier);
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
        timeRangeSupplierMap.put("自定义", timeRangeSupplier);
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

    public boolean isMemoryMode() {
        return memoryMode;
    }

    public TimeRangeDialog memoryMode(boolean memoryMode) {
        this.memoryMode = memoryMode;
        return this;
    }

    public TimeRangeDialog memoryMode() {
        this.memoryMode = true;
        return this;
    }

    @Override
    public String getName() {
        return timeRangeSupplier.getName();
    }

    @Override
    public LocalDateTime getMin() {
        LocalDateTime t0 = getMin0();
        LocalDateTime t1 = getMax0();
        return t0.isBefore(t1) ? t0 : t1;
    }

    private LocalDateTime getMin0() {
        LocalDateTime min = globalTimeRangeSupplier.getMin();
        LocalDateTime user = timeRangeSupplier.getMin();
        return min.isAfter(user) ? min : user;
    }

    @Override
    public LocalDateTime getMax() {
        LocalDateTime t0 = getMin0();
        LocalDateTime t1 = getMax0();
        return t0.isAfter(t1) ? t0 : t1;
    }

    private LocalDateTime getMax0() {
        LocalDateTime max = globalTimeRangeSupplier.getMax();
        LocalDateTime user = timeRangeSupplier.getMax();
        return max.isBefore(user) ? max : user;
    }

    @Override
    public String getLabel() {
        return timeRangeSupplier.getLabel();
    }

    @Override
    public List<Consumer<TimeRangeDialog>> getSaveListeners() {
        return saveListeners;
    }

    public ITimeRangeSupplier getTimeRangeSupplier() {
        return timeRangeSupplier;
    }
}
