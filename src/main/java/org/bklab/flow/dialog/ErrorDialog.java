/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-01 19:23:03
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.dialog.ErrorDialog
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.NotificationFactory;
import org.vaadin.olli.ClipboardHelper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Optional;

public class ErrorDialog extends MessageDialog {

    private final Button copyButton;

    {
        Icon icon = VaadinIcon.CLOSE_CIRCLE_O.create();
        icon.getStyle().set("color", "var(--lumo-error-color-50pct)");
        icon(icon);
        copyButton = new ButtonFactory("复制详情").lumoSmall().lumoTertiaryInline().get();
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
        if (detail != null) addCopyButton(message);
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
        copyButton.addClickListener(e -> new NotificationFactory("复制成功")
                .positionTopEnd().duration(1500).lumoSuccess().open()
        );
        getBottom().right(new ClipboardHelper(message, copyButton));
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
}
