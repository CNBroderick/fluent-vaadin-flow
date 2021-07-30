/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-17 15:07:23
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.html.table.tooltip.NoBorderTooltipTable
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.html.table.tooltip;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.Style;
import org.bklab.flow.base.HasFlowFactory;
import org.bklab.flow.components.html.table.tooltip.builder.NoBorderTooltipTableRowBuilder;
import org.bklab.flow.factory.DivFactory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NoBorderTooltipTable extends Element implements HasFlowFactory<Div, DivFactory> {

    private final Element content;
    private final Element tableBody;
    private final String baseClassName = UUID.randomUUID().toString();
    private final Map<String, Style> styleMap = new LinkedHashMap<>();
    private Element style;

    public NoBorderTooltipTable(int columns) {
        super("div");

        styleMap.put('.' + baseClassName, createStyle("border:none;border-collapse:collapse;border-spacing:0;margin:0px auto;"));
        styleMap.put('.' + baseClassName + " td", createStyle("border-style:solid;border-width:0;font-family:Arial, sans-serif;font-size:14px;" +
                                                              "overflow:hidden;padding:10px 5px;word-break:normal;"));
        styleMap.put('.' + baseClassName + " th", createStyle("th{border-style:solid;border-width:0;font-family:Arial, sans-serif;font-size:14px;" +
                                                              "font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;"));
        setColumnAlign(0, "right");
        for (int i = 1; i < columns; i++) {
            setColumnAlign(i, "left");
        }
        style = new Html("<style>" + createStyleContent() + "</style>").getElement();
        content = new Element("table");
        content.getClassList().add(baseClassName);
//                new Html("<table class='" + baseClassName + "'><tbody></tbody></table>");
        tableBody = new Element("tbody");
        content.appendChild(tableBody);
        appendChild(style, content);
    }

    private Style createStyle() {
        return new Span().getStyle();
    }

    private Style createStyle(String style) {
        Style s = createStyle();
        for (String s1 : style.split(";")) {
            String[] split = s1.split(":");
            if (split.length == 2) s.set(split[0], split[1]);
        }
        return s;
    }

    public NoBorderTooltipTable setColumnStyle(int column, Consumer<Style> styleConsumer) {
        styleConsumer.accept(styleMap.computeIfAbsent(
                '.' + baseClassName + " ." + baseClassName + "-c" + column,
                id -> createStyle().set("text-align", "center").set("vertical-align", "center")
        ));
        return this;
    }

    public NoBorderTooltipTable setColumnAlign(int column, String align) {
        return setColumnStyle(column, style -> style.set("text-align", align));
    }

    public NoBorderTooltipTable refreshStyles() {
        Html html = new Html("<style>" + createStyleContent() + "</style>");
        this.style = html.getElement();
        setChild(0, style);
        return this;
    }

    public String createStyleContent() {
        StringBuilder builder = new StringBuilder();
        styleMap.forEach((name, style) -> builder.append(name).append('{')
                .append(style.getNames().map(n -> n + ':' + style.get(n)).collect(Collectors.joining(";")))
                .append('}').append(' '));
        return builder.toString();
    }

    public Element getTableStyle() {
        return style;
    }

    public Element getContent() {
        return content;
    }

    public NoBorderTooltipTable clear() {
        tableBody.removeAllChildren();
        return this;
    }

    public NoBorderTooltipTable addHead(String... tds) {
        return addRow(createRowBuilder("th").addAll(tds));
    }

    public NoBorderTooltipTable addHead(String name, Element value) {
        return addRow(createRowBuilder("th").add(name).add(value));
    }

    public NoBorderTooltipTable addHead(Component... tds) {
        return addRow(createRowBuilder("th").addAll(tds));
    }

    public NoBorderTooltipTable addHead(Element... tds) {
        return addRow(createRowBuilder("th").addAll(tds));
    }

    public NoBorderTooltipTable addRow(String... tds) {
        return addRow(createRowBuilder("tr").addAll(tds));
    }

    public NoBorderTooltipTable addStringRow(Collection<String> tds) {
        return addRow(createRowBuilder("tr").addAll(tds.toArray(new String[]{})));
    }

    public NoBorderTooltipTable addRow(String name, Element value) {
        return addRow(createRowBuilder("tr").add(name).add(value));
    }

    public NoBorderTooltipTable addRow(Component... tds) {
        return addRow(createRowBuilder("tr").addAll(tds));
    }

    public NoBorderTooltipTable addComponentRow(Collection<Component> tds) {
        return addRow(createRowBuilder("tr").addAll(tds.toArray(new Component[]{})));
    }

    public NoBorderTooltipTable addRow(Element... tds) {
        return addRow(createRowBuilder("tr").addAll(tds));
    }

    public NoBorderTooltipTable addElementRow(Collection<Element> tds) {
        return addRow(createRowBuilder("tr").addAll(tds));
    }

    public NoBorderTooltipTableRowBuilder createRowBuilder(String tagName) {
        return new NoBorderTooltipTableRowBuilder(tagName, baseClassName);
    }

    public NoBorderTooltipTable addRow(Consumer<NoBorderTooltipTableRowBuilder> consumer) {
        NoBorderTooltipTableRowBuilder builder = createRowBuilder("tr");
        consumer.accept(builder);
        return addRow(builder);
    }

    public NoBorderTooltipTable addRow(NoBorderTooltipTableRowBuilder rowBuilder) {
        tableBody.appendChild(rowBuilder.getElement());
        return this;
    }

    public NoBorderTooltipTable style(Consumer<Style> styleConsumer) {
        styleConsumer.accept(getStyle());
        return this;
    }

    public NoBorderTooltipTable style(String name, String value) {
        getStyle().set(name, value);
        return this;
    }

    @Override
    public DivFactory asFactory() {
        return new DivFactory(asComponent());
    }

    public Div asComponent() {
        return this.as(Div.class);
    }
}
