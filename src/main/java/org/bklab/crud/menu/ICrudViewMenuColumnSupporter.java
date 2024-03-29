/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 15:05:51
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.crud.menu.ICrudViewMenuColumnSupporter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.util.lumo.LumoStyles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ICrudViewMenuColumnSupporter<T, G extends Grid<T>, C extends FluentCrudView<T, G>> {


    default C addMenuColumn(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.ELLIPSIS_DOTS_H.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get(), menuEntityBiConsumer);
    }

    default C addEditIconMenuColumn(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.EDIT.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get(), menuEntityBiConsumer);
    }

    default C addMenuButtonColumn(Function<T, String> buttonNameFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(entity -> FluentButton.linkButton(buttonNameFunction.apply(entity)), menuEntityBiConsumer);
    }

    default C addMenuButtonColumn(Function<T, String> buttonNameFunction, Function<T, String> tooltipFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(entity -> FluentButton.linkButton(buttonNameFunction.apply(entity)).tooltip(tooltipFunction.apply(entity)), menuEntityBiConsumer);
    }

    default Grid.Column<T> addMenuSpanColumn(Function<T, String> buttonNameFunction, Function<T, String> tooltipFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return getCrudViewGrid().addComponentColumn(createMenuColumnComponentProvider(t -> new SpanFactory(buttonNameFunction
                .apply(t)).style("color", LumoStyles.Color.Primary._100).style("cursor", "pointer")
                .peekThisWhenItNotNull(tooltipFunction, span -> span.tooltip(tooltipFunction.apply(t))).get(), menuEntityBiConsumer));
    }

    default Grid.Column<T> addMenuSpanColumn(Function<T, String> buttonNameFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer, String replaceColumnKey) {
        return addMenuSpanColumn(buttonNameFunction, null, menuEntityBiConsumer, replaceColumnKey);
    }

    default Grid.Column<T> addMenuSpanColumn(Function<T, String> buttonNameFunction, Function<T, String> tooltipFunction,
                                             IFluentMenuBuilder<T, G> menuEntityBiConsumer, String replaceColumnKey) {
        Grid.Column<T> column = addMenuSpanColumn(buttonNameFunction, tooltipFunction, menuEntityBiConsumer);
        Grid.Column<T> target = getCrudViewGrid().getColumnByKey(replaceColumnKey);
        if (target != null) {
            gridColumnAt(column, target, 1);
            target.setVisible(false);
        }
        return column;
    }

    default C addMenuColumn(Supplier<Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(entity -> menuContentSupplier.get(), menuEntityBiConsumer);
    }

    default C addMenuColumn(Function<T, Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(createMenuColumnComponentProvider(menuContentSupplier, menuEntityBiConsumer));
    }

    default Grid.Column<T> menuCustomColumn(Function<T, Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return getCrudView().getGrid()
                .addComponentColumn(createMenuColumnComponentProvider(menuContentSupplier, menuEntityBiConsumer));
    }

    default Grid.Column<T> menuButtonColumn(Function<T, String> buttonNameFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return getCrudView().getGrid().addComponentColumn(createMenuColumnComponentProvider(entity ->
                FluentButton.linkButton(buttonNameFunction.apply(entity)), menuEntityBiConsumer));
    }

    default Grid.Column<T> menuButtonColumn(Function<T, String> buttonNameFunction, Function<T, String> tooltipFunction, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return getCrudView().getGrid().addComponentColumn(createMenuColumnComponentProvider(entity ->
                FluentButton.linkButton(buttonNameFunction.apply(entity)).tooltip(tooltipFunction.apply(entity)), menuEntityBiConsumer));
    }

    default ValueProvider<T, Component> createMenuColumnComponentProvider(
            Function<T, Component> menuContentSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return new FluentCrudViewComponentMenuValueProvider<>(getCrudView(), menuContentSupplier, menuEntityBiConsumer);
    }

    default <V extends Component> C addMenuColumn(ValueProvider<T, V> componentProvider) {
        Grid.Column<T> column = getCrudView().getGrid().addComponentColumn(componentProvider).setHeader("操作")
                .setKey("menuColumn").setSortable(false).setWidth("5em").setTextAlign(ColumnTextAlign.CENTER);
        return getCrudView();
    }

    default C menuColumnAtFirst() {
        Grid.Column<T> menuColumn = getCrudView().getGrid().getColumnByKey("menuColumn");
        if (menuColumn == null) return getCrudView();
        return gridColumnAt(menuColumn, 0);
    }

    default C menuColumnAtLast() {
        Grid.Column<T> menuColumn = getCrudView().getGrid().getColumnByKey("menuColumn");
        if (menuColumn == null) return getCrudView();
        return gridColumnAt(menuColumn, getCrudView().getGrid().getColumns().size() - 1);
    }

    default C menuColumnAfter(String afterColumn) {
        return menuColumnAfter(getCrudViewGrid().getColumnByKey("menuColumn"), afterColumn);
    }

    default C menuColumnBefore(String beforeColumn) {
        return menuColumnBefore(getCrudViewGrid().getColumnByKey("menuColumn"), beforeColumn);
    }

    default C menuColumnAfter(Grid.Column<T> menuColumn, String afterColumn) {
        Grid.Column<T> column = getCrudViewGrid().getColumnByKey(afterColumn);
        if (column == null) return getCrudView();
        return gridColumnAt(menuColumn, column, 1);
    }

    default C menuColumnBefore(Grid.Column<T> menuColumn, String beforeColumn) {
        Grid.Column<T> column = getCrudViewGrid().getColumnByKey(beforeColumn);
        if (column == null) return getCrudView();
        return gridColumnAt(menuColumn, column, -1);
    }

    default C gridColumnAt(String menuColumnKey, String referenceColumnKey, int relativeDistanceOfColumn) {
        Grid.Column<T> menuColumn = getCrudViewGrid().getColumnByKey(menuColumnKey);
        if (menuColumn == null) return getCrudView();
        Grid.Column<T> referenceColumn = getCrudViewGrid().getColumnByKey(referenceColumnKey);
        if (referenceColumn == null) return getCrudView();
        return gridColumnAt(menuColumn, referenceColumn, relativeDistanceOfColumn);
    }

    default C gridColumnAt(Grid.Column<T> menuColumn, Grid.Column<T> referenceColumn, int relativeDistanceOfColumn) {
        List<Grid.Column<T>> columns = new ArrayList<>(getCrudView().getGrid().getColumns());
        columns.remove(menuColumn);
        columns.add(Math.max(0, columns.indexOf(referenceColumn) + relativeDistanceOfColumn), menuColumn);
        getCrudViewGrid().setColumnOrder(columns);
        return getCrudView();
    }

    default C gridColumnAtFirst(Grid.Column<T> menuColumn) {
        return gridColumnAt(menuColumn, 0);
    }

    default C gridColumnAtLast(Grid.Column<T> menuColumn) {
        return gridColumnAt(menuColumn, getCrudViewGrid().getColumns().size() - 1);
    }

    default C gridColumnAt(Grid.Column<T> menuColumn, int absolutelyPositionOfColumn) {
        List<Grid.Column<T>> columns = new ArrayList<>(getCrudView().getGrid().getColumns());
        columns.remove(menuColumn);
        columns.add(Math.max(0, absolutelyPositionOfColumn), menuColumn);
        getCrudViewGrid().setColumnOrder(columns);
        return getCrudView();
    }

    private Grid<T> getCrudViewGrid() {
        return getCrudView().getGrid();
    }

    List<FluentCrudMenuButton<T, G>> getMenuButtons();

    C getCrudView();
}
