package org.bklab.flow.components.button;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;

@Tag("fluent-button")
@CssImport("./styles/components/button/fluent-button.css")
public class FluentButton extends Button {

    private final static String CLASS_NAME = "fluent-button";

    {
        addClassNames(CLASS_NAME);
    }

    public FluentButton(String text) {
        super(text);
    }

    public FluentButton(Component icon) {
        super(icon);
    }

    public FluentButton(String text, Component icon) {
        super(text, icon);
    }

    public FluentButton(String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(text, clickListener);
    }

    public FluentButton(Component icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(icon, clickListener);
    }

    public FluentButton(String text, Component icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(text, icon, clickListener);
    }

    public FluentButton primary() {
        addClassNames(CLASS_NAME + "__primary");
        return this;
    }

    public FluentButton error() {
        addClassNames(CLASS_NAME + "__error");
        return this;
    }

    public FluentButton dashed() {
        addClassNames(CLASS_NAME + "__dashed");
        return this;
    }
}
