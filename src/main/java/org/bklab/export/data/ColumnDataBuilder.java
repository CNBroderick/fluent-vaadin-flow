package org.bklab.export.data;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.*;
import java.util.function.BiConsumer;

public class ColumnDataBuilder<T> {
    private final Map<String, IColumnDataSupplier<T>> map = new LinkedHashMap<>();
    private final List<String> disableExport = new ArrayList<>();
    private final List<Collection<String>> headers = new ArrayList<>();
    private final Map<String, HorizontalAlignment> alignMap = new LinkedHashMap<>();
    private boolean hasDefaultHeader = true;

    public ColumnDataBuilder() {
    }

    public ColumnDataBuilder(Map<String, IColumnDataSupplier<T>> map) {
        this.map.putAll(map);
    }

    public ColumnDataBuilder<T> hasDefaultHeader(boolean defaultHeader) {
        this.hasDefaultHeader = defaultHeader;
        return this;
    }

    public ColumnDataBuilder<T> header(String... names) {
        return this.header(new ArrayList<>(Arrays.asList(names)));
    }

    public ColumnDataBuilder<T> header(Collection<String> names) {
        this.headers.add(names);
        return this;
    }

    public ColumnDataBuilder<T> align(String name, HorizontalAlignment textAlign) {
        this.alignMap.put(name, textAlign);
        return this;
    }

    public ColumnDataBuilder<T> align(Map<String, HorizontalAlignment> textAlignMap) {
        this.alignMap.putAll(textAlignMap);
        return this;
    }

    public ColumnDataBuilder<T> add(String name, IColumnDataSupplier<T> supplier) {
        this.map.put(name, supplier);
        return this;
    }

    public ColumnDataBuilder<T> disableExport(String... names) {
        Collections.addAll(disableExport, names);
        return this;
    }

    public void forEach(BiConsumer<String, IColumnDataSupplier<T>> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<String, IColumnDataSupplier<T>> entry : map.entrySet()) {
            if (disableExport.contains(entry.getKey())) continue;
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, IColumnDataSupplier<T>> getMap() {
        return map;
    }

    public List<String> getDisableExport() {
        return disableExport;
    }

    public List<Collection<String>> getHeaders() {
        return headers;
    }

    public boolean hasDefaultHeader() {
        return hasDefaultHeader;
    }

    public int columnSize() {
        return map.size();
    }

    public HorizontalAlignment getAlign(String name) {
        return Optional.ofNullable(alignMap.get(name)).orElse(HorizontalAlignment.CENTER);
    }
}
