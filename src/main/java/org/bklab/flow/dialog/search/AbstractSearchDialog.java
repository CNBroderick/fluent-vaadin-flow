package org.bklab.flow.dialog.search;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.textfield.KeywordField;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.FormLayoutFactory;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class AbstractSearchDialog<E extends AbstractSearchDialog<E>> extends ModalDialog {

    protected final List<Consumer<Map<String, Object>>> saveListeners = new ArrayList<>();
    protected final Map<String, Supplier<Object>> parameterMap = new LinkedHashMap<>();
    protected final List<ClearParameterListener> clearParameterConsumer = new ArrayList<>();
    protected final FormLayout formLayout = new FormLayout();
    protected final List<Supplier<String>> statusBuilder = new ArrayList<>();
    protected final AdvanceSearchField<E> advanceSearchField = new AdvanceSearchField<>((E) this);

    public AbstractSearchDialog() {
        build();

        advanceSearchField.getClearButton().addClickListener(e -> {
            clearParameterConsumer.forEach(ClearParameterListener::clear);
            callSaveListeners(new LinkedHashMap<>());
        });

        title("高级搜索").content(formLayout).width("600px", "600px", "80vw");
        addCancelButton();
        footerRight(new FluentButton(VaadinIcon.SEARCH, "搜索").primary().asFactory().clickListener(e -> search()).get());

        new FormLayoutFactory(formLayout).warpWhenOverflow().formItemAlignEnd().widthFull().get();
    }

    protected abstract void build();

    protected void search() {
        Map<String, Object> map = getConditions();
        String status = statusBuilder.stream().map(Supplier::get).filter(Objects::nonNull).collect(Collectors.joining(", \n"));
        advanceSearchField.setValue(status);
        Tooltips.getCurrent().setTooltip(advanceSearchField, status);
        callSaveListeners(map);
        close();
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
        parameterMap.put(name, () -> {
            String value = hasValue.getValue();
            return value == null || value.isBlank() ? null : value.trim();
        });
        statusBuilder.add(() -> Optional.ofNullable(hasValue.getValue()).map(s -> s.isBlank() ? null : s.strip()).orElse(null));
        clearParameterConsumer.add(hasValue::clear);
    }

    protected <T> void register(String name, HasValue<?, T> hasValue, Function<T, String> toStatusLabel) {
        parameterMap.put(name, () -> {
            T value = hasValue.getValue();
            if (value == null) return null;
            if (value instanceof String) return ((String) value).trim().isEmpty() ? null : ((String) value).trim();
            return value;
        });
        statusBuilder.add(() -> Optional.ofNullable(hasValue.getValue()).map(toStatusLabel).map(a -> name + ": " + a).orElse(null));
        clearParameterConsumer.add(hasValue::clear);
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

    public void callSaveListeners(Map<String, Object> object) {
        this.getSaveListeners().forEach((s) -> {
            s.accept(object);
        });
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

    public interface ClearParameterListener {
        void clear();
    }
}
