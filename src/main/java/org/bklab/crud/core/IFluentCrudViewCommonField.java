package org.bklab.crud.core;

import com.vaadin.flow.component.grid.Grid;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.layout.ToolBar;

import java.util.Collection;

public interface IFluentCrudViewCommonField<T, G extends Grid<T>, C extends FluentCrudView<T, G>> {

    ToolBar header();

    Collection<T> getEntities();

    G getGrid();

    C getCrudView();
}
