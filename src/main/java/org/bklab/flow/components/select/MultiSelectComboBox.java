package org.bklab.flow.components.select;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.MultiSelect;
import com.vaadin.flow.data.selection.MultiSelectionEvent;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.shared.Registration;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.factory.TextFieldFactory;
import org.bklab.flow.util.lumo.LumoStyles;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Deprecated
public class MultiSelectComboBox<T> extends Div implements MultiSelect<MultiSelectComboBox<T>, T> {

    private final TextField textField = new TextFieldFactory().lumoSmall().readOnly().get();
    private final Div content = new DivFactory().display("block").get();
    private final Set<T> selectItem = new LinkedHashSet<>();
    private final Map<Checkbox, T> checkboxMap = new LinkedHashMap<>();
    private final Map<T, MultiSelectComboBoxItem<T>> multiSelectComboBoxItemMap = new LinkedHashMap<>();


    private final List<MultiSelectionListener<MultiSelectComboBox<T>, T>> multiSelectionListeners = new ArrayList<>();
    private final List<ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<MultiSelectComboBox<T>, Set<T>>>> valueChangeListeners = new ArrayList<>();
    private final AtomicReference<Set<T>> oldValues = new AtomicReference<>(null);
    private SerializableFunction<T, Component> itemComponentGenerator = t -> new Text(String.valueOf(t));
    private SerializableFunction<Set<T>, String> textFieldLabelGenerator = selectItem -> selectItem.isEmpty() ? null : selectItem.size() + "项选择";

    public MultiSelectComboBox() {
        Button dropButton = new ButtonFactory().icon(VaadinIcon.ANGLE_DOWN).lumoTertiaryInline().lumoSmall().lumoIcon().get();
        Button clearButton = new ButtonFactory().icon(VaadinIcon.CLOSE).clickListener(e -> clearSelect()).lumoTertiaryInline().lumoSmall().lumoIcon().get();

        textField.setSuffixComponent(new DivFactory(clearButton, dropButton).displayFlex().get());
        textField.setClearButtonVisible(false);

    }

    public MultiSelectComboBox<T> itemLabelGenerator(SerializableFunction<T, String> itemLabelGenerator) {
        Objects.requireNonNull(itemLabelGenerator, "itemLabelGenerator is null");
        return itemComponentGenerator(t -> new Span(itemLabelGenerator.apply(t)));
    }

    public MultiSelectComboBox<T> itemComponentGenerator(SerializableFunction<T, Component> itemComponentGenerator) {
        this.itemComponentGenerator = Objects.requireNonNull(itemComponentGenerator, "itemComponentGenerator is null");
        multiSelectComboBoxItemMap.forEach((t, m) -> m.reload(itemComponentGenerator));
        return this;
    }

    public MultiSelectComboBox<T> textFieldLabelGenerator(SerializableFunction<Set<T>, String> textFieldLabelGenerator) {
        this.textFieldLabelGenerator = Objects.requireNonNull(textFieldLabelGenerator, "textFieldLabelGenerator is null");
        effectTextFieldLabel();
        return this;
    }

    private void effectTextFieldLabel() {
        String apply = textFieldLabelGenerator.apply(getSelectedItems());
        if (apply == null) textField.clear();
        else textField.setValue(apply);
    }

    public MultiSelectComboBox<T> items(Stream<T> collection) {
        return items(collection.collect(Collectors.toList()));
    }

    @SafeVarargs
    public final MultiSelectComboBox<T> items(T... collection) {
        return items(Arrays.asList(collection));
    }

    public MultiSelectComboBox<T> items(Collection<T> collection) {
        content.removeAll();
        collection.stream().map(this::createCheckbox).forEach(content::add);
        return this;
    }

    private MultiSelectComboBoxItem<T> createCheckbox(T item) {
        MultiSelectComboBoxItem<T> boxItem = new MultiSelectComboBoxItem<>(item, itemComponentGenerator);
        checkboxMap.put(boxItem.checkbox, boxItem.item);
        multiSelectComboBoxItemMap.put(item, boxItem);
        return boxItem;
    }

    private void callSelectChangeListener(boolean userOriginated) {
        MultiSelectionEvent<MultiSelectComboBox<T>, T> selectionEvent
                = new MultiSelectionEvent<>(this, this, getOldValues(), userOriginated);
        multiSelectionListeners.forEach(listener -> listener.selectionChange(selectionEvent));

        AbstractField.ComponentValueChangeEvent<MultiSelectComboBox<T>, Set<T>> changeEvent
                = new AbstractField.ComponentValueChangeEvent<>(this, this, getOldValues(), userOriginated);
        valueChangeListeners.forEach(e -> e.valueChanged(changeEvent));
    }

    public Set<T> getOldValues() {
        if (oldValues.get() == null) oldValues.set(Collections.unmodifiableSet(getSelectedItems()));
        return oldValues.get();
    }

    public void clearSelect() {
        textField.clear();
        checkboxMap.keySet().forEach(HasValue::clear);
    }

    @Override
    public void updateSelection(Set<T> addedItems, Set<T> removedItems) {
        checkboxMap.forEach((c, t) -> {
            if (addedItems.contains(t)) c.setValue(true);
            if (removedItems.contains(t)) c.setValue(false);
        });
    }

    @Override
    public Set<T> getSelectedItems() {
        return checkboxMap.entrySet().stream().map(e -> e.getKey().getValue() ? e.getValue() : null)
                .filter(Objects::nonNull).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Registration addSelectionListener(MultiSelectionListener<MultiSelectComboBox<T>, T> listener) {
        multiSelectionListeners.add(listener);
        return () -> multiSelectionListeners.remove(listener);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<MultiSelectComboBox<T>, Set<T>>> listener) {
        valueChangeListeners.add(listener);
        return () -> valueChangeListeners.remove(listener);
    }

    @Tag("multi-select-combo-box-item")
    private final static class MultiSelectComboBoxItem<T> extends Div {
        private final Checkbox checkbox;
        private final Span span;
        private final T item;

        {
            getElement().getStyle().set("display", "flex");
            addClassName("multi-select-combo-box-item");
        }

        private MultiSelectComboBoxItem(T item, SerializableFunction<T, Component> itemComponentGenerator) {
            Checkbox checkbox = new Checkbox();
            checkbox.setIndeterminate(false);
            checkbox.getElement().setAttribute("theme", "small");
            checkbox.addClassName(LumoStyles.Padding.Right.M);
            Span span = new SpanFactory(itemComponentGenerator.apply(item)).displayFlex().get();

            this.checkbox = checkbox;
            this.span = span;
            this.item = item;
        }

        private MultiSelectComboBoxItem(Checkbox checkbox, Span span, T item) {
            this.checkbox = checkbox;
            this.span = span;
            this.item = item;
        }

        private void reload(SerializableFunction<T, Component> itemComponentGenerator) {
            span.removeAll();
            span.add(itemComponentGenerator.apply(item));
        }

        public boolean isSelect() {
            return checkbox.getValue();
        }

        public void select(boolean select) {
            checkbox.setValue(select);
        }
    }

}
