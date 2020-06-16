package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.dnd.GridDragEndEvent;
import com.vaadin.flow.component.grid.dnd.GridDragStartEvent;
import com.vaadin.flow.component.grid.dnd.GridDropEvent;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.data.event.SortEvent;
import com.vaadin.flow.data.provider.DataGenerator;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.util.List;
import java.util.Map;

public class GridFactory<T> extends FlowFactory<Grid<T>, GridFactory<T>> implements
        HasStyleFactory<Grid<T>, GridFactory<T>>,
        HasDataProviderFactory<T, Grid<T>, GridFactory<T>>,
        HasSizeFactory<Grid<T>, GridFactory<T>>,
        FocusableFactory<Grid<T>, GridFactory<T>>,
        HasThemeFactory<Grid<T>, GridFactory<T>>,
        HasDataGeneratorsFactory<T, Grid<T>, GridFactory<T>>,
        SortEventSortNotifierFactory<Grid<T>, GridSortOrder<T>, GridFactory<T>> {
    public GridFactory() {
        this(new Grid<>());
    }

    public GridFactory(Grid<T> component) {
        super(component);
    }

    public GridFactory(int pageSize) {
        this(new Grid<>(pageSize));
    }

    public GridFactory(Class<T> beanType, boolean autoCreateColumns) {
        this(new Grid<>(beanType, autoCreateColumns));

    }

    public GridFactory(Class<T> beanType) {
        this(beanType, true);
    }

    public GridFactory<T> sort(List<GridSortOrder<T>> sort) {
        get().sort(sort);
        return this;
    }

    public GridFactory<T> select(T select) {
        get().select(select);
        return this;
    }

    public GridFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public GridFactory<T> columnResizeListener(ComponentEventListener<ColumnResizeEvent<T>> columnResizeListener) {
        get().addColumnResizeListener(columnResizeListener);
        return this;
    }

    public GridFactory<T> classNameGenerator(SerializableFunction<T, String> classNameGenerator) {
        get().setClassNameGenerator(classNameGenerator);
        return this;
    }

    public GridFactory<T> removeThemeVariants(GridVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return this;
    }

    public GridFactory<T> dragStartListener(ComponentEventListener<GridDragStartEvent<T>> dragStartListener) {
        get().addDragStartListener(dragStartListener);
        return this;
    }

    public GridFactory<T> selectionDragDetails(int draggedItemsCount, Map<String, String> dragData) {
        get().setSelectionDragDetails(draggedItemsCount, dragData);
        return this;
    }

    public GridFactory<T> columnReorderListener(ComponentEventListener<ColumnReorderEvent<T>> listener) {
        get().addColumnReorderListener(listener);
        return this;
    }

    public GridFactory<T> columnReorderingAllowed(boolean columnReorderingAllowed) {
        get().setColumnReorderingAllowed(columnReorderingAllowed);
        return this;
    }

    public GridFactory<T> selectionListener(SelectionListener<Grid<T>, T> listener) {
        get().addSelectionListener(listener);
        return this;
    }

    public GridFactory<T> recalculateColumnWidths() {
        get().recalculateColumnWidths();
        return this;
    }

    public GridFactory<T> detailsVisibleOnClick(boolean detailsVisibleOnClick) {
        get().setDetailsVisibleOnClick(detailsVisibleOnClick);
        return this;
    }

    public GridFactory<T> verticalScrollingEnabled(boolean verticalScrollingEnabled) {
        get().setVerticalScrollingEnabled(verticalScrollingEnabled);
        return this;
    }

    public GridFactory<T> itemClickListener(ComponentEventListener<ItemClickEvent<T>> itemClickListener) {
        get().addItemClickListener(itemClickListener);
        return this;
    }

    public GridFactory<T> dragDataGenerator(String type, SerializableFunction<T, String> dragDataGenerator) {
        get().setDragDataGenerator(type, dragDataGenerator);
        return this;
    }

    public GridFactory<T> itemDetailsRenderer(Renderer<T> itemDetailsRenderer) {
        get().setItemDetailsRenderer(itemDetailsRenderer);
        return this;
    }

    public GridFactory<T> itemDoubleClickListener(ComponentEventListener<ItemDoubleClickEvent<T>> itemDoubleClickListener) {
        get().addItemDoubleClickListener(itemDoubleClickListener);
        return this;
    }

    public GridFactory<T> dataGenerator(DataGenerator<T> dataGenerator) {
        get().addDataGenerator(dataGenerator);
        return this;
    }

    public GridFactory<T> sortListener(ComponentEventListener<SortEvent<Grid<T>, GridSortOrder<T>>> listener) {
        get().addSortListener(listener);
        return this;
    }

    public GridFactory<T> column(Renderer<T> renderer, String... sortingProperties) {
        get().addColumn(renderer, sortingProperties);
        return this;
    }

    public GridFactory<T> column(Renderer<T> column) {
        get().addColumn(column);
        return this;
    }

    public GridFactory<T> column(String column) {
        get().addColumn(column);
        return this;
    }

    public <V extends Comparable<? super V>> GridFactory<T> column(ValueProvider<T, V> valueProvider, String... sortingProperties) {
        get().addColumn(valueProvider, sortingProperties);
        return this;
    }

    public GridFactory<T> column(ValueProvider<T, ?> column) {
        get().addColumn(column);
        return this;
    }

    public GridFactory<T> setColumns(String... columns) {
        get().setColumns(columns);
        return this;
    }

    public <V extends Component> GridFactory<T> componentColumn(ValueProvider<T, V> componentColumn) {
        get().addComponentColumn(componentColumn);
        return this;
    }

    public GridFactory<T> columns(String... propertyNames) {
        get().addColumns(propertyNames);
        return this;
    }

    public GridFactory<T> appendHeaderRow() {
        get().appendHeaderRow();
        return this;
    }

    public GridFactory<T> sortableColumns(String... sortableColumns) {
        get().setSortableColumns(sortableColumns);
        return this;
    }

    public GridFactory<T> appendFooterRow() {
        get().appendFooterRow();
        return this;
    }

    public GridFactory<T> themeVariants(GridVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return this;
    }

    public GridFactory<T> prependHeaderRow() {
        get().prependHeaderRow();
        return this;
    }

    public GridFactory<T> prependFooterRow() {
        get().prependFooterRow();
        return this;
    }

    public GridFactory<T> asSingleSelect() {
        get().asSingleSelect();
        return this;
    }

    public GridFactory<T> asMultiSelect() {
        get().asMultiSelect();
        return this;
    }

    public GridFactory<T> deselectAll() {
        get().deselectAll();
        return this;
    }

    public GridFactory<T> pageSize(int pageSize) {
        get().setPageSize(pageSize);
        return this;
    }

    public GridFactory<T> selectionMode(Grid.SelectionMode selectionMode) {
        get().setSelectionMode(selectionMode);
        return this;
    }

    public GridFactory<T> deselect(T deselect) {
        get().deselect(deselect);
        return this;
    }

    @SafeVarargs
    public final GridFactory<T> removeColumns(Grid.Column<T>... removeColumns) {
        get().removeColumns(removeColumns);
        return this;
    }

    public GridFactory<T> removeColumnByKey(String removeColumnByKey) {
        get().removeColumnByKey(removeColumnByKey);
        return this;
    }

    public GridFactory<T> removeAllColumns() {
        get().removeAllColumns();
        return this;
    }

    public GridFactory<T> detailsVisible(T item, boolean visible) {
        get().setDetailsVisible(item, visible);
        return this;
    }

    public GridFactory<T> removeColumn(Grid.Column<T> removeColumn) {
        get().removeColumn(removeColumn);
        return this;
    }

    public GridFactory<T> multiSort(boolean multiSort) {
        get().setMultiSort(multiSort);
        return this;
    }

    public GridFactory<T> contextMenu() {
        get().addContextMenu();
        return this;
    }

    public GridFactory<T> valueProvider(String property, ValueProvider<T, ?> valueProvider) {
        get().addValueProvider(property, valueProvider);
        return this;
    }

    public GridFactory<T> heightByRows(boolean heightByRows) {
        get().setHeightByRows(heightByRows);
        return this;
    }

    public GridFactory<T> dragEndListener(ComponentEventListener<GridDragEndEvent<T>> dragEndListener) {
        get().addDragEndListener(dragEndListener);
        return this;
    }

    public GridFactory<T> dragFilter(SerializablePredicate<T> dragFilter) {
        get().setDragFilter(dragFilter);
        return this;
    }

    public GridFactory<T> dropMode(GridDropMode dropMode) {
        get().setDropMode(dropMode);
        return this;
    }

    public GridFactory<T> rowsDraggable(boolean rowsDraggable) {
        get().setRowsDraggable(rowsDraggable);
        return this;
    }

    public GridFactory<T> dropFilter(SerializablePredicate<T> dropFilter) {
        get().setDropFilter(dropFilter);
        return this;
    }

    public GridFactory<T> dropListener(ComponentEventListener<GridDropEvent<T>> dropListener) {
        get().addDropListener(dropListener);
        return this;
    }

    public GridFactory<T> scrollToStart() {
        get().scrollToStart();
        return this;
    }

    @SafeVarargs
    public final GridFactory<T> columnOrder(Grid.Column<T>... columns) {
        get().setColumnOrder(columns);
        return this;
    }

    public GridFactory<T> columnOrder(List<Grid.Column<T>> columns) {
        get().setColumnOrder(columns);
        return this;
    }

    public GridFactory<T> scrollToIndex(int scrollToIndex) {
        get().scrollToIndex(scrollToIndex);
        return this;
    }

    public GridFactory<T> scrollToEnd() {
        get().scrollToEnd();
        return this;
    }

    public GridFactory<T> lumoNoBorder() {
        get().addThemeVariants(GridVariant.LUMO_NO_BORDER);
        return this;
    }

    public GridFactory<T> lumoNoRowBorders() {
        get().addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS);
        return this;
    }

    public GridFactory<T> lumoColumnBorders() {
        get().addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        return this;
    }

    public GridFactory<T> lumoRowStripes() {
        get().addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        return this;
    }

    public GridFactory<T> lumoCompact() {
        get().addThemeVariants(GridVariant.LUMO_COMPACT);
        return this;
    }

    public GridFactory<T> lumoWrapCellContent() {
        get().addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        return this;
    }

    public GridFactory<T> materialColumnDividers() {
        get().addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
        return this;
    }
}
