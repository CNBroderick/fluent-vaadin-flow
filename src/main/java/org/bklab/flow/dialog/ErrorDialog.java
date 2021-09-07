/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-09-07 16:41:12
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.dialog.ErrorDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.text.ClipboardHelper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Optional;

public class ErrorDialog extends MessageDialog {

    private final Button copyButton;

    private String message;
    private String detail;

    {
        Icon icon = VaadinIcon.CLOSE_CIRCLE_O.create();
        icon.getStyle().set("color", "var(--lumo-error-color-50pct)");
        icon(icon);
        copyButton = new ButtonFactory("复制详情").lumoSmall().lumoTertiaryInline().get();
        getMessage().getElement().getStyle().set("overflow-y", "auto").set("max-height", "30vh");
        setDraggable(true);
    }

    public ErrorDialog() {
    }

    public ErrorDialog(Throwable throwable) {
        this(Optional.ofNullable(throwable.getLocalizedMessage()).orElse(
                Optional.ofNullable(throwable.getMessage()).orElse(toString(throwable))
        ), toString(throwable));
    }

    public ErrorDialog(String message) {
        this(message, (String) null);
    }

    public ErrorDialog(String message, Throwable throwable) {
        this(message, toString(throwable));
    }

    public ErrorDialog(String message, String detail) {
        message(message);
        if (detail != null) addCopyButton(detail);
        this.message = message;
        this.detail = detail;
    }

    public ErrorDialog(String... message) {
        message(message);
        addCopyButton(String.join("\n\t", message));
    }

    public ErrorDialog(Collection<String> message) {
        message(message);
        addCopyButton(String.join("\n\t", message));
    }

    private void addCopyButton(String message) {
        ClipboardHelper.getInstance().extend(copyButton, message);
        getBottom().right(copyButton);
    }

    private static String toString(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public ErrorDialog noCopyButton() {
        copyButton.setVisible(false);
        return this;
    }

    public ErrorDialog hasCopyButton() {
        copyButton.setVisible(true);
        return this;
    }

    @Override
    public void open() {
        UI ui = UI.getCurrent();
        if (ui == null) {
            System.out.println("ui instance not available. message: \n" + message + "\ndetail:\n" + detail);
            return;
        }

        super.open();
    }
}
