/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-18 11:56:06
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.crud.FluentCrudView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.event.SortEvent;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.shared.Registration;
import org.bklab.crud.core.ICrudViewExcelExportSupporter;
import org.bklab.crud.core.ICrudViewKeywordSupporter;
import org.bklab.crud.core.IFluentCrudViewCommonField;
import org.bklab.crud.menu.FluentCrudMenuButton;
import org.bklab.crud.menu.ICrudViewMenuColumnSupporter;
import org.bklab.crud.menu.IFluentGridMenuBuilder;
import org.bklab.crud.menu.IOperationMenuComponentSupporter;
import org.bklab.flow.base.HasReturnThis;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.pagination.PageSwitchEvent;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.components.pagination.layout.MiddleCustomPaginationLayout;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.util.url.QueryParameterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("UnusedReturnValue")
@CssImport("./styles/org/bklab/component/crud/fluent-crud-view.css")
public abstract class FluentCrudView<T, G extends Grid<T>> extends VerticalLayout implements
        IFluentCrudViewCommonField<T, G, FluentCrudView<T, G>>,
        ICrudViewMenuColumnSupporter<T, G, FluentCrudView<T, G>>,
        ICrudViewExcelExportSupporter<T, G, FluentCrudView<T, G>>,
        IOperationMenuComponentSupporter<T, G, FluentCrudView<T, G>>,
        ICrudViewKeywordSupporter<T, G, FluentCrudView<T, G>>,
        HasReturnThis<FluentCrudView<T, G>>,
        BeforeEnterObserver {

    protected final List<Consumer<QueryParameterUtil>> effectQueryParameterUtils = new ArrayList<>();

    private final static String CLASS_NAME = "fluent-crud-view";
    private static final Logger logger = LoggerFactory.getLogger(FluentCrudView.class);
    protected final Map<String, Supplier<Object>> parameterMap = new LinkedHashMap<>();
    protected final ToolBar header = new ToolBar();
    protected final Div content = new Div();
    protected final ToolBar footer = new ToolBar();
    protected final List<T> entities = new ArrayList<>();
    protected final List<T> inMemoryFilteredEntities = new ArrayList<>();
    protected final Map<String, Predicate<T>> inMemoryEntityFilter = new LinkedHashMap<>();
    protected final Pagination pagination = createPagination();
    protected final List<Consumer<List<T>>> afterReloadListeners = new ArrayList<>();
    private final List<Consumer<Exception>> exceptionConsumers = new ArrayList<>();
    private final List<FluentCrudMenuButton<T, G>> menuButtons = new ArrayList<>();
    protected final EmptyLayout emptyLayout = new EmptyLayout("暂无数据");
    protected FluentButton searchButton = new FluentButton(VaadinIcon.SEARCH, "查询", e -> reloadGridDataAndRecalculateColumnWidths()).primary();
    protected boolean hasGridMenu = true;
    protected boolean hasPagination = true;
    protected Function<T, Collection<T>> childProvider = null;
    protected Function<Collection<T>, DataProvider<T, Void>> dataProviderCreator = null;
    protected BiPredicate<T, T> sameEntityBiPredicate = Objects::equals;
    protected final G grid;
    protected QueryParameterUtil queryParameterUtil;

    public FluentCrudView() {
        emptyLayout.setVisible(false);
        getStyle().set("padding", "0.5em 0 0").set("background-color", "white");
        addClassName(CLASS_NAME);
        header.addClassName(CLASS_NAME + "__header");
        content.addClassName(CLASS_NAME + "__content");
        footer.addClassName(CLASS_NAME + "__footer");
        emptyLayout.addClassName(CLASS_NAME + "__empty");
        add(header, content, footer);
        emptyLayout.getElement().getStyle().set("flex-grow", "1");
        emptyLayout.setHeight("calc(100% - 5em)");
        addExceptionConsumer(e -> new ErrorDialog(e).header("错误").build().open());
        addExceptionConsumer(e -> logger.error(Optional.ofNullable(e.getLocalizedMessage()).orElse("错误"), e));

        beforeInitGrid();
        this.grid = createGrid();
        grid.addClassName(CLASS_NAME + "__grid");
    }

    public Pagination createPagination() {
        return new Pagination().onePageSize(20).limit(10).customLayout(new MiddleCustomPaginationLayout());
    }

    protected void beforeInitGrid() {
    }

    private Future<Void> future = null;

    public FluentCrudView<T, G> noRefreshIconButton() {
        searchButton.setVisible(false);
        return this;
    }

    @Deprecated
    public FluentCrudView<T, G> addRefreshIconButton() {
        return useRefreshIconButton();
    }

    public FluentCrudView<T, G> addExceptionConsumer(Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumers.add(exceptionConsumer);
        return this;
    }

    public Consumer<Exception> getExceptionConsumer() {
        return exception -> exceptionConsumers.forEach(a -> a.accept(exception));
    }

    public abstract G createGrid();

    public abstract Collection<T> queryEntities(Map<String, Object> parameters);

    public FluentCrudView<T, G> headerVisible(boolean visible) {
        header.setVisible(visible);
        if (visible) content.removeClassName("fluent-crud-view__no_header");
        else content.addClassName("fluent-crud-view__no_header");
        return this;
    }

    public FluentCrudView<T, G> watch(HasValue<?, ?> hasValue) {
        Registration registration = hasValue.addValueChangeListener(e -> reloadGridData());
        addDetachListener(e -> registration.remove());
        return this;
    }

    public FluentCrudView<T, G> noPagination() {
        this.hasPagination = false;
        return this;
    }

    public FluentCrudView<T, G> hasPagination() {
        this.hasPagination = true;
        return this;
    }

    public FluentCrudView<T, G> toggleEmpty(boolean empty) {
        return empty ? toggleEmpty() : toggleNotEmpty();
    }

    private FluentCrudView<T, G> toggleEmpty() {
        if (grid != null) {
            if (!grid.getFooterRows().isEmpty()) {
                return toggleNotEmpty();
            }

            grid.getStyle().remove("height");
            grid.getStyle().remove("min-height");
            grid.setAllRowsVisible(true);
            grid.setHeight("unset");
            if (grid instanceof TreeGrid) {
                grid.setItems(new TreeDataProvider<>(new TreeData<>()));
            } else {
                grid.setItems(new ListDataProvider<>(Collections.emptyList()));
            }
        }
        footer.setVisible(false);
        emptyLayout.setVisible(true);
        return this;
    }

    private FluentCrudView<T, G> toggleNotEmpty() {
        grid.setAllRowsVisible(false);
        grid.setHeightFull();
        emptyLayout.setVisible(false);
        footer.setVisible(true);
        return this;
    }

    public BiPredicate<T, T> getSameEntityBiPredicate() {
        if (sameEntityBiPredicate == null) this.sameEntityBiPredicate = Objects::equals;
        return sameEntityBiPredicate;
    }

    public FluentCrudView<T, G> setSameEntityBiPredicate(BiPredicate<T, T> sameEntityBiPredicate) {
        this.sameEntityBiPredicate = Objects.requireNonNull(sameEntityBiPredicate, "sameEntityBiPredicate is null");
        return this;
    }

    public FluentCrudView<T, G> reloadContextMenu(T entity, Predicate<T> samePredicate) {
        for (FluentCrudMenuButton<T, G> menuButton : menuButtons) {
            menuButton.reload(entity, samePredicate != null
                                      ? samePredicate : t -> getSameEntityBiPredicate().test(entity, t));
        }
        return this;
    }

    public FluentCrudView<T, G> removeContextMenu(T entity) {
        return this.removeContextMenu(entity, sameEntityBiPredicate);
    }

    public FluentCrudView<T, G> removeContextMenu(T entity, BiPredicate<T, T> sameEntityBiPredicate) {
        menuButtons.stream().filter(m -> m.isThisEntity(entity, sameEntityBiPredicate)).toList().forEach(menuButtons::remove);
        return this;
    }

    private void handleGridGridSortOrderSortEvent(SortEvent<Grid<T>, GridSortOrder<T>> gridGridSortOrderSortEvent) {
        List<T> filteredEntities = inMemoryFilteredEntities.stream().sorted((o1, o2) -> {
            for (GridSortOrder<T> order : gridGridSortOrderSortEvent.getSortOrder()) {
                Grid.Column<T> sorted = order.getSorted();
                int compare = sorted.getComparator(order.getDirection()).compare(o1, o2);
                if (compare == 0) continue;
                return compare;
            }
            return 0;
        }).toList();
        inMemoryFilteredEntities.clear();
        inMemoryFilteredEntities.addAll(filteredEntities);
        pagination.refresh();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        this.queryParameterUtil = new QueryParameterUtil(beforeEnterEvent);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        header.right(searchButton);
        content.remove(grid, emptyLayout);
        content.add(grid, emptyLayout);
        if (hasPagination) {
            footer.middle(pagination);
        } else {
            footer.setVisible(false);
            content.getStyle().set("border-bottom", "none");
        }
        pagination.pageSwitchEvent(createPageSwitchEvent());
        grid.addSortListener(this::handleGridGridSortOrderSortEvent);

        this.effectQueryParameterUtils.forEach(a -> a.accept(queryParameterUtil));
        reloadGridData();
        onAfterAttach();
    }

    protected void onAfterAttach() {

    }

    public void insertEntity(T entity) {
        this.entities.add(entity);
        this.inMemoryFilteredEntities.add(entity);
        this.pagination.totalData(this.entities.size()).build();
        toggleNotEmpty();
    }

    public void deleteEntity(T entity) {
        this.entities.remove(entity);
        this.inMemoryFilteredEntities.remove(entity);
        this.pagination.totalData(this.entities.size()).build();
        this.removeContextMenu(entity);
        toggleEmpty(inMemoryFilteredEntities.isEmpty());
    }

    public void deleteEntity(T entity, BiPredicate<T, T> isEquals) {
        List<T> instance = this.entities.stream().filter(t -> isEquals.test(t, entity)).toList();
        this.entities.removeAll(instance);
        this.inMemoryFilteredEntities.removeAll(entities);
        this.pagination.totalData(this.entities.size()).build();
        this.removeContextMenu(entity, isEquals == null ? getSameEntityBiPredicate() : isEquals);
        toggleEmpty(inMemoryFilteredEntities.isEmpty());
    }

    public void updateEntity(T entity) {
        this.updateEntity(entity, t -> getSameEntityBiPredicate().test(t, entity));
    }

    public void updateEntity(T oldEntity, T newEntity) {
        this.updateEntity(newEntity, t -> getSameEntityBiPredicate().test(oldEntity, t));
    }

    public void updateEntity(T entity, Predicate<T> isEquals) {
        replaceSameEntity(entities, entity, isEquals);
        replaceSameEntity(inMemoryFilteredEntities, entity, isEquals);
        this.reloadContextMenu(entity, isEquals);
        this.pagination.totalData(this.entities.size()).build();
    }

    private void replaceSameEntity(Collection<T> collection, T entity, Predicate<T> isEquals) {
        List<T> instances = collection.stream().map(t -> isEquals.test(t) ? entity : t).toList();
        collection.clear();
        collection.addAll(instances);
    }

    public FluentCrudView<T, G> buildGridMenu(IFluentGridMenuBuilder<T, G> menuEntityBiConsumer) {
        GridContextMenu<T> gridContextMenu = grid.addContextMenu();
        gridContextMenu.setDynamicContentHandler(entity -> {
            if (entity == null) {
                return false;
            }
            gridContextMenu.removeAll();
            grid.select(entity);
            menuEntityBiConsumer.safeBuild(this, gridContextMenu, entity);
            return true;
        });

        return this;
    }

    protected PageSwitchEvent createPageSwitchEvent() {
        return (currentPageNumber, pageSize, lastPageNumber, isFromClient) -> setGridItem(
                hasPagination ? this.inMemoryFilteredEntities.stream().skip((long) (Math.max(Math.min(currentPageNumber, this.inMemoryFilteredEntities.size()
                        / pageSize + 1), 1) - 1) * pageSize).limit(pageSize).collect(Collectors.toList()) : inMemoryFilteredEntities
        );
    }

    public FluentCrudView<T, G> setGridItem(Collection<T> items) {
        if (dataProviderCreator != null) {
            grid.setItems(dataProviderCreator.apply(items));
        } else if (grid instanceof TreeGrid) {
            //noinspection unchecked,rawtypes,rawtypes
            ((TreeGrid<?>) grid).setDataProvider((HierarchicalDataProvider) new TreeDataProvider<>((new TreeData<T>()).addItems(items, getChildProvider()::apply)));
        } else {
            grid.setItems(items);
        }
        return this;
    }

    protected Function<T, Collection<T>> getChildProvider() {
        return childProvider == null ? a -> Collections.emptyList() : childProvider;
    }

    public FluentCrudView<T, G> addParameter(String name, HasValue<?, ?> hasValue) {
        return addParameter(name, hasValue, s -> s instanceof String ? ((String) s).trim().isEmpty() ? null : ((String) s).trim() : s);
    }

    public <V> FluentCrudView<T, G> addParameter(String name, HasValue<?, V> hasValue, Function<V, ?> mapValue) {
        parameterMap.put(name, () -> Optional.ofNullable(hasValue.getValue()).map(mapValue).orElse(null));
        return this;
    }

    public void reloadGridDataInMemory() {
        List<T> entities = this.entities.stream().filter(t ->
                inMemoryEntityFilter.values().stream().filter(Objects::nonNull).allMatch(p -> p.test(t))
        ).collect(Collectors.toList());
        this.inMemoryFilteredEntities.clear();
        this.inMemoryFilteredEntities.addAll(entities);
        pagination.totalData(entities.size()).build();
        toggleEmpty(entities.isEmpty());
        afterReloadListeners.forEach(a -> {
            try {
                a.accept(entities);
            } catch (Exception e) {
                logger.warn("afterReloadListeners throws an error.", e);
            }
        });
    }

    public FluentCrudView<T, G> useRefreshIconButton() {
        searchButton.reset().noPadding().link().iconOnly().asFactory()
                .visible(true).tooltip("刷新").icon(VaadinIcon.REFRESH.create()).text(null);
        return this;
    }

    public void reloadGridData() {
        this.entities.clear();
        Map<String, Object> map = new LinkedHashMap<>();
        parameterMap.forEach((k, v) -> Optional.ofNullable(v).map(Supplier::get).ifPresent(v1 -> map.put(k, v1)));
        Optional.ofNullable(queryEntities(map)).ifPresent(this.entities::addAll);
        this.reloadGridDataInMemory();
    }

    public void reloadGridDataAsynchronous() {
        this.entities.clear();
        Map<String, Object> map = new LinkedHashMap<>();
        parameterMap.forEach((k, v) -> Optional.ofNullable(v).map(Supplier::get).ifPresent(v1 -> map.put(k, v1)));
        UI ui = UI.getCurrent();
        if (ui == null) {
            Optional.ofNullable(queryEntities(map)).ifPresent(this.entities::addAll);
            this.reloadGridDataInMemory();
            return;
        }

        AtomicInteger interval = new AtomicInteger(-1);
        if (future != null && !future.isDone()) {
            future.cancel(true);
        }

        future = ui.access(() -> {
            interval.set(ui.getPollInterval());
            ui.setPollInterval(500);
            Optional.ofNullable(queryEntities(map)).ifPresent(this.entities::addAll);
            ui.setPollInterval(interval.get());
            future = null;
        });
        this.reloadGridDataInMemory();
    }

    public void reloadGridDataAndRecalculateColumnWidths() {
        reloadGridData();
        getGrid().recalculateColumnWidths();
    }

    public void switchEntityPage(Predicate<T> predicate) {
        IntStream.range(0, inMemoryFilteredEntities.size()).filter(i -> predicate.test(inMemoryFilteredEntities.get(i)))
                .findFirst().ifPresent(i -> pagination.setCurrentPage(i / pagination.getPageSize() + 1).refresh());
    }

    public FluentCrudView<T, G> setEntitiesDirectly(Collection<T> items) {
        entities.clear();
        entities.addAll(items);
        return this;
    }

    public Map<String, Predicate<T>> getInMemoryEntityFilter() {
        return inMemoryEntityFilter;
    }

    public Map<String, Supplier<Object>> getParameterMap() {
        return parameterMap;
    }

    @Override
    public ToolBar header() {
        return header;
    }

    @Override
    public Collection<T> getEntities() {
        return entities;
    }

    @Override
    public G getGrid() {
        return grid;
    }

    @Override
    public List<FluentCrudMenuButton<T, G>> getMenuButtons() {
        return menuButtons;
    }

    @Override
    public FluentCrudView<T, G> getCrudView() {
        return this;
    }
}
