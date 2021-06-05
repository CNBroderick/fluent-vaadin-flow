/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-26 09:37:12
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.crud.menu.IOperationMenuComponentSupporter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.crud.FluentCrudView;
import org.bklab.export.grid.GridColumnDataBuilderFactory;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.menu.FluentMenuItem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@SuppressWarnings("UnusedReturnValue")
public interface IOperationMenuComponentSupporter<T, G extends Grid<T>, C extends FluentCrudView<T, G>> extends IFluentMenuSeparatorSupporter {

    default C addOperationMenuButtonToRight(BiConsumer<C, ContextMenu> menuBuilder) {
        return addOperationMenuButtonToRight(menuBuilder, false);
    }

    default C addOperationMenuButtonToRight(BiConsumer<C, ContextMenu> menuBuilder, boolean dynamic) {
        getCrudView().noRefreshIconButton();
        Button operationButton = createOperationMenuButton(menuBuilder, dynamic);
        getCrudView().header().right(operationButton);
        return getCrudView();
    }

    default C separator(ContextMenu contextMenu) {
        IFluentMenuSeparatorSupporter.super.addSeparator(contextMenu);
        return getCrudView();
    }

    default Button createOperationMenuButton(BiConsumer<C, ContextMenu> menuBuilder) {
        return createOperationMenuButton(menuBuilder, false);
    }

    default Button createOperationMenuButton(BiConsumer<C, ContextMenu> menuBuilder, boolean dynamic) {
        Button button = new FluentButton(VaadinIcon.MENU).iconOnly().noPadding().tooltip("点击显示操作菜单").asFactory().alignSelfBaseline().border("0").get();
        return extendOperationItemsComponent(button, menuBuilder, dynamic);
    }

    default <A extends Component> A extendOperationItemsComponent(A component, BiConsumer<C, ContextMenu> menuBuilder, boolean dynamic) {
        ContextMenu contextMenu = new ContextMenu(component);
        menuBuilder.accept(getCrudView(), contextMenu);
        contextMenu.setOpenOnClick(true);
        if (dynamic) buildDynamicOperationMenuComponent(contextMenu, menuBuilder);
        removeDuplicateSeparator(contextMenu);
        return component;
    }

    default C buildDynamicOperationMenuComponent(ContextMenu contextMenu, BiConsumer<C, ContextMenu> menuBuilder) {
        contextMenu.addOpenedChangeListener(e -> {
            if (!e.isOpened()) return;
            contextMenu.removeAll();
            menuBuilder.accept(getCrudView(), contextMenu);
            removeDuplicateSeparator(contextMenu);
        });
        return getCrudView();
    }

    default <A extends Component> A extendOperationMenuComponent(A component, BiConsumer<C, ContextMenu> menuBuilder) {
        ContextMenu contextMenu = new ContextMenu(component);
        menuBuilder.accept(getCrudView(), contextMenu);
        removeDuplicateSeparator(contextMenu);
        contextMenu.setOpenOnClick(true);
        return component;
    }

    default C extendDefaultMenu(ContextMenu contextMenu) {
        addSeparator(contextMenu);
        extendRefreshMenu(contextMenu);
        extendDefaultExportMenuItem(contextMenu);
        return getCrudView();
    }

    default C extendDefaultMenu(ContextMenu contextMenu, Supplier<String> title) {
        addSeparator(contextMenu);
        extendRefreshMenu(contextMenu).createExportMenuItem(contextMenu, title, title);
        return getCrudView();
    }

    default C extendRefreshMenu(ContextMenu contextMenu) {
        new FluentMenuItem(VaadinIcon.REFRESH, "刷新").add(contextMenu, event -> getCrudView().reloadGridDataAndRecalculateColumnWidths());
        return getCrudView();
    }

    default C extendDefaultExportMenuItem(ContextMenu contextMenu) {
        getCrudView().createExportMenuItem().add(contextMenu, event -> getCrudView().processOpenExportDialog());
        return getCrudView();
    }

    default C extendDefaultExportMenu(ContextMenu contextMenu, Supplier<String> filename, Supplier<String> title) {
        getCrudView().createExportMenuItem().add(contextMenu, event -> getCrudView().processOpenExportDialog(
                filename, title, new GridColumnDataBuilderFactory<>(getCrudViewGrid()).createBuilder()));
        return getCrudView();
    }

    private Grid<T> getCrudViewGrid() {
        return getCrudView().getGrid();
    }

    C getCrudView();
}
