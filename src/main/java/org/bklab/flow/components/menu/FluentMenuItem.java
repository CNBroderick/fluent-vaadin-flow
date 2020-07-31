package org.bklab.flow.components.menu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@Tag("fluent-menu-item")
@CssImport("./styles/components/menu/fluent-menu-item.css")
public class FluentMenuItem extends Span {

    private FluentMenuItem() {
    }

    public FluentMenuItem(VaadinIcon icon, String text) {
        this(icon.create(), text);
    }

    public FluentMenuItem(Icon icon, String text) {
        add(icon, new Span(text));
        icon.getElement().getStyle().set("padding", "0 var(--lumo-space-s)").set("color", "#999999")
                .set("width", "var(--lumo-font-size-s)").set("height", "var(--lumo-font-size-s)");
        getElement().getStyle().set("font-size", "var(--lumo-font-size-s)").set("color", "#191919").set("display", "flex");
    }

    public static void addSeparator(ContextMenu contextMenu) {
        contextMenu.add(new Hr());
    }

    public static FluentMenuItem create(VaadinIcon icon, String text) {
        return new FluentMenuItem(icon, text);
    }

    public static FluentMenuItem forDetail(String text) {
        return new FluentMenuItem(VaadinIcon.BOOK, text);
    }

    public static FluentMenuItem forEdit(String text) {
        return new FluentMenuItem(VaadinIcon.EDIT, text);
    }

    public static FluentMenuItem forDelete(String text) {
        return new FluentMenuItem(VaadinIcon.CLOSE, text);
    }

    public static FluentMenuItem forAdd(String text) {
        return new FluentMenuItem(VaadinIcon.PLUS, text);
    }

    public MenuItem add(ContextMenu contextMenu, ComponentEventListener<ClickEvent<MenuItem>> listener) {
        return contextMenu.addItem(this, listener);
    }

    public MenuItem add(ContextMenu contextMenu) {
        return contextMenu.addItem(this);
    }
}
