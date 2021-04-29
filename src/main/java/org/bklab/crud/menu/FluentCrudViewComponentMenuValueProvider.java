/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-23 15:50:47
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.crud.menu.FluentCrudViewComponentMenuValueProvider
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.components.button.FluentButton;

import java.util.function.Function;

public class FluentCrudViewComponentMenuValueProvider<T, G extends Grid<T>, C extends FluentCrudView<T, G>> implements ValueProvider<T, Component> {

    private C fluentCrudView;
    private G grid;
    private Function<T, Component> menuContentSupplier;
    private IFluentMenuBuilder<T, G> menuEntityBiConsumer;

    public FluentCrudViewComponentMenuValueProvider() {
    }

    public FluentCrudViewComponentMenuValueProvider(C fluentCrudView, Function<T, Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        this.fluentCrudView = fluentCrudView;
        this.grid = fluentCrudView.getGrid();
        this.menuContentSupplier = menuContentSupplier;
        this.menuEntityBiConsumer = menuEntityBiConsumer;
    }

    public FluentCrudViewComponentMenuValueProvider(G grid, Function<T, Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        this.grid = grid;
        this.menuContentSupplier = menuContentSupplier;
        this.menuEntityBiConsumer = menuEntityBiConsumer;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> grid(G grid) {
        this.grid = grid;
        return this;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> fluentCrudView(C fluentCrudView) {
        this.fluentCrudView = fluentCrudView;
        this.grid = fluentCrudView.getGrid();
        return this;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> menuContentButtonSupplier(Function<T, String> menuContentSupplier) {
        this.menuContentSupplier = entity -> FluentButton.linkButton(menuContentSupplier.apply(entity));
        return this;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> menuContentButtonSupplier(Function<T, String> text, Function<T, String> tooltip) {
        this.menuContentSupplier = entity -> FluentButton.linkButton(text.apply(entity)).tooltip(tooltip.apply(entity));
        return this;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> menuContentSupplier(Function<T, Component> menuContentSupplier) {
        this.menuContentSupplier = menuContentSupplier;
        return this;
    }

    public FluentCrudViewComponentMenuValueProvider<T, G, C> menuEntityBiConsumer(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        this.menuEntityBiConsumer = menuEntityBiConsumer;
        return this;
    }

    @Override
    public Component apply(T entity) {
        Component button = menuContentSupplier.apply(entity);
        ContextMenu contextMenu = new ContextMenu(button);
        contextMenu.addOpenedChangeListener(e -> {
            if (e.isOpened()) {
                if (contextMenu.getItems().isEmpty()) {
                    menuEntityBiConsumer.safeBuild(fluentCrudView, contextMenu, entity);
                    contextMenu.setVisible(true);
                }
                if (grid != null) grid.select(entity);
            }
        });
        FluentCrudMenuButton<T, G> crudMenuButton = new FluentCrudMenuButton<>(fluentCrudView, entity, button, contextMenu, menuEntityBiConsumer);
        if (fluentCrudView != null) fluentCrudView.getMenuButtons().add(crudMenuButton);
        contextMenu.setOpenOnClick(true);
        menuEntityBiConsumer.safeBuild(fluentCrudView, contextMenu, entity);
        return button;
    }
}
