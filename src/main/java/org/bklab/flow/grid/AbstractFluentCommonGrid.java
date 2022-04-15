package org.bklab.flow.grid;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridArrayUpdater;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.function.SerializableBiFunction;
import com.vaadin.flow.function.ValueProvider;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
    public <V extends Component> Column<T> addComponentColumn(ValueProvider<T, V> componentProvider) {
        AtomicReference<Column<T>> reference = new AtomicReference<>();
        Column<T> column = super.addComponentColumn(entity -> {
            V component = componentProvider.apply(entity);
            if (component == null) {
                String str;
                try {
                    str = new JsonMapper().writeValueAsString(entity);
                } catch (Exception ignore) {
                    str = Objects.toString(entity);
                }
                System.err.println(reference.get() == null ? "unknown" : reference.get().getKey() + "'s component render is null, use empty span instead. entity: \n" + str);
                return new Span();
            }
            return component;
        });
        reference.set(column);
        return column;
    }

    @Override
    public Grid<T> get() {
        return this;
    }
}
