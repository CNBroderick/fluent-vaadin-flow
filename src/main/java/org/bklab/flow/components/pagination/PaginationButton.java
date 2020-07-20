package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.factory.IconFactory;

@CssImport("./styles/components/pagination/pagination-button.css")
public class PaginationButton extends Button {

    private final static String CLASS_NAME = "pagination-button";

    {
        addClassName(CLASS_NAME);
        border(false);
    }

    /**
     * 普通页码按钮
     *
     * @param pageNumber 页码
     * @param listener   点击事件
     */
    public PaginationButton(int pageNumber, ComponentEventListener<ClickEvent<Button>> listener) {
        super(String.valueOf(pageNumber), listener);
    }

    /**
     * 翻页按钮
     *
     * @param forward  true：> 向后翻页  false：< 向前翻页
     * @param listener 点击事件
     */
    public PaginationButton(boolean forward, ComponentEventListener<ClickEvent<Button>> listener) {
        super(createIcon(forward), listener);
    }

    /**
     * 切换页码按钮
     *
     * @param forward  true：>> 向后切换  false：<< 向前切换
     * @param listener 点击事件
     */
    public PaginationButton(ComponentEventListener<ClickEvent<Button>> listener,boolean forward) {
        Icon focus = forward ? VaadinIcon.ANGLE_DOUBLE_RIGHT.create() : VaadinIcon.ANGLE_DOUBLE_LEFT.create();
        toggleBlurMode();
        addFocusListener(e -> toggleFocusMode(focus));
        addBlurListener(e -> toggleBlurMode());
        getElement().addEventListener("mouseover", e -> toggleFocusMode(focus));
        addClassName(CLASS_NAME + "__jump");
    }

    private static Icon createIcon(boolean forward) {
        return new IconFactory(forward ? VaadinIcon.ANGLE_RIGHT : VaadinIcon.ANGLE_LEFT).get();
    }

    private void toggleBlurMode() {
        setText("•••");
        setIcon(null);
    }

    private void toggleFocusMode(Icon icon) {
        setText(null);
        setIcon(icon);
    }

    public PaginationButton setText(int pageNumber) {
        setText(String.valueOf(pageNumber));
        return this;
    }

    public PaginationButton border(boolean border) {
        addClassName(border ? CLASS_NAME + "__has-border" : CLASS_NAME + "__no-border");
        removeClassName(border ? CLASS_NAME + "__no-border" : CLASS_NAME + "__has-border");
        return this;
    }

    public PaginationButton normalSize() {
        return normalSize(true);
    }

    public PaginationButton normalSize(boolean normal) {
        if (normal) addClassName(CLASS_NAME + "__normal-size");
        else removeClassName(CLASS_NAME + "__normal-size");
        return this;
    }

    public PaginationButton select() {
        addClassName(CLASS_NAME + "__selected");
        return this;
    }

    public PaginationButton deselect() {
        removeClassName(CLASS_NAME + "__selected");
        return this;
    }

    public PaginationButton disable() {
        setEnabled(false);
        setDisableOnClick(true);
        addClassName(CLASS_NAME + "__disabled");
        return this;
    }

    public PaginationButton enable() {
        setEnabled(true);
        setDisableOnClick(false);
        removeClassName(CLASS_NAME + "__disabled");
        return this;
    }

}
