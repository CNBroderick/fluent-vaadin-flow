package org.bklab.flow.dialog.search;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableFunction;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.base.HasReturnThis;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.range.LocalDateRangeComboHelper;
import org.bklab.flow.components.range.LocalDateTimeRangeComboHelper;
import org.bklab.flow.components.range.NumberRangeTextFieldHelper;
import org.bklab.flow.components.textfield.KeywordField;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.FormLayoutFactory;
import org.bklab.flow.factory.RadioButtonGroupFactory;
import org.bklab.flow.factory.TextFieldFactory;
import org.bklab.flow.layout.ToolBar;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class AbstractSearchDialog<E extends AbstractSearchDialog<E>> extends ModalDialog implements HasReturnThis<E> {

    protected final List<Consumer<Map<String, Object>>> saveListeners = new ArrayList<>();
    protected final Map<String, Supplier<Object>> parameterMap = new LinkedHashMap<>();
    protected final List<ClearParameterListener> clearParameterConsumer = new ArrayList<>();
    protected final FormLayout formLayout = new FormLayout();
    protected final List<Supplier<String>> statusBuilder = new ArrayList<>();
    protected final AdvanceSearchField<E> advanceSearchField = new AdvanceSearchField<>((E) this);
    protected final FluentButton searchButton = new FluentButton(VaadinIcon.SEARCH, "搜索").primary().clickListener(e -> search());
    protected final Map<String, HasValue<?, ?>> componentMap = new LinkedHashMap<>();

    protected final Object[] args;

    {
        title("高级搜索").content(formLayout).width("600px", "600px", "80vw");
        addCancelButton();
        footerRight(searchButton);

        advanceSearchField.getClearButton().addClickListener(e -> {
            clearParameterConsumer.forEach(ClearParameterListener::clear);
            callSaveListeners(getConditions());
            refreshSearchFieldValue();
            Tooltips.getCurrent().removeTooltip(e.getSource());
        });
    }

    public AbstractSearchDialog() {
        this(new Object[]{});
    }

    public AbstractSearchDialog(Object... args) {
        this.args = args;
        if(args == null || args.length < 1) init();
    }

    public <T> T getArgs(int index) {
        return (T) args[index];
    }

    protected E init() {
        build();
        new FormLayoutFactory(formLayout).warpWhenOverflow().formItemAlignEnd().componentFullWidth().widthFull().get();
        refreshSearchFieldValue();
        return thisObject();
    }

    protected abstract void build();

    protected void search() {
        refreshSearchFieldValue();
        callSaveListeners(getConditions());
        close();
    }

    public void refreshSearchFieldValue() {
        String status = createStatus();
        if (status != null && !status.isBlank()) {
            advanceSearchField.setValue(status);
            Tooltips.getCurrent().setTooltip(advanceSearchField, status);
            advanceSearchField.getClearButton().setVisible(true);
        } else {
            advanceSearchField.clear();
            Tooltips.getCurrent().removeTooltip(advanceSearchField);
            advanceSearchField.getClearButton().setVisible(false);
        }
    }

    public String createStatus() {
        List<String> status = statusBuilder.stream().map(Supplier::get).filter(Objects::nonNull).filter(s -> !s.isBlank()).collect(Collectors.toList());
        return status.isEmpty() ? null : String.join(", \n", status);
    }

    public Map<String, Object> getConditions() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        parameterMap.forEach((k, p) -> Optional.ofNullable(p.get()).ifPresent(v -> map.put(k, v)));
        return map;
    }

    public AdvanceSearchField<E> getAdvanceSearchField() {
        return advanceSearchField;
    }

    protected void createKeyword() {
        formLayout.addFormItem(new KeywordField((textField, componentEvent) -> {
        }).asFactory().widthFull().peek(t -> register("关键字", t)).get(), "关键字：");
    }


    protected void register(String name, HasValue<?, String> hasValue) {
        register(name, name, hasValue);
    }

    protected void register(String caption, String name, HasValue<?, String> hasValue) {
        parameterMap.put(name, () -> {
            String value = hasValue.getValue();
            return value == null || value.isBlank() ? null : value.trim();
        });
        statusBuilder.add(() -> Optional.ofNullable(hasValue.getValue()).map(s -> s.isBlank() ? null : s.strip())
                .map(s -> caption + ": " + s).orElse(null));
        componentMap.put(name, hasValue);
        clearParameterConsumer.add(hasValue::clear);
    }

    protected <T> void register(String name, HasValue<?, T> hasValue, Function<T, String> toStatusLabel) {
        this.register(name, name, hasValue, toStatusLabel);
    }

    protected <T> void register(String caption, String name, HasValue<?, T> hasValue, Function<T, String> toStatusLabel) {
        parameterMap.put(name, () -> {
            T value = hasValue.getValue();
            if (value == null) return null;
            if (value instanceof String) return ((String) value).trim().isEmpty() ? null : ((String) value).trim();
            if (value instanceof Collection<?> && ((Collection<?>) value).isEmpty()) return null;
            return value;
        });
        componentMap.put(name, hasValue);
        addStatusBuilder(caption, hasValue, toStatusLabel);
        clearParameterConsumer.add(hasValue::clear);
    }

    protected <T, V> void register(String caption, String name, HasValue<?, T> hasValue, Function<T, V> toValueFunction, Function<T, String> toStatusLabel) {
        parameterMap.put(name, () -> Optional.ofNullable(hasValue.getValue()).map(toValueFunction).orElse(null));
        addStatusBuilder(caption, hasValue, toStatusLabel);
        componentMap.put(name, hasValue);
        clearParameterConsumer.add(hasValue::clear);
    }

    private <T, V> void addStatusBuilder(String caption, HasValue<?, T> hasValue, Function<T, String> toStatusLabel) {
        statusBuilder.add(() -> Optional.ofNullable(hasValue.getValue()).map(value -> {
            if (value instanceof String) return ((String) value).trim().isEmpty() ? null : ((String) value).trim();
            if (value instanceof Collection<?> && ((Collection<?>) value).isEmpty()) return null;
            return toStatusLabel.apply(value);
        }).map(a -> caption + ": " + a).orElse(null));
    }

    protected <T> void register(String name, String minName, String maxName, HasValue<?, T> min, HasValue<?, T> max, Function<T, String> toStatusLabel) {
        Function<T, T> checker = t -> Optional.ofNullable(t)
                .map(a -> {
                    if (a instanceof String) {
                        String trim = ((String) a).trim();
                        //noinspection unchecked
                        return trim.isEmpty() ? null : (T) trim;
                    }
                    return a;
                })
                .orElse(null);
        parameterMap.put(minName, () -> checker.apply(min.getValue()));
        parameterMap.put(maxName, () -> checker.apply(max.getValue()));
        componentMap.put(minName, min);
        componentMap.put(maxName, max);

        clearParameterConsumer.add(min::clear);
        clearParameterConsumer.add(max::clear);

        statusBuilder.add(() -> {
            T minValue = min.getValue();
            T maxValue = max.getValue();

            if (minValue == null && maxValue == null) return null;

            if (minValue != null && maxValue != null) {
                return name + ": " + toStatusLabel.apply(minValue) + "-" + toStatusLabel.apply(maxValue);
            }
            if (minValue != null) {
                return name + ": ≥" + toStatusLabel.apply(minValue);
            }

            return name + ": ≤" + toStatusLabel.apply(maxValue);
        });

    }

    public E extend(Map<String, Supplier<Object>> map) {
        map.putAll(parameterMap);
        return (E) this;
    }

    public E extend(Map<String, Supplier<Object>> map, ToolBar header, Consumer<Map<String, Object>> reload) {
        extend(map);
        header.left(advanceSearchField);
        addSaveListeners(reload);
        return returnThis();
    }

    public E returnThis() {
        return (E) this;
    }

    public void callSaveListeners(Map<String, Object> object) {
        this.getSaveListeners().forEach(s -> s.accept(object));
    }

    public E addSaveListeners(Consumer<Map<String, Object>> saveListener) {
        this.getSaveListeners().add(saveListener);
        return (E) this;
    }

    public boolean removeSaveListeners(Consumer<Map<String, Object>> saveListener) {
        return this.getSaveListeners().remove(saveListener);
    }

    public List<Consumer<Map<String, Object>>> getSaveListeners() {
        return saveListeners;
    }

    public <V> E value(String name, V value) {
        getHasValue(name).setValue(value);
        refreshSearchFieldValue();
        advanceSearchField.checkClearButtonVisibility();
        return thisObject();
    }

    public <V> E bind(String name, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<V> value) {
        value.ifPresent(v -> value(name, v));
        return thisObject();
    }

    public E withNumber(String caption, String minName, String maxName, String tooltip) {
        NumberRangeTextFieldHelper helper = new NumberRangeTextFieldHelper().limit(0d, Double.MAX_VALUE);
        helper.add(new FluentButton(VaadinIcon.INFO_CIRCLE_O).link().noPadding().asFactory().tooltip(tooltip).get());
        register(caption, minName, maxName, helper.getMinField(), helper.getMaxField(), Number::toString);
        formLayout.addFormItem(helper, caption + '：');
        return returnThis();
    }

    public E withNumber(String caption, String minName, String maxName) {
        NumberRangeTextFieldHelper helper = new NumberRangeTextFieldHelper().limit(0d, Double.MAX_VALUE);
        register(caption, minName, maxName, helper.getMinField(), helper.getMaxField(), Number::toString);
        formLayout.addFormItem(helper, caption + '：');
        return returnThis();
    }

    public E withDateRange(String caption, String minName, String maxName) {
        LocalDateRangeComboHelper helper = new LocalDateRangeComboHelper();
        register(caption, minName, maxName, helper.getStart(), helper.getEnd(), a -> DateTimeFormatter.ofPattern("yyyy-MM-dd").format(a));
        formLayout.addFormItem(helper, caption + '：');
        return returnThis();
    }

    public E withDateTimeRange(String caption, String minName, String maxName) {
        LocalDateTimeRangeComboHelper helper = new LocalDateTimeRangeComboHelper();
        register(caption, minName, maxName, helper.getStart(), helper.getEnd(), a -> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(a));
        formLayout.addFormItem(helper, caption + '：');
        return returnThis();
    }

    public E withString(String caption, String parameterName) {
        TextField textField = new TextFieldFactory().lumoSmall().widthFull().get();
        register(caption, parameterName, textField);
        formLayout.addFormItem(textField, caption + '：');
        return returnThis();
    }

    public E withString(String caption, String parameterName, String placeholder) {
        TextField textField = new TextFieldFactory().lumoSmall().widthFull().placeholder(placeholder).get();
        register(caption, parameterName, textField);
        formLayout.addFormItem(textField, caption + '：');
        return returnThis();
    }

    public <T> E withBooleanRadio(String caption, String parameterName, String trueCaption, String falseCaption, T trueValue, T falseValue) {
        return withRadio(caption, parameterName,
                new ComponentRenderer<>((SerializableFunction<Boolean, Span>) b -> new Span(b ? trueCaption : falseCaption)),
                b -> b ? trueValue : falseValue,
                b -> b ? trueCaption : falseCaption,
                List.of(true, false)
        );
    }

    public E withStringRadio(String caption, String parameterName, List<String> items) {
        return withRadio(caption, parameterName,
                new ComponentRenderer<>((SerializableFunction<String, Span>) Span::new),
                Function.identity(), Function.identity(), items);
    }

    public <T, V> E withRadio(String caption, String parameterName, ComponentRenderer<? extends Component, T> renderer,
                              Function<T, V> toValueFunction, Function<T, String> toStatusLabel, List<T> items) {
        RadioButtonGroup<T> radioButtonGroup = new RadioButtonGroupFactory<T>().lumoSmall()
                .renderer(renderer).items(items).flexWrap().displayFlex().get();
        register(caption, parameterName, radioButtonGroup, toValueFunction, toStatusLabel);
        formLayout.addFormItem(radioButtonGroup, caption + '：');
        return returnThis();
    }


    public E peek(Consumer<E> consumer) {
        consumer.accept(thisObject());
        return thisObject();
    }

    public <V> HasValue<?, V> getHasValue(String name) {
        return ((HasValue<?, V>) componentMap.get(name));
    }

    public interface ClearParameterListener {
        void clear();
    }
}
