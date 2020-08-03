package org.bklab.crud;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

public interface IFluentMenuBuilder<T, G extends Grid<T>> {

    void build(FluentCrudView<T, G> fluentCrudView, ContextMenu contextMenu, T entity);

    default void safeBuild(FluentCrudView<T, G> fluentCrudView, ContextMenu contextMenu, T entity) {
        try {
            build(fluentCrudView, contextMenu, entity);
        } catch (Exception e) {
            new ErrorDialog(e).build().open();
            LoggerFactory.getLogger(getClass()).error("构建菜单错误", e);
        }
    }
}
