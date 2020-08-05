package org.bklab.flow.components.select;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.function.SerializableFunction;
import org.bklab.flow.factory.ButtonFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MultiSelectBox<T> extends TextField {
    private final MultiSelectListBox<T> multiSelectListBox = new MultiSelectListBox<>();
    private String placeholder = "请选择";
    private SerializableFunction<T, String> itemLabelGenerator = String::valueOf;

    public MultiSelectBox() {
        setWidthFull();
        ContextMenu contextMenu = new ContextMenu(this);
        contextMenu.setOpenOnClick(true);
        contextMenu.add(multiSelectListBox);
        setReadOnly(true);
        multiSelectListBox.addSelectionListener(multiSelectionEvent -> {
            int a = multiSelectionEvent.getValue().size();
            getElement().setAttribute("title", a > 0 ? multiSelectionEvent.getValue().stream().map(itemLabelGenerator).collect(Collectors.joining(",")) : placeholder);
            setValue(a > 0 ? a + "项选择" : placeholder);
        });

        addThemeVariants(TextFieldVariant.LUMO_SMALL);
        addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        addToSuffix(new ButtonFactory().clickListener(buttonClickEvent -> {
            buttonClickEvent.getSource().setIcon(contextMenu.isOpened()
                    ? VaadinIcon.CHEVRON_UP_SMALL.create() : VaadinIcon.CHEVRON_DOWN_SMALL.create());
            contextMenu.setVisible(!contextMenu.isOpened());
        }).icon(VaadinIcon.CHEVRON_DOWN_SMALL).lumoSmall().lumoTertiaryInline().get());
    }

    public MultiSelectBox<T> itemLabelGenerator(SerializableFunction<T, String> itemLabelGenerator) {
        this.itemLabelGenerator = itemLabelGenerator;
        multiSelectListBox.setRenderer(new TextRenderer<>(itemLabelGenerator::apply));
        return this;
    }

    public MultiSelectBox<T> itemComponentGenerator(SerializableFunction<T, Component> itemComponentGenerator) {
        multiSelectListBox.setRenderer(new ComponentRenderer<>(itemComponentGenerator));
        return this;
    }

    @SafeVarargs
    public final MultiSelectBox<T> items(T... items) {
        multiSelectListBox.setItems(items);
        return this;
    }

    public MultiSelectBox<T> items(Collection<T> items) {
        multiSelectListBox.setItems(items);
        return this;
    }

    public MultiSelectBox<T> items(Set<T> items) {
        multiSelectListBox.setItems(items);
        return this;
    }

    public MultiSelectBox<T> values(Set<T> items) {
        multiSelectListBox.setValue(items);
        return this;
    }

    public MultiSelectBox<T> values(Collection<T> items) {
        multiSelectListBox.setValue(new LinkedHashSet<>(items));
        return this;
    }

    @SafeVarargs
    public final MultiSelectBox<T> values(T... items) {
        multiSelectListBox.setValue(new LinkedHashSet<>(Arrays.asList(items)));
        return this;
    }

    public MultiSelectBox<T> peekSelectBox(Consumer<MultiSelectListBox<T>> consumer) {
        consumer.accept(multiSelectListBox);
        return this;
    }

    public MultiSelectBox<T> placeholder(String placeholder) {
        this.placeholder = placeholder;
        setPlaceholder(placeholder);
        setValue(placeholder);
        return this;
    }

    public Set<T> getValues() {
        return multiSelectListBox.getSelectedItems();
    }

    public MultiSelectListBox<T> getMultiSelectListBox() {
        return multiSelectListBox;
    }
}
