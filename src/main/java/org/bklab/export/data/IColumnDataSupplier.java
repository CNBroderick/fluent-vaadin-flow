package org.bklab.export.data;

import com.vaadin.flow.function.SerializableFunction;

public interface IColumnDataSupplier<T> extends SerializableFunction<T, String> {

    String get(T entity);

    @Override
    default String apply(T entity) {
        return get(entity);
    }
}
