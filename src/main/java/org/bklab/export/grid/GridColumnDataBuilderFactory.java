package org.bklab.export.grid;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.bklab.export.data.ColumnDataBuilder;

import java.util.*;

public class GridColumnDataBuilderFactory<T> {

    private final Grid<T> grid;
    private final Map<Grid.Column<T>, String> headerMap = new LinkedHashMap<>();
    private ColumnDataBuilder<T> builder;

    public GridColumnDataBuilderFactory(Grid<T> grid) {
        this.grid = grid;
        Map<String, String> headerNameMap = grid instanceof HasColumnHeaderMapping
                ? ((HasColumnHeaderMapping) grid).getHeaderMapping() : Collections.emptyMap();

        List<Grid.Column<T>> columns = grid.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            Grid.Column<T> c = columns.get(i);
            headerMap.put(c, Optional.ofNullable(headerNameMap.getOrDefault(
                    c.getKey(), c.getKey())).orElse("列" + (i + 1)));
        }
    }

    private HorizontalAlignment convertColumnTextAlign(ColumnTextAlign align) {
        if (align == null) return HorizontalAlignment.CENTER;
        return switch (align) {
            case START -> HorizontalAlignment.LEFT;
            case CENTER -> HorizontalAlignment.CENTER;
            case END -> HorizontalAlignment.RIGHT;
        };
    }

    public GridColumnDataBuilderFactory<T> headersFromColumn(Map<Grid.Column<T>, String> headerMap) {
        this.headerMap.putAll(headerMap);
        return this;
    }

    public GridColumnDataBuilderFactory<T> headers(Map<String, String> headerMap) {
        headerMap.forEach((k, v) -> Optional.ofNullable(
                grid.getColumnByKey(k)
        ).ifPresent(c -> this.headerMap.put(c, v)));
        return this;
    }

    public GridColumnDataBuilderFactory<T> removeColumns(String... keys) {
        for (String key : keys) {
            headerMap.remove(grid.getColumnByKey(key));
        }
        return this;
    }

    public GridColumnDataBuilderFactory<T> removeColumns(int... columns) {
        ArrayList<Grid.Column<T>> allColumns = new ArrayList<>(headerMap.keySet());
        Arrays.stream(columns).filter(column -> column < allColumns.size())
                .mapToObj(allColumns::get).filter(Objects::nonNull).forEach(headerMap::remove);
        return this;
    }

    @SafeVarargs
    public final GridColumnDataBuilderFactory<T> removeColumns(Grid.Column<T>... columns) {
        for (Grid.Column<T> column : columns) {
            headerMap.remove(column);
        }
        return this;
    }

    public ColumnDataBuilder<T> getBuilder() {
        if (builder == null) this.builder = createBuilder();
        return builder;
    }

    public ColumnDataBuilder<T> createBuilder() {
        ColumnDataBuilder<T> builder = new ColumnDataBuilder<>();
        headerMap.forEach((key, value) -> addColumn(builder, value, key));
        builder.header(headerMap.values());
        return builder;
    }

    private void addColumn(ColumnDataBuilder<T> builder, String name, Grid.Column<T> column) {
        builder.add(name, entity -> {
            Renderer<T> renderer = column.getRenderer();
            if (renderer instanceof ComponentRenderer) {
                return getString(((ComponentRenderer<?, T>) renderer).createComponent(entity));
            }

            List<String> strings = new ArrayList<>();
            renderer.getValueProviders().forEach((k, v) -> {
                String apply = Optional.ofNullable(getString(v.apply(entity))).map(String::strip).orElse("");
                if (apply.isBlank() || "null".equalsIgnoreCase(apply)) return;
                strings.add(apply);
            });

            return String.join("\n", strings);
        });
        builder.align(name, convertColumnTextAlign(column.getTextAlign()));
    }

    private String getString(Object o) {
        if (o == null) return "";
        if (o instanceof String) return (String) o;
        if (o instanceof HasText) return ((HasText) o).getText();
        return String.valueOf(o);
    }

}
