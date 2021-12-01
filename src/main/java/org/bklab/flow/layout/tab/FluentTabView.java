/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 16:15:18
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.layout.tab.FluentTabView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.layout.tab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.bklab.flow.base.HasFlowFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.layout.ToolBar;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Tag("fluent-tab-view")
public class FluentTabView extends Div implements HasFlowFactory<Div, DivFactory> {

    private final ToolBar toolBar = new ToolBar();
    private final Tabs tabs = new Tabs();
    private final Map<String, FluentTab> fluentTabMap = new LinkedHashMap<>();
    private final Div content = new DivFactory().width("calc(100% - 2em)").border()
            .padding("0.5em").margin("0.5em").height("calc(100% - 5em)").overflowYScroll().get();

    public FluentTabView() {
        setSizeFull();
        getStyle().set("background-color", "white");

        tabs.addSelectedChangeListener(e -> {
            if (e.isFromClient() && e.getSelectedTab() instanceof FluentTab) {
                FluentTab selectedTab = (FluentTab) e.getSelectedTab();
                content.removeAll();
                content.add(selectedTab.getComponent());
            }
        });
        toolBar.left(tabs);
        add(toolBar, content);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public FluentTabView addTab(String id, String caption, Supplier<Component> componentSupplier) {
        return addTab(new FluentTab(id, caption, componentSupplier));
    }

    public FluentTabView addTab(String id, Supplier<Component> componentSupplier) {
        return addTab(id, id, componentSupplier);
    }

    public FluentTabView addTab(FluentTab fluentTab) {
        if (fluentTabMap.containsKey(fluentTab.id)) {
            Optional.ofNullable(fluentTabMap.get(fluentTab.id)).ifPresent(tabs::remove);
        }
        fluentTabMap.put(fluentTab.id, fluentTab);
        tabs.add(fluentTab);
        return this;
    }

    public FluentTabView cacheAll() {
        fluentTabMap.values().forEach(FluentTab::enableCache);
        return this;
    }

    public FluentTabView cache(String... ids) {
        for (String id : ids) {
            Optional.ofNullable(fluentTabMap.get(id)).ifPresent(FluentTab::enableCache);
        }
        return this;
    }

    public FluentTabView disableCache(String... ids) {
        for (String id : ids) {
            Optional.ofNullable(fluentTabMap.get(id)).ifPresent(FluentTab::disableCache);
        }
        return this;
    }

    public FluentTabView selectFirst() {
        fluentTabMap.values().stream().findFirst().ifPresent(s -> {
            tabs.setSelectedTab(s);
            content.removeAll();
            content.add(s.getComponent());
        });
        return this;
    }

    public FluentTabView select(String id) {
        Optional.ofNullable(fluentTabMap.get(id)).ifPresent(s -> {
            tabs.setSelectedTab(s);
            content.removeAll();
            content.add(s.getComponent());
        });
        return this;
    }

    public Tabs getTabs() {
        return tabs;
    }

    public Map<String, FluentTab> getFluentTabMap() {
        return fluentTabMap;
    }

    public Div getContent() {
        return content;
    }

    public String getCurrentSelectTabId() {
        Tab selectedTab = getTabs().getSelectedTab();
        for (Map.Entry<String, FluentTab> entry : getFluentTabMap().entrySet()) {
            if (entry.getValue() == selectedTab) return entry.getKey();
        }
        return getFluentTabMap().keySet().stream().findFirst().orElse(null);
    }

    public FluentTab getCurrentSelectTab() {
        return getFluentTabMap().get(getCurrentSelectTabId());
    }

    @Override
    public DivFactory asFactory() {
        return new DivFactory(this);
    }
}
