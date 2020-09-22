package org.bklab.crud;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import org.bklab.export.data.ColumnDataBuilder;
import org.bklab.export.grid.GridColumnDataBuilderFactory;
import org.bklab.export.xlsx.ExcelDataExporter;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.pagination.PageSwitchEvent;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.components.pagination.layout.MiddleCustomPaginationLayout;
import org.bklab.flow.components.textfield.KeywordField;
import org.bklab.flow.dialog.DownloadDialog;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.util.function.EmptyFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@CssImport("./styles/org/bklab/component/crud/fluent-crud-view.css")
public abstract class FluentCrudView<T, G extends Grid<T>> extends VerticalLayout {

    private final static String CLASS_NAME = "fluent-crud-view";
    private static final Logger logger = LoggerFactory.getLogger(FluentCrudView.class);
    protected final Map<String, Supplier<Object>> parameterMap = new LinkedHashMap<>();
    protected final ToolBar header = new ToolBar();
    protected final Div content = new Div();
    protected final Div footer = new Div();
    protected final List<T> entities = new ArrayList<>();
    protected final Map<String, Predicate<T>> inMemoryEntityFilter = new LinkedHashMap<>();
    protected final Pagination pagination = new Pagination().onePageSize(20).limit(10).customLayout(new MiddleCustomPaginationLayout());
    protected final G grid = createGrid();
    private final List<Consumer<Exception>> exceptionConsumers = new ArrayList<>();
    protected final Button searchButton = new FluentButton(VaadinIcon.SEARCH, "查询").primary().asFactory().clickListener(e -> reloadGridData()).get();
    protected boolean hasGridMenu = true;
    protected boolean hasPagination = true;
    private final List<FluentCrudMenuButton<T, G>> menuButtons = new ArrayList<>();
    protected final List<Consumer<List<T>>> afterReloadListeners = new ArrayList<>();
    private final List<T> inMemoryFilteredEntities = new ArrayList<>();
    protected final EmptyLayout emptyLayout = new EmptyLayout("暂无数据");

    public FluentCrudView() {
        emptyLayout.setVisible(false);
        getStyle().set("padding", "0.5em").set("background-color", "white");
        addClassName(CLASS_NAME);
        header.addClassName(CLASS_NAME + "__header");
        content.addClassName(CLASS_NAME + "__content");
        footer.addClassName(CLASS_NAME + "__footer");
        emptyLayout.addClassName(CLASS_NAME + "__empty");
        add(header, content, footer);
        grid.addClassName(CLASS_NAME + "__grid");
        addExceptionConsumer(e -> new ErrorDialog(e).header("错误").build().open());
        addExceptionConsumer(e -> logger.error("错误", e));
        emptyLayout.getElement().getStyle().set("flex-grow", "1");
        emptyLayout.setHeight("calc(100% - 5em)");
    }

    protected FluentCrudView<T, G> addRefreshIconButton() {
        header.right(new FluentButton(VaadinIcon.REFRESH).link().clickListener(e -> reloadGridData()));
        return this;
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

    public FluentCrudView<T, G> headerVisible(boolean visible) {
        header.setVisible(visible);
        if (visible) content.removeClassName("fluent-crud-view__no_header");
        else content.addClassName("fluent-crud-view__no_header");
        return this;
    }

    public FluentCrudView<T, G> enableExport() {
        PageTitle annotation = getClass().getAnnotation(PageTitle.class);
        return annotation != null ? enableExport(annotation.value()) : enableExport("导出");
    }

    public FluentCrudView<T, G> enableExport(String fileName) {
        return enableExport(fileName, fileName);
    }

    public FluentCrudView<T, G> enableExport(String fileName, String excelTitle) {
        return enableExport(fileName, excelTitle, EmptyFunctions.emptyConsumer(), EmptyFunctions.emptyConsumer());
    }

    public FluentCrudView<T, G> enableExport(String fileName, String excelTitle, Map<String, String> keyHeaderMap) {
        return enableExport(fileName, excelTitle, factory -> factory.headers(keyHeaderMap), EmptyFunctions.emptyConsumer());
    }

    public FluentCrudView<T, G> enableExport(String fileName, String excelTitle,
                                             Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                             Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        header.right(createExportButton(fileName, excelTitle, builderFactoryConsumer, columnDataBuilderConsumer));
        return this;
    }

    public Button createExportButton(String fileName, String excelTitle,
                                     Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                     Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        GridColumnDataBuilderFactory<T> factory = new GridColumnDataBuilderFactory<>(grid);
        builderFactoryConsumer.accept(factory);
        ColumnDataBuilder<T> builder = factory.createBuilder();
        columnDataBuilderConsumer.accept(builder);

        return new FluentButton(VaadinIcon.EXTERNAL_LINK, "导出").clickListener(e ->
                new DownloadDialog(fileName + "导出完毕，请下载。", new ExcelDataExporter<>(builder)
                        .createStreamFactory(fileName + "-" + DateTimeFormatter.ofPattern("uuuuMMdd_HHmmss")
                                .format(LocalDateTime.now()) + ".xlsx", excelTitle, entities)
                ).build().open()
        );
    }

    public FluentCrudView<T, G> noPagination() {
        this.hasPagination = false;
        return this;
    }

    public FluentCrudView<T, G> hasPagination() {
        this.hasPagination = true;
        return this;
    }

    public FluentCrudView<T, G> toggleEmpty() {
        if (grid != null) {
            grid.getStyle().remove("height");
            grid.getStyle().remove("min-height");
            grid.setHeightByRows(true);
        }
        emptyLayout.setVisible(true);
        return this;
    }

    public FluentCrudView<T, G> addMenuColumn(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.ELLIPSIS_DOTS_H.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get(), menuEntityBiConsumer);
    }

    protected BiPredicate<T, T> sameEntityBiPredicate = Objects::equals;

    public BiPredicate<T, T> getSameEntityBiPredicate() {
        if (sameEntityBiPredicate == null) this.sameEntityBiPredicate = Objects::equals;
        return sameEntityBiPredicate;
    }

    public FluentCrudView<T, G> setSameEntityBiPredicate(BiPredicate<T, T> sameEntityBiPredicate) {
        this.sameEntityBiPredicate = Objects.requireNonNull(sameEntityBiPredicate, "sameEntityBiPredicate is null");
        return this;
    }

    public FluentCrudView<T, G> addMenuColumn(Supplier<Button> buttonSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        Grid.Column<T> column = grid.addComponentColumn(entity -> {
            Button button = buttonSupplier.get();
            ContextMenu contextMenu = new ContextMenu(button);
            contextMenu.addOpenedChangeListener(e -> {
                if (e.isOpened()) {
                    if (contextMenu.getItems().isEmpty()) {
                        menuEntityBiConsumer.safeBuild(this, contextMenu, entity);
                        contextMenu.setVisible(true);
                    }
                    grid.select(entity);
                }
            });
            FluentCrudMenuButton<T, G> crudMenuButton = new FluentCrudMenuButton<>(this, entity, button, contextMenu, menuEntityBiConsumer);
            menuButtons.add(crudMenuButton);
            contextMenu.setOpenOnClick(true);
            menuEntityBiConsumer.safeBuild(this, contextMenu, entity);
            return button;
        }).setHeader("操作").setKey("operationButton").setSortable(false)
                .setTextAlign(ColumnTextAlign.CENTER).setAutoWidth(true).setFrozen(true);
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
        menuButtons.stream().filter(m -> m.isThisEntity(entity, sameEntityBiPredicate))
                .collect(Collectors.toList()).forEach(menuButtons::remove);
        return this;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        header.right(searchButton);
        content.remove(grid, emptyLayout);
        content.add(grid, emptyLayout);
        if (hasPagination) {
            footer.add(pagination);
        } else {
            footer.setVisible(false);
            content.getStyle().set("border-bottom", "none");
        }
        pagination.pageSwitchEvent(createPageSwitchEvent());

        reloadGridData();
    }

    public void insertEntity(T entity) {
        this.entities.add(entity);
        this.inMemoryFilteredEntities.add(entity);
        this.pagination.totalData(this.entities.size()).build();
    }

    public void deleteEntity(T entity) {
        this.entities.remove(entity);
        this.inMemoryFilteredEntities.remove(entity);
        this.pagination.totalData(this.entities.size()).build();
        this.removeContextMenu(entity);
    }

    public void deleteEntity(T entity, BiPredicate<T, T> isEquals) {
        List<T> instance = this.entities.stream().filter(t -> isEquals.test(t, entity)).collect(Collectors.toList());
        this.entities.removeAll(instance);
        this.inMemoryFilteredEntities.removeAll(entities);
        this.pagination.totalData(this.entities.size()).build();
        this.removeContextMenu(entity, isEquals == null ? getSameEntityBiPredicate() : isEquals);
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
        List<T> instances = collection.stream().map(t -> isEquals.test(t) ? entity : t).collect(Collectors.toList());
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
        return (currentPageNumber, pageSize, lastPageNumber, isFromClient) -> grid.setItems(hasPagination
                ? this.inMemoryFilteredEntities.stream().skip((Math.max(Math.min(currentPageNumber,
                this.inMemoryFilteredEntities.size() / pageSize + 1), 1) - 1) * pageSize).limit(pageSize).collect(Collectors.toList())
                : inMemoryFilteredEntities
        );
    }

    protected FluentCrudView<T, G> addParameter(String name, HasValue<?, ?> hasValue) {
        return addParameter(name, hasValue, s -> s instanceof String ? ((String) s).trim().isEmpty() ? null : ((String) s).trim() : s);
    }

    protected <V> FluentCrudView<T, G> addParameter(String name, HasValue<?, V> hasValue, Function<V, ?> mapValue) {
        parameterMap.put(name, () -> Optional.ofNullable(hasValue.getValue()).map(mapValue).orElse(null));
        return this;
    }

    protected KeywordField addKeywordField() {
        KeywordField keywordField = new KeywordField((c, v) -> reloadGridData());
        parameterMap.put("keyword", () -> Optional.ofNullable(keywordField.getValue()).map(k -> k.trim().isEmpty() ? null : k.trim()).orElse(null));
        return keywordField;
    }

    protected FluentCrudView<T, G> addKeywordFieldToLeft() {
        header.left(addKeywordField());
        return this;
    }

    protected FluentCrudView<T, G> addKeywordFieldToMiddle() {
        header.middle(addKeywordField());
        return this;
    }

    protected FluentCrudView<T, G> addKeywordFieldToRight() {
        header.right(addKeywordField());
        return this;
    }

    public void reloadGridDataInMemory() {
        List<T> entities = this.entities.stream().filter(t ->
                inMemoryEntityFilter.values().stream().filter(Objects::nonNull).allMatch(p -> p.test(t))
        ).collect(Collectors.toList());
        this.inMemoryFilteredEntities.clear();
        this.inMemoryFilteredEntities.addAll(entities);
        pagination.totalData(entities.size()).build();
        if (entities.isEmpty()) toggleEmpty();
        else {
            grid.setHeightByRows(false);
            grid.setSizeFull();
            emptyLayout.setVisible(false);
        }
        afterReloadListeners.forEach(a -> a.accept(entities));
    }

    public void reloadGridData() {
        this.entities.clear();
        Map<String, Object> map = new LinkedHashMap<>();
        parameterMap.forEach((k, v) -> Optional.ofNullable(v).map(Supplier::get).ifPresent(v1 -> map.put(k, v1)));
        Optional.ofNullable(queryEntities(map)).ifPresent(this.entities::addAll);
        this.reloadGridDataInMemory();
    }
}
