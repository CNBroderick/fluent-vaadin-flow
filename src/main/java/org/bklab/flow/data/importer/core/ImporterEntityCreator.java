package org.bklab.flow.data.importer.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ImporterEntityCreator<T> {

    private final Map<String, ImporterEntityField<T>> columnMap = new LinkedHashMap<>();
    private final Supplier<T> entityCreator;
    private boolean hasHeader = true;

    public ImporterEntityCreator(Supplier<T> entityCreator) {
        this.entityCreator = entityCreator;
    }

    public ImporterEntityCreator<T> addColumn(String name, BiConsumer<T, String> setter) {
        columnMap.put(name, new ImporterEntityField<>(name, setter));
        return this;
    }

    public ImporterEntityCreator<T> addColumn(ImporterEntityField<T> importerEntityField) {
        columnMap.put(importerEntityField.getName(), importerEntityField);
        return this;
    }

    public ImporterEntityField<T> get(String name) {
        return columnMap.getOrDefault(name, new ImporterEntityField<T>(name));
    }

    public T createEntity() {
        return entityCreator.get();
    }

    public ImporterEntityCreator<T> setEntityField(String name, T entity, String value) {
        get(name).set(entity, value);
        return this;
    }

    public boolean noHeader() {
        return !this.hasHeader;
    }

    public ImporterEntityCreator<T> hasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
        return this;
    }

    public Map<String, ImporterEntityField<T>> getColumnMap() {
        return columnMap;
    }

    public Supplier<T> getEntityCreator() {
        return entityCreator;
    }

    public boolean hasHeader() {
        return hasHeader;
    }
}
