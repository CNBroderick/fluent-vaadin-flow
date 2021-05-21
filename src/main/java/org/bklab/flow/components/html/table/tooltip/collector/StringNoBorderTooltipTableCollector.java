/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-17 14:57:34
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.html.table.tooltip.collector.StringNoBorderTooltipTableCollector
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.html.table.tooltip.collector;

import org.bklab.flow.components.html.table.tooltip.NoBorderTooltipTable;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringNoBorderTooltipTableCollector implements Collector<Collection<String>, NoBorderTooltipTable, NoBorderTooltipTable> {

    private final int columns;

    public StringNoBorderTooltipTableCollector(int columns) {
        this.columns = columns;
    }

    @Override
    public Supplier<NoBorderTooltipTable> supplier() {
        return () -> new NoBorderTooltipTable(columns);
    }

    @Override
    public BiConsumer<NoBorderTooltipTable, Collection<String>> accumulator() {
        return NoBorderTooltipTable::addStringRow;
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
