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
import org.bklab.flow.factory.IconFactory;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.flow.factory.SpanFactory;
import org.vaadin.olli.ClipboardHelper;

@Tag("fluent-menu-item")
@CssImport("./styles/org/bklab/component/menu/fluent-menu-item.css")
@CssImport(id = "vaadin-context-menu-overlay-transparent", value = "./styles/org/bklab/component/menu/vaadin-context-menu-overlay.css", themeFor = "vaadin-context-menu-overlay")
public class FluentMenuItem extends Span {

    private FluentMenuItem() {
    }

    public FluentMenuItem(VaadinIcon icon, String text) {
        this(icon.create(), text);
    }

    public FluentMenuItem(Icon icon, String text) {
        add(icon, new Span(text));
        icon.addClassName("fluent-menu-item-iron-icon");
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

    public static FluentMenuItem forCopy(String text, String content, String successMessage) {
        FluentMenuItem fluentMenuItem = new FluentMenuItem();
        Icon icon = new IconFactory(VaadinIcon.COPY_O).color("#999999").size("var(--lumo-font-size-xs)").padding("0 var(--lumo-space-m)").get();
        fluentMenuItem.add(new ClipboardHelper(content, new SpanFactory(icon, new Span(text)).displayFlex().get()));
        fluentMenuItem.addClickListener(event -> new NotificationFactory(successMessage).lumoSuccess().duration(1000).positionTopEnd().open());
        return fluentMenuItem;
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
        return peek(contextMenu.addItem(this, listener));
    }

    public MenuItem add(ContextMenu contextMenu) {
        return peek(contextMenu.addItem(this));
    }

    private MenuItem peek(MenuItem menuItem) {
        menuItem.getElement().setAttribute("fluent-menu-item", true);
        menuItem.getElement().getClassList().add("fluent-menu-item");
        return menuItem;
    }
}