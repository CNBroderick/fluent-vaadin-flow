package org.bklab.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

public interface IFluentGridMenuBuilder<T, G extends Grid<T>> {

    void build(FluentCrudView<T, G> fluentCrudView, GridContextMenu<T> gridContextMenu, T entity);

    default void safeBuild(FluentCrudView<T, G> fluentCrudView, GridContextMenu<T> gridContextMenu, T entity) {
        try {
            build(fluentCrudView, gridContextMenu, entity);
        } catch (Exception e) {
            new ErrorDialog(e).build().open();
            LoggerFactory.getLogger(getClass()).error("构建菜单错误", e);
        }
    }
}
