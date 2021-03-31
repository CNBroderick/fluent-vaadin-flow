package org.bklab.flow.grid;

import com.vaadin.flow.component.grid.Grid;

public abstract class AbstractFluentCommonGrid<T> extends Grid<T> implements FluentCommonGrid<T> {

    @Override
    public Grid<T> get() {
        return this;
    }
}
