/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-11-09 10:46:02
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.dialog.AskDialog
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.HorizontalLayoutFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.layout.LmrHorizontalLayout;
import org.bklab.flow.layout.TmbVerticalLayout;

import java.util.function.Consumer;

public class AskDialog extends FluentDialog {
    private final Button confirm;
    private final LmrHorizontalLayout bottom;
    private Icon icon;
    private String header;
    private String message;
    private boolean hasConfirmed = false;

    {
        setDraggable(true);
    }

    public AskDialog(String message, Consumer<Void> whenConfirmed) {
        this.message = message;
        this.confirm = new ButtonFactory().text("确定").clickListener(e -> {
            this.hasConfirmed = true;
            close();
            whenConfirmed.accept(null);
        }).lumoSmall().lumoPrimary().get();
        this.bottom = new LmrHorizontalLayout();
        setWidth("420px");
    }

    public AskDialog(String message, Consumer<Void> whenConfirmed, Consumer<Void> whenConfused) {
        this(message, whenConfirmed);
        addOpenedChangeListener(e -> {
            if (!e.isOpened() && !hasConfirmed) whenConfused.accept(null);
        });
    }

    public Button getConfirm() {
        return confirm;
    }

    public LmrHorizontalLayout getBottom() {
        return bottom;
    }

    public AskDialog icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public AskDialog header(String header) {
        this.header = header;
        return this;
    }

    public AskDialog message(String message) {
        this.message = message;
        return this;
    }

    public AskDialog build() {
        removeAll();

        if (icon == null) {
            icon = VaadinIcon.QUESTION.create();
            icon.getStyle().set("color", "var(--lumo-primary-color-50pct)");
        }

        icon.getStyle().set("min-width", "1.8em");

        bottom.right(confirm, new ButtonFactory().lumoSmall().text("取消").clickListener(x -> close()).get());
        if (header == null) return buildNoHeader();

        Span message = new Span(this.message);
        Span header = new Span(this.header);
        header.getStyle().set("font-weight", "600").set("font-size", "var(--lumo-font-size-m)");
        message.getStyle().set("font-size", "var(--lumo-font-size-s)").set("padding-left", "3.2em");

        TmbVerticalLayout main = new TmbVerticalLayout().noBorder();
        main.top(new HorizontalLayoutFactory(icon, header).expand(header).widthFull().get());
        main.middle(message);
        main.bottom(bottom);
        main.getStyle().set("padding", "1em 1em 1em 0").set("display", "inline-block");
        add(main);

        return this;
    }

    public AskDialog extendConfirmButton(Consumer<Button> consumer) {
        consumer.accept(confirm);
        return this;
    }

    private AskDialog buildNoHeader() {
        Span span = new Span(message);
        span.getStyle().set("font-size", "var(--lumo-font-size-s)");
        HorizontalLayout content = new HorizontalLayoutFactory(icon, span).expand(span).widthFull().get();
        VerticalLayout main = new VerticalLayoutFactory(content, bottom).expand(content).widthFull().get();
        main.getStyle().set("padding", "1.5em 1.5em 1em 1.5em").set("display", "inline-block");
        add(main);

        return this;
    }

    @Override
    public AskDialog get() {
        return this;
    }

}
