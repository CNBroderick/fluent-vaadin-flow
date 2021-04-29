/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-20 11:45:28
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.crud.menu.IFluentMenuBuilder
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

public interface IFluentMenuBuilder<T, G extends Grid<T>> extends IFluentMenuSeparatorSupporter {

    void build(FluentCrudView<T, G> fluentCrudView, ContextMenu contextMenu, T entity);

    default void safeBuild(FluentCrudView<T, G> fluentCrudView, ContextMenu contextMenu, T entity) {
        try {

            build(fluentCrudView, contextMenu, entity);
            removeDuplicateSeparator(contextMenu);
            Component target = contextMenu.getTarget();
            if (target instanceof HasEnabled) {
                ((HasEnabled) target).setEnabled(contextMenu.getChildren().count() > 0);
            }
        } catch (Exception e) {
            new ErrorDialog(e).build().open();
            LoggerFactory.getLogger(getClass()).error("构建菜单错误", e);
        }
    }
}
