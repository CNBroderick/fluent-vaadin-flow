package org.bklab.crud;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;

@CssImport("./styles/components/crud/fluent-crud-view.css")
public abstract class FluentCrudView<T, G extends Grid<G>> extends VerticalLayout {

    private final static String CLASS_NAME = "fluent-crud-view";
    private final ToolBar header = new ToolBar();
    private final Div content = new Div();
    private final Div footer = new Div();
    private G grid;

    public FluentCrudView() {
        getStyle().set("padding", "0.5em").set("background-color", "white");
        addClassName(CLASS_NAME);
        header.addClassName(CLASS_NAME + "__header");
        content.addClassName(CLASS_NAME + "__content");
        footer.addClassName(CLASS_NAME + "__footer");
        add(header, content, footer);
    }

    public abstract G createGrid();

    public abstract Pagination createPagination();

    public void buildContextMenu(ContextMenu contextMenu){

    }

    public abstract void build(ContextMenu contextMenu);

    public FluentCrudView<T, G> toggleEmpty() {
        content.removeAll();
        if (grid != null) {
            grid.setHeight(grid.getHeaderRows().size() * 3 + "em");
            content.add(grid);
        }
        content.add(new EmptyLayout());
        return this;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        G grid = createGrid();
        content.add(grid == null ? new EmptyLayout() : grid);
        Pagination pagination = createPagination();
        if(pagination != null) {
            footer.add(pagination);
        }
    }
}
