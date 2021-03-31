package org.bklab.crud.core;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import org.bklab.crud.FluentCrudView;
import org.bklab.export.data.ColumnDataBuilder;
import org.bklab.export.grid.GridColumnDataBuilderFactory;
import org.bklab.export.xlsx.ExcelDataExporter;
import org.bklab.flow.components.button.FluentButton;
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
        GridColumnDataBuilderFactory<T> factory = new GridColumnDataBuilderFactory<>(getGrid());
        builderFactoryConsumer.accept(factory);
        ColumnDataBuilder<T> builder = factory.createBuilder();
        columnDataBuilderConsumer.accept(builder);
        return createExportButton(fileName, excelTitle, builder);
    }

    default Button createExportButton(Supplier<String> fileName, Supplier<String> excelTitle, ColumnDataBuilder<T> columnDataBuilder) {
        return FluentButton.exportButton().clickListener(e -> {
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
        }).iconOnly();
    }
}
