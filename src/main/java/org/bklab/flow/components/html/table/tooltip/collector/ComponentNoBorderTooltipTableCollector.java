/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-21 14:21:30
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.html.table.tooltip.collector.ComponentNoBorderTooltipTableCollector
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.html.table.tooltip.collector;

import com.vaadin.flow.component.Component;
import org.bklab.flow.components.html.table.tooltip.NoBorderTooltipTable;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ComponentNoBorderTooltipTableCollector implements Collector<Collection<Component>, NoBorderTooltipTable, NoBorderTooltipTable> {

    private final int columns;

    public ComponentNoBorderTooltipTableCollector(int columns) {
        this.columns = columns;
    }

    @Override
    public Supplier<NoBorderTooltipTable> supplier() {
        return () -> new NoBorderTooltipTable(columns);
    }

    @Override
    public BiConsumer<NoBorderTooltipTable, Collection<Component>> accumulator() {
        return NoBorderTooltipTable::addComponentRow;
    }

    @Override
    public BinaryOperator<NoBorderTooltipTable> combiner() {
        return (left, right) -> {
            right.getContent().getChildren().forEach(a -> left.getContent().appendChild(a));
            return left;
        };
    }

    @Override
    public Function<NoBorderTooltipTable, NoBorderTooltipTable> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
