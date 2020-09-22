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
import com.vaadin.flow.dom.Style;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DialogFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.text.TitleLabel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@CssImport(value = "./styles/components/modal-dialog.css", themeFor = "vaadin-dialog-overlay")
public class ModalDialog extends Dialog {

    private final ToolBar toolBar = new ToolBar();
    private final Div content = new Div();
    private final ToolBar footer = new ToolBar();
    private final TitleLabel title = new TitleLabel("提示");

    public ModalDialog() {
        VerticalLayoutFactory main = new VerticalLayoutFactory(toolBar, content, footer);
        add(main.get());

        createContainerStyle(main.get().getStyle());
        createTopStyle(toolBar.getStyle());
        createContentStyle(content.getStyle());
        createFooterStyle(footer.getStyle());

        main.expand(content)
                .alignSelfStart(toolBar)
                .alignSelfCenter(content)
                .alignSelfEnd()
                .padding(false)
                .margin(false)
                .spacing(false)
                .sizeFull();
        toolBar.left(title).right(createCloseButton());
        setDraggable(true);
        setModal(true);
        setResizable(true);
    }

    private void createContainerStyle(Style style) {
        style
                .set("font-family", "apple-system,BlinkMacSystemFont,segoe ui,Roboto,helvetica neue,Arial,noto sans,sans-serif,apple color emoji,segoe ui emoji,segoe ui symbol,noto color emoji")
                .set("color", "rgba(0,0,0,.65)")
                .set("font-size", "14px")
                .set("font-variant", "tabular-nums")
                .set("line-height", "1.5715")
                .set("list-style", "none")
                .set("font-feature-settings", "tnum'")
                .set("margin", "0")
                .set("padding", "0")
                .set("box-sizing", "border-box")
                .set("position", "relative")
                .set("background-color", "fff")
                .set("background-clip", "padding-box")
                .set("border", "0")
                .set("border-radius", "2px")
                .set("box-shadow", "0 3px 6px -4px rgba(0,0,0,.12),0 6px 16px 0 rgba(0,0,0,.08),0 9px 28px 8px rgba(0,0,0,.05)")
                .set("pointer-events", "auto")
                .set("overflow", "hidden")
        ;
    }

    private void createTopStyle(Style style) {
        style
                .set("font-family", "-apple-system,BlinkMacSystemFont,segoe ui,Roboto,helvetica neue,Arial,noto sans,sans-serif,apple color emoji,segoe ui emoji,segoe ui symbol,noto color emoji")
                .set("font-size", "14px")
                .set("font-variant", "tabular-nums")
                .set("line-height", "1.5715")
                .set("list-style", "none")
                .set("font-feature-settings", "'tnum'")
                .set("pointer-events", "auto")
                .set("margin", "0")
                .set("box-sizing", "border-box")
                .set("padding", "16px 24px")
                .set("color", "rgba(0,0,0,.65)")
                .set("background", "#fff")
                .set("border-bottom", "1px solid #f0f0f0")
                .set("border-radius", "2px 2px 0 0")
        ;
    }

    private void createContentStyle(Style style) {
        style
                .set("font-family", "-apple-system,BlinkMacSystemFont,segoe ui,Roboto,helvetica neue,Arial,noto sans,sans-serif,apple color emoji,segoe ui emoji,segoe ui symbol,noto color emoji")
                .set("color", "rgba(0,0,0,.65)")
                .set("font-variant", "tabular-nums")
                .set("list-style", "none")
                .set("font-feature-settings", "'tnum'")
                .set("pointer-events", "auto")
                .set("margin", "0")
                .set("box-sizing", "border-box")
                .set("padding", "1em 24px")
                .set("font-size", "14px")
                .set("line-height", "1.5715")
                .set("width", "100%")
                .set("overflow", "auto")
        ;
    }

    private void createFooterStyle(Style style) {
        style
                .set("font-family", "-apple-system,BlinkMacSystemFont,segoe ui,Roboto,helvetica neue,Arial,noto sans,sans-serif,apple color emoji,segoe ui emoji,segoe ui symbol,noto color emoji")
                .set("color", "rgba(0,0,0,.65)")
                .set("font-size", "14px")
                .set("font-variant", "tabular-nums")
                .set("line-height", "1.5715")
                .set("list-style", "none")
                .set("font-feature-settings", "'tnum'")
                .set("pointer-events", "auto")
                .set("margin", "0")
                .set("box-sizing", "border-box")
                .set("padding", "10px 16px")
                .set("text-align", "right")
                .set("background", "0 0")
                .set("border-top", "1px solid #f0f0f0")
                .set("border-radius", "0 0 2px 2px")
        ;
    }

    private void createCloseButtonStyle(Style style) {
        style
                .set("background", "transparent !important")
                .set("border", "none !important")
                .set("list-style", "none")
                .set("font-feature-settings", "'tnum'")
                .set("pointer-events", "auto")
                .set("touch-action", "manipulation")
                .set("font-size", "inherit")
                .set("font-family", "inherit")
                .set("overflow", "visible")
                .set("text-transform", "none")
                .set("margin", "0")
                .set("box-sizing", "border-box")
                .set("position", "absolute")
                .set("top", "0")
                .set("right", "0")
                .set("z-index", "10")
                .set("padding", "0")
                .set("font-weight", "700")
                .set("line-height", "1")
                .set("border", "0")
                .set("outline", "0")
                .set("cursor", "pointer")
                .set("transition", "color .3s")
                .set("-webkit-appearance", "button")
                .set("color", "rgba(0,0,0,.75)")
                .set("text-decoration", "none")
        ;
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
        footerRight(new FluentButton(text, listener));
        return this;
    }

    public ModalDialog addPrimaryButton(VaadinIcon icon, String text, ComponentEventListener<ClickEvent<Button>> listener) {
        footerRight(new FluentButton(icon, text, listener));
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
        return this;
    }

    public DialogFactory asFactory() {
        return new DialogFactory(this);
    }

    private Div createCloseButton() {
        Div div = new Div(new Html("<span style='pointer-events: auto;font-family: inherit;font-weight: 700;cursor: pointer;color: rgba(0,0,0,.75);box-sizing: border-box;display: block;width: 56px;height: 56px;font-size: 16px;font-style: normal;line-height: 56px;text-align: center;text-transform: none;text-rendering: auto;'><span role=\"img\" aria-label=\"close\"><svg viewBox=\"64 64 896 896\" focusable=\"false\" class=\"\" data-icon=\"close\" width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\"><path d=\"M563.8 512l262.5-312.9c4.4-5.2.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 824.9A7.95 7.95 0 00203 838h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z\"></path></svg></span></span>"));
        div.addClickListener(e -> close());
        createCloseButtonStyle(div.getStyle());
        return div;
    }
}
