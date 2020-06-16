package org.bklab.flow.factory;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasEnabledFactory;
import org.bklab.flow.base.HasSizeFactory;

public class ButtonFactory extends FlowFactory<Button, ButtonFactory> implements
        HasSizeFactory<Button, ButtonFactory>,
        HasEnabledFactory<Button, ButtonFactory> {

    public ButtonFactory(Button component) {
        super(component);
    }

    public ButtonFactory(String text) {
        this(new Button(text));
    }

    public ButtonFactory(String text, Component icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(new Button(text, icon, clickListener));
    }

    public ButtonFactory(String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(new Button(text, clickListener));
    }

    public ButtonFactory(String text, Component icon) {
        this(new Button(text, icon));
    }

    public ButtonFactory() {
        this(new Button());
    }

    public ButtonFactory text(String text) {
        get().setText(text);
        return this;
    }

    public ButtonFactory icon(Component icon) {
        get().setIcon(icon);
        return this;
    }

    public ButtonFactory clickInClient() {
        get().clickInClient();
        return this;
    }

    public ButtonFactory iconAfterText(boolean iconAfterText) {
        get().setIconAfterText(iconAfterText);
        return this;
    }

    public ButtonFactory click() {
        get().click();
        return this;
    }

    public ButtonFactory disableOnClick(boolean disableOnClick) {
        get().setDisableOnClick(disableOnClick);
        return this;
    }

    public ButtonFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

}
