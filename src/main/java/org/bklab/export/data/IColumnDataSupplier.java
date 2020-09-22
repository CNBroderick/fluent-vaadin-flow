package org.bklab.export.data;

public interface IColumnDataSupplier<T> {

    String get(T entity);

}
