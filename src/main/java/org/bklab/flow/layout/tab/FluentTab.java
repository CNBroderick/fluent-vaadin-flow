package org.bklab.flow.layout.tab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tab;

import java.util.function.Supplier;

public class FluentTab extends Tab {
    protected final String id;
    protected Supplier<Component> componentSupplier;
    protected Component component;
    protected boolean cacheMode = true;

    public FluentTab(String id) {
        this.id = id;
    }

    public FluentTab(String id, Supplier<Component> componentSupplier) {
        this(id, id, componentSupplier);
    }

    public FluentTab(String id, String caption, Supplier<Component> componentSupplier) {
        super(caption);
        this.id = id;
        this.componentSupplier = componentSupplier;
    }

    public FluentTab(String id, String caption) {
        super(caption);
        this.id = id;
    }

    public FluentTab(String id, Component component, Supplier<Component> componentSupplier) {
        super(component);
        this.id = id;
        this.componentSupplier = componentSupplier;
    }

    public FluentTab(String id, Supplier<Component> componentSupplier, Component... component) {
        super(component);
        this.id = id;
        this.componentSupplier = componentSupplier;
    }

    public Component getComponent() {
        if (component == null || !cacheMode) component = componentSupplier.get();
        return component;
    }

    public Supplier<Component> getComponentSupplier() {
        return componentSupplier;
    }

    public FluentTab enableCache() {
        this.cacheMode = true;
        return this;
    }

    public FluentTab disableCache() {
        this.cacheMode = false;
        return this;
    }
}
