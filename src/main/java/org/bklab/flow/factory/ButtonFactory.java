/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 17:16:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.ButtonFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinButtonFactory;
import org.bklab.flow.base.HasEnabledFactory;
import org.bklab.flow.base.HasSizeFactory;

public class ButtonFactory extends FlowFactory<Button, ButtonFactory> implements
        GeneratedVaadinButtonFactory<Button, ButtonFactory>,
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

    public ButtonFactory(String text, VaadinIcon icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(new Button(text, icon.create(), clickListener));
    }

    public ButtonFactory(String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(new Button(text, clickListener));
    }

    public ButtonFactory(String text, Component icon) {
        this(new Button(text, icon));
    }

    public ButtonFactory(String text, VaadinIcon icon) {
        this(new Button(text, icon.create()));
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

    public ButtonFactory icon(VaadinIcon icon) {
        get().setIcon(icon.create());
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
