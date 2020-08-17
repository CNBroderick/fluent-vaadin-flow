package org.bklab.flow.textfield;

import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.IronOverlayBuilder;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.factory.ListBoxFactory;
import org.bklab.flow.factory.TextFieldFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class AutoComplete<T> extends Div {
    private final IronOverlay ironOverlay;
    private final TextField textField;
    private final ListBox<T> listBox = new ListBoxFactory<T>().lumoSmall().widthFull().get();
    Function<String, Collection<T>> suggestGenerator;
    private Function<T, String> itemLabelGenerator = Objects::toString;
    private T value;

    {
        this.ironOverlay = IronOverlayBuilder.get().with(listBox)
                .withVerticalAlign(VerticalOrientation.TOP).build();
    }

    public AutoComplete() {
        this(new TextFieldFactory().lumoSmall().widthFull().get());
    }

    public AutoComplete(TextField textField) {
        this.textField = textField;
    }

    public AutoComplete<T> build() {
        add(textField, ironOverlay);
        listBox.addValueChangeListener(this::handleSelectChanged);
        textField.addValueChangeListener(e -> {
            items(suggestGenerator.apply(
                    Optional.ofNullable(e.getValue()).orElse("")
            ));
            ironOverlay.open();
        });
        textField.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        textField.setValueChangeTimeout(500);
        return this;
    }

    public AutoComplete<T> suggestGenerator(Function<String, Collection<T>> suggestGenerator) {
        this.suggestGenerator = suggestGenerator;
        return this;
    }

    private void handleSelectChanged(AbstractField.ComponentValueChangeEvent<ListBox<T>, T> event) {
        T value = event.getValue();
        if (value == null) {
            textField.clear();
            return;
        }
        textField.setValue(itemLabelGenerator.apply(value));
        ironOverlay.close();
        this.value = value;
    }

    @SafeVarargs
    public final AutoComplete<T> items(T... items) {
        this.listBox.setItems(items);
        return this;
    }

    public AutoComplete<T> items(Collection<T> items) {
        this.listBox.setItems(items);
        return this;
    }

    public AutoComplete<T> value(T value) {
        this.value = value;
        if (value == null) {
            textField.clear();
            return this;
        }
        textField.setValue(itemLabelGenerator.apply(value));
        return this;
    }

    public T getValue() {
        return value;
    }

    public AutoComplete<T> itemLabelGenerator(Function<T, String> itemLabelGenerator) {
        this.itemLabelGenerator = itemLabelGenerator;
        return this;
    }

    public IronOverlay getIronOverlay() {
        return ironOverlay;
    }

    public TextField getTextField() {
        return textField;
    }

    public ListBox<T> getListBox() {
        return listBox;
    }
}
