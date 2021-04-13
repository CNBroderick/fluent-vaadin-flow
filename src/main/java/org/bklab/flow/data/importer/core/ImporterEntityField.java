package org.bklab.flow.data.importer.core;

import org.bklab.flow.util.function.EmptyFunctions;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public class ImporterEntityField<T> {

    public boolean nullable = false;
    private String name;
    private boolean indispensable = true;
    private BiConsumer<T, String> setter = EmptyFunctions.emptyBiConsumer();

    public ImporterEntityField(String name) {
        this.name = name;
    }

    public ImporterEntityField(String name, BiConsumer<T, String> setter) {
        this.name = name;
        this.setter = setter;
    }

    public ImporterEntityField(String name, boolean indispensable, BiConsumer<T, String> setter) {
        this.name = name;
        this.indispensable = indispensable;
        this.setter = setter;
    }

    public String getName() {
        return name;
    }

    public ImporterEntityField<T> setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isIndispensable() {
        return indispensable;
    }

    public ImporterEntityField<T> setIndispensable(boolean indispensable) {
        this.indispensable = indispensable;
        return this;
    }

    public BiConsumer<T, String> getSetter() {
        return setter;
    }

    public ImporterEntityField<T> setSetter(BiConsumer<T, String> setter) {
        this.setter = setter;
        return this;
    }

    public boolean isNullable() {
        return nullable;
    }

    public ImporterEntityField<T> setNullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public ImporterEntityField<T> set(T entity, String value) {
        try {
            setter.accept(entity, value);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("解析[" + entity.getClass().getName() + "]中字段[" + name + "]的值[" + value + "]失败。", e);
        }
        return this;
    }
}
