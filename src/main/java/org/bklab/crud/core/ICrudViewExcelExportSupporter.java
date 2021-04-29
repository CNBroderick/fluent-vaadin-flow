/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-20 11:56:27
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.crud.core.ICrudViewExcelExportSupporter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.core;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import org.bklab.crud.FluentCrudView;
import org.bklab.export.data.ColumnDataBuilder;
import org.bklab.export.grid.GridColumnDataBuilderFactory;
import org.bklab.export.xlsx.ExcelDataExporter;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.menu.FluentMenuItem;
import org.bklab.flow.dialog.DownloadDialog;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.util.function.EmptyFunctions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ICrudViewExcelExportSupporter<T, G extends Grid<T>, C extends FluentCrudView<T, G>>
        extends IFluentCrudViewCommonField<T, G, C> {

    default C enableExport() {
        PageTitle annotation = getClass().getAnnotation(PageTitle.class);
        return annotation != null ? enableExport(annotation.value()) : enableExport("导出");
    }

    default C enableExport(String fileName) {
        return enableExport(fileName, fileName);
    }

    default C enableExport(String fileName, String excelTitle) {
        return enableExport(fileName, excelTitle, EmptyFunctions.emptyConsumer(), EmptyFunctions.emptyConsumer());
    }

    default C enableExport(String fileName, String excelTitle, Map<String, String> keyHeaderMap) {
        return enableExport(fileName, excelTitle, factory -> factory.headers(keyHeaderMap), EmptyFunctions.emptyConsumer());
    }

    default C enableExport(String fileName, String excelTitle,
                           Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                           Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        header().right(createExportButton(fileName, excelTitle, builderFactoryConsumer, columnDataBuilderConsumer));
        return getCrudView();
    }

    default C enableExport(Supplier<String> fileName) {
        return enableExport(fileName, fileName);
    }

    default C enableExport(Supplier<String> fileName, Supplier<String> excelTitle) {
        return enableExport(fileName, excelTitle, EmptyFunctions.emptyConsumer(), EmptyFunctions.emptyConsumer());
    }

    default C enableExport(Supplier<String> fileName, Supplier<String> excelTitle, Map<String, String> keyHeaderMap) {
        return enableExport(fileName, excelTitle, factory -> factory.headers(keyHeaderMap), EmptyFunctions.emptyConsumer());
    }

    default C createExportMenuItem(ContextMenu contextMenu, Supplier<String> fileName) {
        return createExportMenuItem(contextMenu, fileName, fileName);
    }

    default C createExportMenuItem(ContextMenu contextMenu, Supplier<String> fileName, Supplier<String> excelTitle) {
        return createExportMenuItem(contextMenu, fileName, excelTitle, EmptyFunctions.emptyConsumer(), EmptyFunctions.emptyConsumer());
    }

    default C createExportMenuItem(ContextMenu contextMenu, Supplier<String> fileName, Supplier<String> excelTitle,
                                   Map<String, String> keyHeaderMap) {
        return createExportMenuItem(contextMenu, fileName, excelTitle, factory -> factory.headers(keyHeaderMap), EmptyFunctions.emptyConsumer());
    }

    default C enableExport(Supplier<String> fileName, Supplier<String> excelTitle,
                           Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                           Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        header().right(createExportButton(fileName, excelTitle, builderFactoryConsumer, columnDataBuilderConsumer));
        return getCrudView();
    }

    default Button createExportButton(String fileName, String excelTitle,
                                      Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                      Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        return createExportButton(() -> fileName, () -> excelTitle, builderFactoryConsumer, columnDataBuilderConsumer);
    }

    default Button createExportButton(String fileName, String excelTitle, ColumnDataBuilder<T> columnDataBuilder) {
        return createExportButton(() -> fileName, () -> excelTitle, columnDataBuilder);
    }

    default Button createExportButton(Supplier<String> fileName, Supplier<String> excelTitle,
                                      Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                      Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        return createExportButton(fileName, excelTitle, createColumnDataBuilder(builderFactoryConsumer, columnDataBuilderConsumer));
    }

    default C createExportMenuItem(ContextMenu contextMenu, Supplier<String> fileName, Supplier<String> excelTitle,
                                   Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                   Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        return createExportMenuItem(contextMenu, fileName, excelTitle, createColumnDataBuilder(builderFactoryConsumer, columnDataBuilderConsumer));
    }

    default ColumnDataBuilder<T> createColumnDataBuilder(Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                                         Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        GridColumnDataBuilderFactory<T> factory = new GridColumnDataBuilderFactory<>(getGrid());
        builderFactoryConsumer.accept(factory);
        ColumnDataBuilder<T> builder = factory.createBuilder();
        columnDataBuilderConsumer.accept(builder);
        return builder;
    }

    default FluentMenuItem createExportMenuItem() {
        return FluentMenuItem.create(VaadinIcon.EXTERNAL_LINK, "导出到Excel");
    }

    default C createExportMenuItem(ContextMenu contextMenu, Supplier<String> fileName, Supplier<String> excelTitle, ColumnDataBuilder<T> columnDataBuilder) {
        FluentMenuItem.create(VaadinIcon.EXTERNAL_LINK, "导出到Excel").add(contextMenu,
                event -> processOpenExportDialog(fileName, excelTitle, columnDataBuilder));
        return getCrudView();
    }

    default Button createExportButton(Supplier<String> fileName, Supplier<String> excelTitle, ColumnDataBuilder<T> columnDataBuilder) {
        return FluentButton.exportButton().clickListener(e -> processOpenExportDialog(fileName, excelTitle, columnDataBuilder)).iconOnly();
    }


    default void processOpenExportDialog() {
        processOpenExportDialog(() -> "导出数据", () -> "导出数据", new GridColumnDataBuilderFactory<>(getGrid()).createBuilder());
    }

    default void processOpenExportDialog(Supplier<String> fileName, Supplier<String> excelTitle,
                                         Consumer<GridColumnDataBuilderFactory<T>> builderFactoryConsumer,
                                         Consumer<ColumnDataBuilder<T>> columnDataBuilderConsumer) {
        GridColumnDataBuilderFactory<T> factory = new GridColumnDataBuilderFactory<>(getGrid());
        builderFactoryConsumer.accept(factory);
        ColumnDataBuilder<T> builder = factory.createBuilder();
        columnDataBuilderConsumer.accept(builder);
        processOpenExportDialog(fileName, excelTitle, builder);
    }

    default void processOpenExportDialog(Supplier<String> fileName, Supplier<String> excelTitle, ColumnDataBuilder<T> columnDataBuilder) {
        String finalFileName = fileName.get();
        Collection<T> entities = getEntities();
        if (entities.isEmpty()) {
            new ErrorDialog("当前无任何数据，无需导出。").build().open();
            return;
        }
        new DownloadDialog(finalFileName + "导出完毕，请下载。", new ExcelDataExporter<>(columnDataBuilder)
                .createStreamFactory(finalFileName + "-" + DateTimeFormatter.ofPattern("uuuuMMdd_HHmmss")
                        .format(LocalDateTime.now()) + ".xlsx", excelTitle.get(), entities)
        ).build().open();
    }
}
