package org.bklab.flow.parameter;

import com.vaadin.flow.component.grid.Grid;
import org.bklab.crud.grid.FluentComponentGrid;
import org.bklab.flow.factory.SpanFactory;

public class ParameterGrid extends Grid<ParameterEntry> {

    public ParameterGrid() {

        addComponentColumn(entry -> new SpanFactory(entry.getCaption()).tooltip(entry.getName()).get()).setHeader("名称");


    }
}
