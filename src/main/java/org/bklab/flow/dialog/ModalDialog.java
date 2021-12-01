/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-19 09:40:48
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.ModalDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DialogFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.text.TitleLabel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@CssImport(value = "./styles/org/bklab/component/dialog/modal-dialog.css")
@CssImport(value = "./styles/org/bklab/component/dialog/vaadin-dialog-overlay.css", themeFor = "vaadin-dialog-overlay")
public class ModalDialog extends Dialog {

    private final ToolBar toolBar = new ToolBar();
    private final Div content = new Div();
    private final ToolBar footer = new ToolBar();
    private final TitleLabel title = new TitleLabel("提示");

    public ModalDialog() {
        add(toolBar, content, footer);

        toolBar.addClassName("modal-dialog-toolbar");
        content.addClassName("modal-dialog-content");
        footer.addClassName("modal-dialog-footer");

        toolBar.left(title).right(createCloseButton());
        setDraggable(true);
        setModal(true);
        setResizable(true);
    }

    public ModalDialog title(String title) {
        this.title.removeAll();
        this.title.setText(title);
        return this;
    }

    public ModalDialog title(Component... components) {
        this.title.removeAll();
        this.title.add(components);
        return this;
    }

    public ModalDialog modal() {
        this.setModal(true);
        return this;
    }

    public ModalDialog modal(boolean modal) {
        this.setModal(modal);
        return this;
    }

    public ModalDialog addButton(BiConsumer<ButtonFactory, ModalDialog> buttonModalConsumer) {
        buttonModalConsumer.accept(new ButtonFactory().lumoSmall(), this);
        return this;
    }

    public ModalDialog addButton(Consumer<FluentButton> buttonModalConsumer) {
        FluentButton fluentButton = new FluentButton();
        footerRight(fluentButton);
        buttonModalConsumer.accept(fluentButton);
        return this;
    }

    public ModalDialog addButton(Button button) {
        footerRight(button);
        return this;
    }

    public ModalDialog addPrimaryButton(Consumer<FluentButton> buttonModalConsumer) {
        FluentButton fluentButton = new FluentButton().primary();
        footerRight(fluentButton);
        buttonModalConsumer.accept(fluentButton);
        return this;
    }

    public ModalDialog addPrimaryButton(String text, ComponentEventListener<ClickEvent<Button>> listener) {
        footerRight(new FluentButton(text, listener).primary());
        return this;
    }

    public ModalDialog addPrimaryButton(VaadinIcon icon, String text, ComponentEventListener<ClickEvent<Button>> listener) {
        footerRight(new FluentButton(icon, text, listener).primary());
        return this;
    }

    public ModalDialog addSaveButton(ComponentEventListener<ClickEvent<Button>> listener) {
        footerRight(FluentButton.saveButton().primary().asFactory().clickListener(listener).get());
        return this;
    }

    public ModalDialog addUpdateButton(ComponentEventListener<ClickEvent<Button>> listener) {
        footerRight(FluentButton.updateButton().primary().asFactory().clickListener(listener).get());
        return this;
    }

    public ModalDialog addSaveButton(boolean updateMode, ComponentEventListener<ClickEvent<Button>> listener) {
        return updateMode ? addUpdateButton(listener) : addSaveButton(listener);
    }

    public ModalDialog addCloseButton() {
        return addCloseButton("关闭");
    }

    public ModalDialog height(String height) {
        setHeight(height);
        return this;
    }

    public ModalDialog width(String width) {
        setWidth(width);
        return this;
    }

    public ModalDialog width(String minWidth, String maxWidth) {
        setMinWidth(minWidth);
        setMaxWidth(maxWidth);
        return this;
    }

    public ModalDialog height(String minHeight, String maxHeight) {
        setMinHeight(minHeight);
        setMaxHeight(maxHeight);
        return this;
    }

    public ModalDialog width(String minWidth, String width, String maxWidth) {
        setMinWidth(minWidth);
        setWidth(width);
        setMaxWidth(maxWidth);
        return this;
    }

    public ModalDialog height(String minHeight, String height, String maxHeight) {
        setMinHeight(minHeight);
        setHeight(height);
        setMaxHeight(maxHeight);
        return this;
    }

    public ModalDialog addCancelButton() {
        return footerRight(FluentButton.cancelButton().asFactory().clickListener(event -> close()).get());
    }

    public ModalDialog addCloseButton(String text) {
        return footerRight(FluentButton.cancelButton().asFactory().text(text).clickListener(event -> close()).get());
    }

    public ModalDialog toolBarRight(Component... components) {
        toolBar.right(components);
        return this;
    }

    public ModalDialog toolBarMiddle(Component... components) {
        toolBar.middle(components);
        return this;
    }

    public ModalDialog toolBarLeft(Component... components) {
        toolBar.left(components);
        return this;
    }

    public ModalDialog content(Component... components) {
        content.add(components);
        return this;
    }

    public ModalDialog content(String text) {
        content.add(text);
        return this;
    }

    public ModalDialog footerRight(Component... components) {
        footer.right(components);
        return this;
    }

    public ModalDialog footerLeft(Component... components) {
        footer.left(components);
        return this;
    }

    public ModalDialog footerMiddle(Component... components) {
        footer.middle(components);
        return this;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public Div getContent() {
        return content;
    }

    public ToolBar getFooter() {
        return footer;
    }

    public TitleLabel getTitle() {
        return title;
    }

    public ModalDialog noFooter() {
        this.footer.setVisible(false);
        this.content.addClassName("modal-dialog-no-footer");
        return this;
    }

    public ModalDialog hasFooter() {
        this.footer.setVisible(true);
        this.content.removeClassName("modal-dialog-no-footer");
        return this;
    }

    public DialogFactory asFactory() {
        return new DialogFactory(this);
    }

    private Div createCloseButton() {
        return new DivFactory(new Html("<span class='dialog-modal-close-button-inner-html'><span role=\"img\" aria-label=\"close\"><svg viewBox=\"64 64 896 896\" focusable=\"false\" class=\"\" data-icon=\"close\" width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\"> <path d=\"M563.8 512l262.5-312.9c4.4-5.2.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 824.9A7.95 7.95 0 00203 838h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z\"></path> </svg></span></span>")).clickListener(e -> close()).className("dialog-modal-close-button").get();
    }

    public ModalDialog size(String width, String height) {
        this.setWidth(width);
        this.setHeight(height);
        return this;
    }
}
