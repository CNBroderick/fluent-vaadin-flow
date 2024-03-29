/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 09:46:47
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.layout.tab.FluentTab
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

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

    public FluentTab enableCache(boolean enable) {
        this.cacheMode = enable;
        return this;
    }

    public FluentTab disableCache() {
        this.cacheMode = false;
        return this;
    }

    public String getTabId() {
        return id;
    }

    public boolean isCacheMode() {
        return cacheMode;
    }
}
