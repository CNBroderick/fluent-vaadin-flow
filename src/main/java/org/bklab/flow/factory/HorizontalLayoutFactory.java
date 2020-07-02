/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-18 21:13:18
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.HorizontalLayoutFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.FlexComponentFactory;
import org.bklab.flow.base.ThemableLayoutFactory;

public class HorizontalLayoutFactory extends FlowFactory<HorizontalLayout, HorizontalLayoutFactory> implements
        ThemableLayoutFactory<HorizontalLayout, HorizontalLayoutFactory>,
        FlexComponentFactory<HorizontalLayout, HorizontalLayoutFactory>,
        ClickNotifierFactory<HorizontalLayout, HorizontalLayoutFactory> {
    public HorizontalLayoutFactory(HorizontalLayout component) {
        super(component);
    }

    public HorizontalLayoutFactory() {
        this(new HorizontalLayout());
    }

    public HorizontalLayoutFactory(Component... components) {
        this(new HorizontalLayout(components));
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignment(FlexComponent.Alignment defaultVerticalComponentAlignment) {
        get().setDefaultVerticalComponentAlignment(defaultVerticalComponentAlignment);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentStart() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentEnd() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentCenter() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentStretch() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentBaseline() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        return this;
    }

    public HorizontalLayoutFactory defaultVerticalComponentAlignmentAuto() {
        get().setDefaultVerticalComponentAlignment(FlexComponent.Alignment.AUTO);
        return this;
    }

    public HorizontalLayoutFactory spacing(boolean spacing) {
        get().setSpacing(spacing);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignment(FlexComponent.Alignment flexComponentAlignment, Component... component) {
        get().setVerticalComponentAlignment(flexComponentAlignment, component);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignmentEnd(Component... component) {
        get().setVerticalComponentAlignment(FlexComponent.Alignment.END, component);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignmentCenter(Component... component) {
        get().setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, component);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignmentStretch(Component... component) {
        get().setVerticalComponentAlignment(FlexComponent.Alignment.STRETCH, component);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignmentBaseline(Component... component) {
        get().setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, component);
        return this;
    }

    public HorizontalLayoutFactory verticalComponentAlignmentAuto(Component... component) {
        get().setVerticalComponentAlignment(FlexComponent.Alignment.AUTO, component);
        return this;
    }

    public HorizontalLayoutFactory padding(boolean padding) {
        get().setPadding(padding);
        return this;
    }

    public HorizontalLayoutFactory alignItems(FlexComponent.Alignment alignItems) {
        get().setAlignItems(alignItems);
        return this;
    }

    public HorizontalLayoutFactory alignItemsStart() {
        get().setAlignItems(FlexComponent.Alignment.START);
        return this;
    }

    public HorizontalLayoutFactory alignItemsEnd() {
        get().setAlignItems(FlexComponent.Alignment.END);
        return this;
    }

    public HorizontalLayoutFactory alignItemsCenter() {
        get().setAlignItems(FlexComponent.Alignment.CENTER);
        return this;
    }

    public HorizontalLayoutFactory alignItemsStretch() {
        get().setAlignItems(FlexComponent.Alignment.STRETCH);
        return this;
    }

    public HorizontalLayoutFactory alignItemsBaseline() {
        get().setAlignItems(FlexComponent.Alignment.BASELINE);
        return this;
    }

    public HorizontalLayoutFactory alignItemsAuto() {
        get().setAlignItems(FlexComponent.Alignment.AUTO);
        return this;
    }

    public HorizontalLayoutFactory alignSelf(FlexComponent.Alignment flexComponentAlignment, HasElement... hasElement) {
        get().setAlignSelf(flexComponentAlignment, hasElement);
        return this;
    }

    public HorizontalLayoutFactory andExpand(Component... andExpand) {
        get().addAndExpand(andExpand);
        return this;
    }
}
