package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.FlexComponentFactory;
import org.bklab.flow.base.ThemableLayoutFactory;

public class VerticalLayoutFactory extends FlowFactory<VerticalLayout, VerticalLayoutFactory> implements
        ThemableLayoutFactory<VerticalLayout, VerticalLayoutFactory>,
        FlexComponentFactory<VerticalLayout, VerticalLayoutFactory>,
        ClickNotifierFactory<VerticalLayout, VerticalLayoutFactory> {
    public VerticalLayoutFactory(VerticalLayout component) {
        super(component);
    }

    public VerticalLayoutFactory() {
        this(new VerticalLayout());
    }

    public VerticalLayoutFactory(Component... components) {
        this(new VerticalLayout(components));
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignment(FlexComponent.Alignment defaultHorizontalComponentAlignment) {
        get().setDefaultHorizontalComponentAlignment(defaultHorizontalComponentAlignment);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentStart() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentEnd() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.END);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentCenter() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentStretch() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentBaseline() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.BASELINE);
        return this;
    }

    public VerticalLayoutFactory defaultHorizontalComponentAlignmentAuto() {
        get().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.AUTO);
        return this;
    }

    public VerticalLayoutFactory spacing(boolean spacing) {
        get().setSpacing(spacing);
        return this;
    }

    public VerticalLayoutFactory horizontalComponentAlignment(FlexComponent.Alignment flexComponentAlignment, Component... component) {
        get().setHorizontalComponentAlignment(flexComponentAlignment, component);
        return this;
    }


    public VerticalLayoutFactory horizontalComponentAlignmentEnd(Component... component) {
        get().setHorizontalComponentAlignment(FlexComponent.Alignment.END, component);
        return this;
    }

    public VerticalLayoutFactory horizontalComponentAlignmentCenter(Component... component) {
        get().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, component);
        return this;
    }

    public VerticalLayoutFactory horizontalComponentAlignmentStretch(Component... component) {
        get().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH, component);
        return this;
    }

    public VerticalLayoutFactory horizontalComponentAlignmentBaseline(Component... component) {
        get().setHorizontalComponentAlignment(FlexComponent.Alignment.BASELINE, component);
        return this;
    }

    public VerticalLayoutFactory horizontalComponentAlignmentAuto(Component... component) {
        get().setHorizontalComponentAlignment(FlexComponent.Alignment.AUTO, component);
        return this;
    }

    public VerticalLayoutFactory padding(boolean padding) {
        get().setPadding(padding);
        return this;
    }

    public VerticalLayoutFactory alignItems(FlexComponent.Alignment alignItems) {
        get().setAlignItems(alignItems);
        return this;
    }

    public VerticalLayoutFactory alignSelf(FlexComponent.Alignment flexComponentAlignment, HasElement... hasElement) {
        get().setAlignSelf(flexComponentAlignment, hasElement);
        return this;
    }

    public VerticalLayoutFactory andExpand(Component... andExpand) {
        get().addAndExpand(andExpand);
        return this;
    }
}
