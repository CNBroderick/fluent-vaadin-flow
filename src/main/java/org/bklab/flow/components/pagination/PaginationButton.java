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

    private Boolean jump;
    private Boolean forward;
    private Integer pageNo;

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
        this.pageNo = pageNumber;
        setDescription("第 " + pageNumber + " 页");
    }

    /**
     * 翻页按钮
     *
     * @param forward  true：> 向后翻页  false：< 向前翻页
     * @param listener 点击事件
     */
    public PaginationButton(boolean forward, ComponentEventListener<ClickEvent<Button>> listener) {
        super(createIcon(forward), listener);
        this.forward = forward;
        setDescription(forward ? "下一页" : "上一页");
    }

    /**
     * 切换页码按钮
     *
     * @param forward  true：>> 向后切换  false：<< 向前切换
     * @param listener 点击事件
     */
    public PaginationButton(ComponentEventListener<ClickEvent<Button>> listener,boolean forward) {
        this.jump = forward;
        Icon focus = forward ? VaadinIcon.ANGLE_DOUBLE_RIGHT.create() : VaadinIcon.ANGLE_DOUBLE_LEFT.create();
        toggleBlurMode();
        addFocusListener(e -> toggleFocusMode(focus));
        addBlurListener(e -> toggleBlurMode());
        getElement().addEventListener("mouseover", e -> toggleFocusMode(focus));
        getElement().addEventListener("mouseout", e -> toggleBlurMode());
        addClickListener(listener);
        addClassName(CLASS_NAME + "__jump");
        setDescription(forward ? "向后" : "向前");
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

    public PaginationButton enable(boolean enable) {
        if (enable) return enable();
        else return disable();
    }

    public PaginationButton enable() {
        setEnabled(true);
        setDisableOnClick(false);
        removeClassName(CLASS_NAME + "__disabled");
        return this;
    }

    public PaginationButton setDescription(String description) {
        getElement().setProperty("title", description)
                .setProperty("aria-label", description);
        return this;
    }

    public boolean isPageNo() {
        return pageNo != null;
    }

    public boolean isNext() {
        return forward != null && forward;
    }

    public boolean isPrev() {
        return forward != null && !forward;
    }

    public boolean isNextJump() {
        return jump != null && jump;
    }

    public boolean isPrevJump() {
        return jump != null && !jump;
    }

    public Integer getPageNo() {
        return pageNo;
    }
}
