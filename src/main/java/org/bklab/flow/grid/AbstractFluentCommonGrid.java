package org.bklab.flow.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridArrayUpdater;
import com.vaadin.flow.function.SerializableBiFunction;

public abstract class AbstractFluentCommonGrid<T> extends Grid<T> implements FluentCommonGrid<T> {

    public AbstractFluentCommonGrid() {
        super();
    }

    public AbstractFluentCommonGrid(int pageSize) {
        super(pageSize);
    }

    public AbstractFluentCommonGrid(Class<T> beanType, boolean autoCreateColumns) {
        super(beanType, autoCreateColumns);
    }

    public AbstractFluentCommonGrid(Class<T> beanType) {
        super(beanType);
    }

    protected <U extends GridArrayUpdater, B extends DataCommunicatorBuilder<T, U>> AbstractFluentCommonGrid(Class<T> beanType, SerializableBiFunction<GridArrayUpdater.UpdateQueueData, Integer, UpdateQueue> updateQueueBuilder, B dataCommunicatorBuilder) {
        super(beanType, updateQueueBuilder, dataCommunicatorBuilder);
    }

    protected <U extends GridArrayUpdater, B extends DataCommunicatorBuilder<T, U>> AbstractFluentCommonGrid(int pageSize, SerializableBiFunction<GridArrayUpdater.UpdateQueueData, Integer, UpdateQueue> updateQueueBuilder, B dataCommunicatorBuilder) {
        super(pageSize, updateQueueBuilder, dataCommunicatorBuilder);
    }

    @Override
    public Grid<T> get() {
        return this;
    }
}
