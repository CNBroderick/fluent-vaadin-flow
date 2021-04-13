package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.components.menu.FluentMenuItem;
import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface IFluentGridMenuBuilder<T, G extends Grid<T>> {

    void build(FluentCrudView<T, G> fluentCrudView, GridContextMenu<T> gridContextMenu, T entity);

    default void safeBuild(FluentCrudView<T, G> fluentCrudView, GridContextMenu<T> gridContextMenu, T entity) {
        try {
            build(fluentCrudView, gridContextMenu, entity);
            removeDuplicateSeparator(gridContextMenu);
            Component target = gridContextMenu.getTarget();
            if (target instanceof HasEnabled) {
                ((HasEnabled) target).setEnabled(gridContextMenu.getChildren().count() > 0);
            }
        } catch (Exception e) {
            new ErrorDialog(e).build().open();
            LoggerFactory.getLogger(getClass()).error("构建表格菜单错误", e);
        }
    }

    default void addSeparator(ContextMenu contextMenu) {
        FluentMenuItem.addSeparator(contextMenu);
    }


    default void removeDuplicateSeparator(GridContextMenu<T> contextMenu) {
        Set<Component> separators = new HashSet<>();
        Component last = null;
        for (Component item : contextMenu.getChildren().collect(Collectors.toList())) {
            if (last != null && isSeparator(item) && isSeparator(last)) {
                separators.add(last);
            }
            last = item;
        }
        if (isSeparator(last)) separators.add(last);

        separators.forEach(contextMenu::remove);
    }

    default boolean isSeparator(Component component) {
        return component != null && "hr".equals(component.getElement().getTag());
    }
}
