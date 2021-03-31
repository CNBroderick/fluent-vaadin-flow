package org.bklab.flow.components.selector;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@CssImport("./styles/components/selector/button-selector.css")
public class EntityButtonSelector<T> extends Span {


    private final static String CLASS_NAME = "button-selector";
    private static final Logger logger = LoggerFactory.getLogger(ButtonSelector.class);
    private final Map<T, SelectButton> map = new LinkedHashMap<>();
    private final List<BiConsumer<T, ClickEvent<Button>>> selectListeners = new ArrayList<>();
    private Function<T, String> labelGenerator = String::valueOf;

    public EntityButtonSelector() {
        addClassName(CLASS_NAME + "__container");
    }

    public EntityButtonSelector<T> labelGenerator(Function<T, String> labelGenerator) {
        Objects.requireNonNull(labelGenerator);
        this.labelGenerator = labelGenerator;
        this.map.forEach((entity, button) -> button.setText(labelGenerator.apply(entity)));
        return this;
    }

    public Optional<SelectButton> get(T name) {
        return Optional.ofNullable(map.get(name));
    }

    public EntityButtonSelector<T> clear() {
        removeAll();
        map.clear();
        return this;
    }

    public EntityButtonSelector<T> addSelectListeners(BiConsumer<T, ClickEvent<Button>> listener) {
        this.selectListeners.add(listener);
        return this;
    }

    @SafeVarargs
    public final EntityButtonSelector<T> add(T... entities) {
        for (T entity : entities) {
            addButton(entity);
        }
        return this;
    }

    public EntityButtonSelector<T> add(Collection<T> entities) {
        for (T entity : entities) {
            addButton(entity);
        }
        return this;
    }

    public SelectButton addButton(T entity) {
        SelectButton selectButton = new SelectButton();
        selectButton.setText(labelGenerator.apply(entity));
        selectButton.addClickListener(e -> {
            map.values().forEach(SelectButton::inactive);
            selectButton.active();
        });
        selectButton.addClickListener(event -> selectListeners.forEach(listener -> listener.accept(entity, event)));
        add(selectButton);
        map.put(entity, selectButton);
        return selectButton;
    }

    public EntityButtonSelector<T> activeFirst() {
        map.keySet().stream().findFirst().ifPresent(this::active);
        return this;
    }

    public EntityButtonSelector<T> active(T entity) {
        get(entity).ifPresent(selectButton -> {
            map.values().forEach(SelectButton::inactive);
            selectButton.active();
        });
        return this;
    }
}
