package org.bklab.flow.components.selector;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@CssImport("./styles/components/selector/button-selector.css")
public class ButtonSelector extends Span {

    private final static String CLASS_NAME = "button-selector";
    private static final Logger logger = LoggerFactory.getLogger(ButtonSelector.class);
    private final Map<String, SelectButton> map = new LinkedHashMap<>();

    public ButtonSelector() {
        addClassName(CLASS_NAME + "__container");
    }

    public Optional<SelectButton> get(String name) {
        return Optional.ofNullable(map.get(name));
    }

    public ButtonSelector add(String name, ComponentEventListener<ClickEvent<Button>> listener) {
        addButton(name, listener);
        return this;
    }

    public SelectButton addButton(String name, ComponentEventListener<ClickEvent<Button>> listener) {
        SelectButton selectButton = new SelectButton();
        selectButton.setText(name);
        selectButton.addClickListener(e -> {
            map.values().forEach(SelectButton::inactive);
            selectButton.active();
        });
        selectButton.addClickListener(listener);
        add(selectButton);
        map.put(name, selectButton);
        return selectButton;
    }

    public ButtonSelector active(int index) {
        try {
            new ArrayList<>(map.values()).get(index).active();
        } catch (Exception error) {
            logger.error("越界的索引" + index, error);
        }
        return this;
    }

    public ButtonSelector activeFirst() {
        return active(0);
    }

    public ButtonSelector active(String name) {
        get(name).ifPresent(SelectButton::active);
        return this;
    }


}
