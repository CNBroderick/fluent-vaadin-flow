package org.bklab.crud.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridDataView;
import com.vaadin.flow.component.grid.dataview.GridLazyDataView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.bklab.flow.factory.ButtonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FluentComponentGrid<T> extends Grid<T> {

    private static final Logger logger = LoggerFactory.getLogger(FluentComponentGrid.class);
    private final ListDataProvider<T> listDataProvider = new ListDataProvider<>(new ArrayList<>());
    private final Map<String, FluentColumnRender<T, ?>> columnRender = new LinkedHashMap<>();
    private final Map<Component, FluentColumnRender<T, ?>> componentMap = new LinkedHashMap<>();
    private final List<FluentGridItem<T, ?>> gridItems = new ArrayList<>();
    private final Map<T, Binder<T>> binderMap = new LinkedHashMap<>();
    private final Map<T, Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>>> entityRowMap = new LinkedHashMap<>();

    public FluentComponentGrid() {
        setHeightByRows(true);
        setWidthFull();
        setMinHeight("200px");
        super.setItems(listDataProvider);
    }

    public <C extends Component> FluentComponentGrid<T> add(FluentColumnRender<T, C> fluentColumnRender) {
        columnRender.put(fluentColumnRender.getId(), fluentColumnRender);
        addComponentColumn(entity -> getComponent(entity, fluentColumnRender))
                .setKey(fluentColumnRender.getId()).setTextAlign(ColumnTextAlign.CENTER)
                .setHeader(fluentColumnRender.getName()).setAutoWidth(true);
        return this;
    }

    @SafeVarargs
    public final <C extends Component> FluentComponentGrid<T> add(FluentColumnRender<T, C>... fluentColumnRender) {
        for (FluentColumnRender<T, C> render : fluentColumnRender) {
            this.add(render);
        }
        return this;
    }

    public FluentComponentGrid<T> addDeleteColumn() {
        return add(new FluentColumnRender<>("delete", "操作", entity ->
                new ButtonFactory().lumoSmall().lumoIcon().lumoTertiaryInline()
                        .icon(VaadinIcon.TRASH).clickListener(e -> remove(entity)).get()));
    }

    private <C extends Component> Component getComponent(T entity, FluentColumnRender<T, C> fluentColumnRender) {
        Component component = Optional.ofNullable(entityRowMap.get(entity))
                .map(c -> c.get(fluentColumnRender))
                .map(FluentGridItem::getComponent).orElse(null);

        if (component != null) {
            component.getElement().removeFromTree();
            return component;
        }

        return FluentGridItem.registered(entity, fluentColumnRender, this.gridItems, this.entityRowMap, this.binderMap);
    }

    public <C extends Component> C getRowComponent(T currentEntity, String id) {
        return Optional.ofNullable(this.<C>getRowItem(currentEntity, id)).map(FluentGridItem::getComponent).orElse(null);
    }

    public <C extends Component> FluentGridItem<T, C> getRowItem(T currentEntity, String id) {
        try {
            //noinspection unchecked
            return (FluentGridItem<T, C>) Optional.ofNullable(getRow(currentEntity))
                    .map(c -> c.get(columnRender.get(id))).orElse(null);
        } catch (Exception e) {
            logger.error("获取上一列组件失败。", e);
            return null;
        }
    }

    public <C extends Component> Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>> getRow(T currentEntity) {
        return entityRowMap.get(currentEntity);
    }

    public <C extends Component> Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>> getRow(int row) {
        try {
            return entityRowMap.get(new ArrayList<>(listDataProvider.getItems()).get(row));
        } catch (Exception e) {
            return null;
        }
    }

    public <C extends Component> C getPreviousRowComponent(T currentEntity, String id) {
        return getRowComponent(getPreviousRowEntity(currentEntity), id);
    }

    public <C extends Component> FluentGridItem<T, C> getPreviousRowItem(T currentEntity, String id) {
        return this.getRowItem(getPreviousRowEntity(currentEntity), id);
    }

    public <C extends Component> Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>> getPreviousRow(T currentEntity) {
        return getRow(getPreviousRowEntity(currentEntity));
    }

    public <C extends Component> C getNextRowComponent(T currentEntity, String id) {
        return getRowComponent(getNextRowEntity(currentEntity), id);
    }

    public <C extends Component> FluentGridItem<T, C> getNextRowItem(T currentEntity, String id) {
        return getRowItem(getNextRowEntity(currentEntity), id);
    }

    public <C extends Component> Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>> getNextRow(T currentEntity) {
        return getRow(getNextRowEntity(currentEntity));
    }

    public T getPreviousRowEntity(T currentEntity) {
        ArrayList<T> ts = new ArrayList<>(getDataProvider().getItems());
        int i = ts.indexOf(currentEntity);
        return i > 0 ? ts.get(i - 1) : null;
    }

    public T getNextRowEntity(T currentEntity) {
        ArrayList<T> ts = new ArrayList<>(getDataProvider().getItems());
        int i = ts.indexOf(currentEntity);
        return i > -1 && i < ts.size() - 2 ? ts.get(i + 1) : null;
    }

    public List<T> getEntities() {
        gridItems.forEach(FluentGridItem::writeEntity);
        return new ArrayList<>(listDataProvider.getItems());
    }

    public T getFirstEntity() {
        ArrayList<T> ts = new ArrayList<>(listDataProvider.getItems());
        return ts.isEmpty() ? null : ts.get(0);
    }

    public T getFirstEntityIfAbsent(Supplier<T> entitySupplier) {
        T firstEntity = getFirstEntity();
        if (firstEntity == null) {
            firstEntity = entitySupplier.get();
            add(firstEntity);
        }
        return firstEntity;
    }

    public T getLastEntityIfAbsent(Supplier<T> entitySupplier) {
        T lastEntity = getLastEntity();
        if (lastEntity == null) {
            lastEntity = entitySupplier.get();
            add(lastEntity);
        }
        return lastEntity;
    }

    public T getLastEntity() {
        ArrayList<T> ts = new ArrayList<>(listDataProvider.getItems());
        return ts.isEmpty() ? null : ts.get(ts.size() - 1);
    }

    public T getEntity(int row) {
        try {
            return new ArrayList<>(listDataProvider.getItems()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public void refreshAll() {
        listDataProvider.refreshAll();
    }

    public <C extends Component> FluentColumnRender<T, C> getColumnProvider(String key) {
        //noinspection unchecked
        return (FluentColumnRender<T, C>) columnRender.get(key);
    }

    public FluentComponentGrid<T> add(T entity) {
        listDataProvider.getItems().add(entity);
        listDataProvider.refreshAll();
        return this;
    }

    public FluentComponentGrid<T> remove(T entity) {
        listDataProvider.getItems().remove(entity);
        listDataProvider.refreshAll();
        return this;
    }

    @Override
    public ListDataProvider<T> getDataProvider() {
        return listDataProvider;
    }

    @Override
    public GridDataView<T> setItems(DataProvider<T, ?> dataProvider) {
        throw new RuntimeException("请使用 building.ui.components.grid.FluentComponentGrid.setItems(com.vaadin.flow.data.provider.ListDataProvider<T>)");
    }

    @Override
    public GridListDataView<T> setItems(ListDataProvider<T> dataProvider) {
        this.listDataProvider.getItems().clear();
        this.listDataProvider.getItems().addAll(dataProvider.getItems());
        return getListDataView();
    }

    @Override
    public GridLazyDataView<T> setItems(BackEndDataProvider<T, Void> dataProvider) {
        throw new RuntimeException("请使用 building.ui.components.grid.FluentComponentGrid.setItems(com.vaadin.flow.data.provider.ListDataProvider<T>)");
    }

    public List<T> save() {
        gridItems.forEach(FluentGridItem::writeEntity);
        return new ArrayList<>(listDataProvider.getItems());
    }

    public void writeComponent() {
        gridItems.forEach(FluentGridItem::writeComponent);
    }

    /**
     * @return error message
     */
    public List<String> validateAll() {
        List<BindingValidationStatus<?>> a = new ArrayList<>();
        binderMap.forEach((e, b) -> a.addAll(b.validate().getFieldValidationErrors()));
        return a.stream().filter(BindingValidationStatus::isError).map(s -> s.getMessage().orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
