/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-04 14:43:37
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.components.menu.FluentMenuItem
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.menu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.function.SerializableConsumer;
import org.bklab.flow.factory.IconFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.text.ClipboardHelper;
import org.bklab.flow.util.lumo.LumoStyles;

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
        icon.addClassName("fluent-menu-item-vaadin-icon");
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

    public static FluentMenuItem forCopy(String text, String content) {
        return createCopy(text).clipboard(content);
    }

    public static FluentMenuItem forCopy(String text, String content, String title) {
        return createCopy(text).clipboard(content, title);
    }

    public static FluentMenuItem forCopy(String text, String content, SerializableConsumer<ClipboardHelper.OnClipboardResult> resultConsumer) {
        return createCopy(text).clipboard(content, resultConsumer);
    }

    public static FluentMenuItem forCopy(String text, String content, String title, SerializableConsumer<ClipboardHelper.OnClipboardResult> resultConsumer) {
        return createCopy(text).clipboard(content, title, resultConsumer);
    }

    private static FluentMenuItem createCopy(String text) {
        FluentMenuItem fluentMenuItem = new FluentMenuItem();
        Icon icon = new IconFactory(VaadinIcon.COPY_O).color("#999999").size("var(--lumo-font-size-xs)").padding("0 var(--lumo-space-m)").get();
        fluentMenuItem.add(new SpanFactory(icon, new Span(text)).displayFlex().get());
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

    public FluentMenuItem clipboard(String content) {
        ClipboardHelper.getInstance().extend(this, content);
        return this;
    }

    public FluentMenuItem clipboard(String content, SerializableConsumer<ClipboardHelper.OnClipboardResult> resultConsumer) {
        ClipboardHelper.getInstance().extend(this, content, resultConsumer);
        return this;
    }

    public FluentMenuItem clipboard(String content, String title) {
        ClipboardHelper.getInstance().extend(this, content, title);
        return this;
    }

    public FluentMenuItem clipboard(String content, String title, SerializableConsumer<ClipboardHelper.OnClipboardResult> resultConsumer) {
        ClipboardHelper.getInstance().extend(this, content, title, resultConsumer);
        return this;
    }

    public FluentMenuItem colorError() {
        return color(LumoStyles.Color.Error._100);
    }

    public FluentMenuItem color(String color) {
        getStyle().set("color", color);
        getChildren().forEach(a -> {
            if (a instanceof HasStyle) ((HasStyle) a).getStyle().set("color", color);
        });
        return this;
    }

    public FluentMenuItem classNames(String... classNames) {
        addClassNames(classNames);
        return this;
    }

    private MenuItem peek(MenuItem menuItem) {
        menuItem.getElement().setAttribute("fluent-menu-item", true);
        menuItem.getElement().getClassList().add("fluent-menu-item");
        return menuItem;
    }
}
