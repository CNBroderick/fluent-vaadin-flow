package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.splitlayout.GeneratedVaadinSplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinSplitLayoutFactory;
import org.bklab.flow.base.HasSizeFactory;

public class SplitLayoutFactory extends FlowFactory<SplitLayout, SplitLayoutFactory> implements
        GeneratedVaadinSplitLayoutFactory<SplitLayout, SplitLayoutFactory>,
        HasSizeFactory<SplitLayout, SplitLayoutFactory> {
    public SplitLayoutFactory() {
        this(new SplitLayout());
    }

    public SplitLayoutFactory(Component primaryComponent, Component secondaryComponent) {
        this(new SplitLayout(primaryComponent, secondaryComponent));
    }

    public SplitLayoutFactory(SplitLayout component) {
        super(component);
    }

    public SplitLayoutFactory remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public SplitLayoutFactory removeAll() {
        get().removeAll();
        return this;
    }

    public SplitLayoutFactory splitterPosition(double splitterPosition) {
        get().setSplitterPosition(splitterPosition);
        return this;
    }

    public SplitLayoutFactory splitterDragendListener(ComponentEventListener<GeneratedVaadinSplitLayout.SplitterDragendEvent<SplitLayout>> splitterDragendListener) {
        get().addSplitterDragendListener(splitterDragendListener);
        return this;
    }

    public SplitLayoutFactory secondaryStyle(String styleName, String value) {
        get().setSecondaryStyle(styleName, value);
        return this;
    }

    public SplitLayoutFactory toPrimary(Component... toPrimary) {
        get().addToPrimary(toPrimary);
        return this;
    }

    public SplitLayoutFactory toSecondary(Component... toSecondary) {
        get().addToSecondary(toSecondary);
        return this;
    }

    public SplitLayoutFactory orientation(SplitLayout.Orientation orientation) {
        get().setOrientation(orientation);
        return this;
    }

    public SplitLayoutFactory orientationVertical() {
        get().setOrientation(SplitLayout.Orientation.VERTICAL);
        return this;
    }

    public SplitLayoutFactory orientationHorizontal() {
        get().setOrientation(SplitLayout.Orientation.HORIZONTAL);
        return this;
    }

    public SplitLayoutFactory primaryStyle(String styleName, String value) {
        get().setPrimaryStyle(styleName, value);
        return this;
    }

}
