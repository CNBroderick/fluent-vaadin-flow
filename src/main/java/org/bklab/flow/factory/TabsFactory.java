package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinTabsFactory;
import org.bklab.flow.base.HasOrderedComponentsFactory;
import org.bklab.flow.base.HasSizeFactory;

public class TabsFactory extends FlowFactory<Tabs, TabsFactory> implements
        GeneratedVaadinTabsFactory<Tabs, TabsFactory>,
        HasOrderedComponentsFactory<Tabs, TabsFactory>,
        HasSizeFactory<Tabs, TabsFactory> {
    public TabsFactory() {
        this(new Tabs());
    }

    public TabsFactory(Tab... tabs) {
        this(new Tabs(tabs));
    }

    public TabsFactory(boolean autoSelect, Tab... tabs) {
        this(new Tabs(autoSelect, tabs));
    }

    public TabsFactory(Tabs component) {
        super(component);
    }

    public TabsFactory add(Tab... add) {
        get().add(add);
        return this;
    }

    public TabsFactory add(Component... add) {
        get().add(add);
        return this;
    }

    public TabsFactory remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public TabsFactory replace(Component oldComponent, Component newComponent) {
        get().replace(oldComponent, newComponent);
        return this;
    }

    public TabsFactory removeAll() {
        get().removeAll();
        return this;
    }

    public TabsFactory componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return this;
    }

    public TabsFactory flexGrowForEnclosedTabs(double flexGrowForEnclosedTabs) {
        get().setFlexGrowForEnclosedTabs(flexGrowForEnclosedTabs);
        return this;
    }

    public TabsFactory selectedChangeListener(ComponentEventListener<Tabs.SelectedChangeEvent> selectedChangeListener) {
        get().addSelectedChangeListener(selectedChangeListener);
        return this;
    }

    public TabsFactory selectedTab(Tab selectedTab) {
        get().setSelectedTab(selectedTab);
        return this;
    }

    public TabsFactory selectedIndex(int selectedIndex) {
        get().setSelectedIndex(selectedIndex);
        return this;
    }

    public TabsFactory autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return this;
    }

    public TabsFactory orientation(Tabs.Orientation orientation) {
        get().setOrientation(orientation);
        return this;
    }
}
