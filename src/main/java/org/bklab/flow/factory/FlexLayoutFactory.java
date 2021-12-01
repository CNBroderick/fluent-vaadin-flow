/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-16 13:52:41
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.factory.FlexLayoutFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.FlexComponentFactory;

public class FlexLayoutFactory extends FlowFactory<FlexLayout, FlexLayoutFactory> implements
        FlexComponentFactory<FlexLayout, FlexLayoutFactory>,
        ClickNotifierFactory<FlexLayout, FlexLayoutFactory> {

    public FlexLayoutFactory() {
        this(new FlexLayout());
    }

    public FlexLayoutFactory(Component... children) {
        this(new FlexLayout(children));
    }

    public FlexLayoutFactory(FlexLayout component) {
        super(component);
    }

    public FlexLayoutFactory flexBasis(String width, HasElement... elementContainers) {
        get().setFlexBasis(width, elementContainers);
        return this;
    }

    public FlexLayoutFactory alignContent(FlexLayout.ContentAlignment alignContent) {
        get().setAlignContent(alignContent);
        return this;
    }

    public FlexLayoutFactory alignContentStart() {
        get().setAlignContent(FlexLayout.ContentAlignment.START);
        return this;
    }

    public FlexLayoutFactory alignContentEnd() {
        get().setAlignContent(FlexLayout.ContentAlignment.END);
        return this;
    }

    public FlexLayoutFactory alignContentCenter() {
        get().setAlignContent(FlexLayout.ContentAlignment.CENTER);
        return this;
    }

    public FlexLayoutFactory alignContentStretch() {
        get().setAlignContent(FlexLayout.ContentAlignment.STRETCH);
        return this;
    }

    public FlexLayoutFactory alignContentSpaceBetween() {
        get().setAlignContent(FlexLayout.ContentAlignment.SPACE_BETWEEN);
        return this;
    }

    public FlexLayoutFactory alignContentSpaceAround() {
        get().setAlignContent(FlexLayout.ContentAlignment.SPACE_AROUND);
        return this;
    }

    public FlexLayoutFactory flexWrap(FlexLayout.FlexWrap flexWrap) {
        get().setFlexWrap(flexWrap);
        return this;
    }

    public FlexLayoutFactory flexWrapNowrap() {
        get().setFlexWrap(FlexLayout.FlexWrap.NOWRAP);
        return this;
    }

    public FlexLayoutFactory flexWrapWrap() {
        get().setFlexWrap(FlexLayout.FlexWrap.WRAP);
        return this;
    }

    public FlexLayoutFactory flexWrapWrapReverse() {
        get().setFlexWrap(FlexLayout.FlexWrap.WRAP_REVERSE);
        return this;
    }

    public FlexLayoutFactory order(int order, HasElement elementContainer) {
        get().setOrder(order, elementContainer);
        return this;
    }

    public FlexLayoutFactory flexShrink(double flexShrink, HasElement... elementContainers) {
        get().setFlexShrink(flexShrink, elementContainers);
        return this;
    }

    public FlexLayoutFactory flexDirection(FlexLayout.FlexDirection flexDirection) {
        get().setFlexDirection(flexDirection);
        return this;
    }

    public FlexLayoutFactory flexDirectionRow() {
        get().setFlexDirection(FlexLayout.FlexDirection.ROW);
        return this;
    }

    public FlexLayoutFactory flexDirectionRowReverse() {
        get().setFlexDirection(FlexLayout.FlexDirection.ROW_REVERSE);
        return this;
    }

    public FlexLayoutFactory flexDirectionColumn() {
        get().setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return this;
    }

    public FlexLayoutFactory flexDirectionColumnReverse() {
        get().setFlexDirection(FlexLayout.FlexDirection.COLUMN_REVERSE);
        return this;
    }
}
