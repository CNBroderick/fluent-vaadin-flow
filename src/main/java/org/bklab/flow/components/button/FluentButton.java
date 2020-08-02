package org.bklab.flow.components.button;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.factory.ButtonFactory;

@Tag("fluent-button")
@CssImport("./styles/org/bklab/component/button/fluent-button.css")
public class FluentButton extends Button {

    private final static String CLASS_NAME = "fluent-button";

    public FluentButton(VaadinIcon icon, String text) {
        super(text, icon.create());
    }

    public static FluentButton saveButton() {
        return new FluentButton(VaadinIcon.CHECK_CIRCLE_O, "保存").primary();
    }

    public static FluentButton updateButton() {
        return new FluentButton(VaadinIcon.EDIT, "修改").primary();
    }

    public static FluentButton closeButton() {
        return new FluentButton(VaadinIcon.CLOSE_SMALL, "关闭");
    }

    public static FluentButton cancelButton() {
        return new FluentButton(VaadinIcon.CLOSE_SMALL, "取消");
    }

    public static FluentButton errorButton() {
        return new FluentButton(VaadinIcon.EXCLAMATION_CIRCLE_O, "错误");
    }


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

    public FluentButton link() {
        addClassNames(CLASS_NAME + "__link");
        return this;
    }

    public ButtonFactory asFactory() {
        return new ButtonFactory(this);
    }
}
