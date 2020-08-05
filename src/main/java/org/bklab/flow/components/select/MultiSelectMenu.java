package org.bklab.flow.components.select;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import org.bklab.flow.factory.CheckboxFactory;

import java.util.*;
import java.util.function.Function;

public class MultiSelectMenu<T> extends MenuBar {

    private final Map<T, Checkbox> checkboxMap = new LinkedHashMap<>();
    private final MenuItem root;
    private Function<T, String> labelGenerator = Objects::toString;

    public MultiSelectMenu() {
        root = addItem("请选择");
    }

    public void labelGenerator(Function<T, String> captionGenerator) {
        this.labelGenerator = captionGenerator;
    }

    @SafeVarargs
    public final MultiSelectMenu<T> items(T... items) {
        removeAll();
        checkboxMap.clear();
        for (T item : items) {
            addItem(item);
        }
        return this;
    }

    public MultiSelectMenu<T> items(Set<T> items) {
        removeAll();
        checkboxMap.clear();
        items.forEach(this::addItem);
        return this;
    }

    private void addItem(T item) {
        Checkbox checkbox = new CheckboxFactory().labelAsHtml(labelGenerator.apply(item))
                .ariaLabel(labelGenerator.apply(item)).get();
        root.add(checkbox);
        checkboxMap.put(item, checkbox);
    }

    public Set<T> getSelectItems() {
        Set<T> set = new LinkedHashSet<>();
        checkboxMap.forEach((k, v) -> Optional.of(v)
                .filter(Checkbox::getValue).ifPresent(a -> set.add(k)));
        return set;
    }
}
