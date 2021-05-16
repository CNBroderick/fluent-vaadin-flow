/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-16 14:44:20
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.html.table.NoBorderTooltipTableRowBuilder
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.html.table;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NoBorderTooltipTableRowBuilder implements Collector<Element, NoBorderTooltipTableRowBuilder, NoBorderTooltipTableRowBuilder> {
    private final Element element;
    private final String baseClassName;

    public NoBorderTooltipTableRowBuilder(String tagName, String baseClassName) {
        element = new Element(tagName);
        this.baseClassName = baseClassName;
    }

    public NoBorderTooltipTableRowBuilder add(String td) {
        return add(Element.createText(td));
    }

    public NoBorderTooltipTableRowBuilder add(Component td) {
        return add(td.getElement());
    }

    public NoBorderTooltipTableRowBuilder add(Element element) {
        Element td = new Element("td");
        td.getClassList().add(baseClassName + "-c" + this.element.getChildCount());
        td.appendChild(element);
        this.element.appendChild(td);
        return this;
    }

    public NoBorderTooltipTableRowBuilder addAll(String... tds) {
        for (String td : tds) {
            this.add(td);
        }
        return this;
    }

    public NoBorderTooltipTableRowBuilder addAll(Component... tds) {
        for (Component td : tds) {
            this.add(td);
        }
        return this;
    }

    public NoBorderTooltipTableRowBuilder addAll(Element... tds) {
        for (Element td : tds) {
            this.add(td);
        }
        return this;
    }

    public NoBorderTooltipTableRowBuilder addAll(Collection<Element> td) {
        td.forEach(this::add);
        return this;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public Supplier<NoBorderTooltipTableRowBuilder> supplier() {
        return () -> this;
    }

    @Override
    public BiConsumer<NoBorderTooltipTableRowBuilder, Element> accumulator() {
        return NoBorderTooltipTableRowBuilder::add;
    }

    @Override
    public BinaryOperator<NoBorderTooltipTableRowBuilder> combiner() {
        return (left, right) -> left.addAll(right.element.getChildren().collect(Collectors.toSet()));
    }

    @Override
    public Function<NoBorderTooltipTableRowBuilder, NoBorderTooltipTableRowBuilder> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }

}
