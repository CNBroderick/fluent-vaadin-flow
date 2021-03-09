package org.bklab.flow.components.button;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.factory.ButtonFactory;

@Tag("fluent-button")
@CssImport("./styles/org/bklab/component/button/fluent-button.css")
public class FluentButton extends Button {

    private final static String CLASS_NAME = "fluent-button";

    public FluentButton(VaadinIcon icon, String text) {
        super(text, icon.create());
    }

    public static FluentButton addButton() {
        return new FluentButton(VaadinIcon.PLUS, "新建").primary();
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

    public FluentButton(Component icon) {
        super(icon);
    }

    public static FluentButton searchButton() {
        return new FluentButton(VaadinIcon.SEARCH, "查询");
    }

    public static FluentButton linkButton(String link) {
        return new FluentButton(link).link().noPadding();
    }

    public FluentButton(VaadinIcon icon) {
        super(icon.create());
    }

    public FluentButton() {
    }

    {
        addClassNames(CLASS_NAME);
    }

    public FluentButton(VaadinIcon icon, String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(text, icon.create(), clickListener);
    }

    public FluentButton(String text) {
        super(text);
    }

    public static FluentButton exportButton() {
        return new FluentButton(VaadinIcon.EXTERNAL_LINK).link().noPadding().tooltip("导出到Excel文件");
    }

    public static FluentButton refreshIconButton() {
        return new FluentButton(VaadinIcon.REFRESH).link().noPadding().tooltip("刷新");
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

    public static FluentButton errorButton() {
        return new FluentButton(VaadinIcon.EXCLAMATION_CIRCLE_O, "错误").error();
    }

    public FluentButton reset() {
        removeClassName(CLASS_NAME + "__primary");
        removeClassName(CLASS_NAME + "__error");
        removeClassName(CLASS_NAME + "__dashed");
        removeClassName(CLASS_NAME + "__link");
        return this;
    }

    public FluentButton primary() {
        addClassNames(CLASS_NAME + "__primary");
        return this;
    }

    public FluentButton error() {
        addClassNames(CLASS_NAME + "__error");
        return this;
    }

    public FluentButton iconOnly() {
        Component icon = getIcon();
        if (icon != null) icon.getElement().getStyle().set("padding-left", "0").set("padding-bottom", "0");
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

    public FluentButton clickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        addClickListener(listener);
        return this;
    }

    public FluentButton noPadding() {
        getStyle().set("padding-top", "0").set("padding-bottom", "0")
                .set("padding-left", "0").set("padding-right", "0");
        return this;
    }

    public FluentButton tooltip(String text) {
        Tooltips.getCurrent().setTooltip(this, text);
        return this;
    }

    public ButtonFactory asFactory() {
        return new ButtonFactory(this);
    }
}
