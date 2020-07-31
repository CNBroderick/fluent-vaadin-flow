package org.bklab.crud;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.components.pagination.PageSwitchEvent;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.components.pagination.layout.MiddleCustomPaginationLayout;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@CssImport("./styles/components/crud/fluent-crud-view.css")
public abstract class FluentCrudView<T, G extends Grid<T>> extends VerticalLayout {

    private final static String CLASS_NAME = "fluent-crud-view";
    private static final Logger logger = LoggerFactory.getLogger(FluentCrudView.class);
    protected final ToolBar header = new ToolBar();
    protected final Div content = new Div();
    protected final Div footer = new Div();
    protected final List<T> entities = new ArrayList<>();
    protected final Pagination pagination = new Pagination().onePageSize(20).limit(10).customLayout(new MiddleCustomPaginationLayout());
    protected final G grid = createGrid();
    protected final Map<String, Supplier<Object>> parameterMap = new LinkedHashMap<>();
    protected final Button searchButton = new ButtonFactory("查询").icon(VaadinIcon.SEARCH)
            .lumoSmall().lumoPrimary().clickListener(e -> reloadGridData()).get();
    private final List<Consumer<Exception>> exceptionConsumers = new ArrayList<>();
    protected boolean hasGridMenu = true;
    protected boolean hasPagination = true;

    public FluentCrudView() {
        getStyle().set("padding", "0.5em").set("background-color", "white");
        addClassName(CLASS_NAME);
        header.addClassName(CLASS_NAME + "__header");
        content.addClassName(CLASS_NAME + "__content");
        footer.addClassName(CLASS_NAME + "__footer");
        add(header, content, footer);
        grid.addClassName(CLASS_NAME + "__grid");
        addExceptionConsumer(e -> new ErrorDialog(e).header("错误").build().open());
        addExceptionConsumer(e -> logger.error("错误", e));
    }

    protected FluentCrudView<T, G> addExceptionConsumer(Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumers.add(exceptionConsumer);
        return this;
    }

    public Consumer<Exception> getExceptionConsumer() {
        return exception -> exceptionConsumers.forEach(a -> a.accept(exception));
    }

    public abstract G createGrid();

    public abstract Collection<T> queryEntities(Map<String, Object> parameters);

    public FluentCrudView<T, G> noPagination() {
        this.hasPagination = false;
        return this;
    }

    public FluentCrudView<T, G> hasPagination() {
        this.hasPagination = true;
        return this;
    }

    public FluentCrudView<T, G> toggleEmpty() {
        content.removeAll();
        EmptyLayout emptyLayout = new EmptyLayout();
        emptyLayout.getElement().getStyle().set("flex-grow", "1");
        if (grid != null) {
            grid.setHeightByRows(true);
            content.add(grid);
        }
        content.add(emptyLayout);
        return this;
    }

    public FluentCrudView<T, G> addMenuColumn(BiConsumer<ContextMenu, T> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.COG_O.create())
                .lumoTertiary().lumoIcon().lumoSmall().get(), menuEntityBiConsumer);
    }

    public FluentCrudView<T, G> addMenuColumn(Supplier<Button> buttonSupplier, BiConsumer<ContextMenu, T> menuEntityBiConsumer) {
        grid.addComponentColumn(entity -> {
            Button button = buttonSupplier.get();
            ContextMenu contextMenu = new ContextMenu(button);
            button.addClickListener(e -> {
                grid.select(entity);
                contextMenu.removeAll();
                menuEntityBiConsumer.accept(contextMenu, entity);
            });
            contextMenu.setOpenOnClick(true);
            return button;
        }).setHeader("操作").setKey("operationButton").setSortable(false)
                .setTextAlign(ColumnTextAlign.CENTER).setAutoWidth(true).setFrozen(true);
        return this;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        header.right(searchButton);
        content.add(grid == null ? new EmptyLayout() : grid);
        if (hasPagination) {
            footer.add(pagination);
        } else {
            footer.setVisible(false);
            content.getStyle().set("border-bottom", "none");
        }
        pagination.pageSwitchEvent(createPageSwitchEvent());

        reloadGridData();
    }

    protected void insertEntity(T entity) {
        this.entities.add(entity);
        this.pagination.totalData(this.entities.size()).build();
    }

    protected void deleteEntity(T entity) {
        this.entities.remove(entity);
        this.pagination.totalData(this.entities.size()).build();
    }

    protected void updateEntity(T entity) {
        this.updateEntity(entity, t -> t.equals(entity));
    }

    protected void updateEntity(T oldEntity, T newEntity) {
        this.updateEntity(newEntity, oldEntity::equals);
    }

    protected void updateEntity(T entity, Predicate<T> isEquals) {
        List<T> instances = entities.stream().map(t -> isEquals.test(t) ? entity : t).collect(Collectors.toList());
        this.entities.clear();
        this.entities.addAll(instances);
        this.pagination.totalData(this.entities.size()).build();
    }

    public FluentCrudView<T, G> buildGridMenu(BiConsumer<GridContextMenu<T>, T> menuEntityBiConsumer) {
        GridContextMenu<T> gridContextMenu = grid.addContextMenu();
        gridContextMenu.setDynamicContentHandler(entity -> {
            if (entity == null) {
                return false;
            }
            gridContextMenu.removeAll();
            grid.select(entity);
            menuEntityBiConsumer.accept(gridContextMenu, entity);
            return true;
        });

        return this;
    }

    protected PageSwitchEvent createPageSwitchEvent() {
        return (currentPageNumber, pageSize, lastPageNumber, isFromClient) -> grid.setItems(hasPagination
                ? this.entities.stream().skip((Math.max(Math.min(currentPageNumber, entities.size() / pageSize + 1), 1) - 1) * pageSize).limit(pageSize).collect(Collectors.toList())
                : entities
        );
    }

    protected void reloadGridData() {
        this.entities.clear();
        Map<String, Object> map = new LinkedHashMap<>();
        parameterMap.forEach((k, v) -> Optional.ofNullable(v).map(Supplier::get).ifPresent(v1 -> map.put(k, v1)));
        Optional.ofNullable(queryEntities(map)).ifPresent(this.entities::addAll);
        pagination.totalData(this.entities.size()).build();
        if (entities.isEmpty()) toggleEmpty();
        else {
            grid.setHeightByRows(false);
            grid.setSizeFull();
            content.removeAll();
            content.add(grid);
        }
    }
}
