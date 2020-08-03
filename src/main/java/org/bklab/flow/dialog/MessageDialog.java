/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-06 16:22:38
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.dialog.MessageDialog
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.IFlowFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.HorizontalLayoutFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.layout.LmrHorizontalLayout;
import org.bklab.flow.layout.TmbVerticalLayout;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

public class MessageDialog extends Dialog implements IFlowFactory<MessageDialog>, HasSizeFactory<MessageDialog, MessageDialog> {

    private final Button confirm;
    private final LmrHorizontalLayout bottom;
    private Icon icon;
    private String header;
    private final Span message = new Span();

    public MessageDialog() {
        setMinHeight("185px");
        setWidth("520px");
        setMaxWidth("90vw");
        this.confirm = new ButtonFactory().text("确定").clickListener(e -> close()).lumoSmall().lumoPrimary().get();
        this.bottom = new LmrHorizontalLayout();
    }

    public Button getConfirm() {
        return confirm;
    }

    public LmrHorizontalLayout getBottom() {
        return bottom;
    }

    public MessageDialog icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public MessageDialog header(String header) {
        this.header = header;
        return this;
    }

    public MessageDialog message(String message) {
        this.message.add(Optional.ofNullable(message).orElse(""));
        return this;
    }

    public MessageDialog message(String... message) {
        Div div = new Div();
        for (String s : message) {
            div.add(new Html("<div>" + Optional.ofNullable(s).orElse("") + "</div>"));
        }
        this.message.add(div);
        return this;
    }

    public MessageDialog message(Collection<String> message) {
        Div div = new Div();
        for (String s : message) {
            div.add(new Html("<div>" + Optional.ofNullable(s).orElse("") + "</div>"));
        }
        this.message.add(div);
        return this;
    }

    public MessageDialog content(Component content) {
        if (content != null) this.message.add(content);
        return this;
    }

    public MessageDialog forWarning() {
        icon = VaadinIcon.INFO_CIRCLE_O.create();
        icon.getStyle().set("color", "#fdd68a");
        return this;
    }

    public MessageDialog forSuccess() {
        icon = VaadinIcon.CHECK_CIRCLE_O.create();
        icon.getStyle().set("color", "var(--lumo-success-color-50pct)");
        return this;
    }

    public MessageDialog build() {
        removeAll();

        if (icon == null) {
            icon = VaadinIcon.INFO_CIRCLE_O.create();
            icon.getStyle().set("color", "var(--lumo-primary-color-50pct)");
        }

        icon.getStyle().set("min-width", "1.8em");

        bottom.right(confirm);
        if (header == null) return buildNoHeader();
        Span header = new Span(this.header);
        header.getStyle().set("font-weight", "600").set("font-size", "var(--lumo-font-size-m)");
        message.getStyle().set("font-size", "var(--lumo-font-size-s)").set("padding-left", "3.2em")
                .set("word-break", "break-word").set("display", "flex").set("align-self", "flex-start")
                .set("overflow-wrap", "anywhere");

        TmbVerticalLayout main = new TmbVerticalLayout().noBorder();
        main.top(new HorizontalLayoutFactory(icon, header).expand(header).widthFull().get());
        main.middle(message);
        main.bottom(bottom);
        main.getStyle().set("padding", "1em 1em 1em 0");
        add(main);

        return this;
    }

    public MessageDialog extendConfirmButton(Consumer<Button> consumer) {
        consumer.accept(confirm);
        return this;
    }

    private MessageDialog buildNoHeader() {
        message.getStyle().set("font-size", "var(--lumo-font-size-s)").set("display", "flex").set("align-self", "flex-start").set("word-break", "break-word").set("overflow-wrap", "anywhere");
        HorizontalLayout content = new HorizontalLayoutFactory(icon, message).expand(message).widthFull().get();
        VerticalLayout main = new VerticalLayoutFactory(content, bottom).expand(content).widthFull().get();
        main.getStyle().set("padding", "1.5em 1.5em 1em 1.5em");
        add(main);

        return this;
    }

    @Override
    public MessageDialog get() {
        return this;
    }
}
