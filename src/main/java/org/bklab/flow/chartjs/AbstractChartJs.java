/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-23 15:50:26
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.chartjs.AbstractChartJs
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.chartjs;

import be.ceau.chart.Chart;
import be.ceau.chart.color.Color;
import be.ceau.chart.javascript.JavaScriptFunction;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.util.predicate.MobileBrowserPredicate;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "UnusedReturnValue"})
public abstract class AbstractChartJs<T extends AbstractChartJs<T>> extends VerticalLayout implements HasSizeFactory<AbstractChartJs<T>, AbstractChartJs<T>> {

    protected Map<String, Number> data = new LinkedHashMap<>();
    protected Map<String, Map<String, Number>> bigData = new LinkedHashMap<>();

    protected String title = "";
    protected String numberSuffix = "";
    protected String numberPrefix = "";
    protected List<Color> colors;
    protected Boolean intValue = Boolean.FALSE;
    protected double colorAlpha = 0.2;
    protected double barPercentage = 0.5;
    protected int fractionDigits = 2;
    protected Color defaultTooltipLabelBackgroundColor = new Color(238, 253, 254, 0.75);
    private ChartJs chartJs;

    public AbstractChartJs() {
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        setMargin(false);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

    }

    public boolean isMobile() {
        try {
            return new MobileBrowserPredicate().test(VaadinSession.getCurrent().getBrowser());
        } catch (Exception ignore) {
            return false;
        }
    }

    public String[] getColorArray() {
        return colors.stream().map(Color::toString).collect(Collectors.toList()).toArray(new String[]{});
    }

    public T addData(String name, Number value) {
        data.put(name, value);
        return (T) this;
    }

    public T setData(Map<String, Number> data) {
        this.data = data;
        return (T) this;
    }

    public T addData(Map<String, ? extends Number> data) {
        data.forEach((k, v) -> this.data.put(k, v));
        return (T) this;
    }

    public T addBigData(String name, String key, Number value) {
        Map<String, Number> map = bigData.getOrDefault(name, new LinkedHashMap<>());
        map.put(key, value);
        this.bigData.put(name, map);
        return (T) this;
    }

    public T setBigData(String name, Map<String, Number> data) {
        this.bigData.put(name, data);
        return (T) this;
    }

    public T addBigData(Map<String, Map<String, ? extends Number>> bigData) {
        bigData.forEach(this::addBigData);
        return (T) this;
    }

    public T addBigData(String name, Map<String, ? extends Number> data) {
        Map<String, Number> map = bigData.getOrDefault(name, new LinkedHashMap<>());
        data.forEach(map::put);
        this.bigData.put(name, map);
        return (T) this;
    }

    public T addBigData(Consumer<Map<String, Map<String, Number>>> bigDataConsumer) {
        bigDataConsumer.accept(bigData);
        return (T) this;
    }

    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }


    public T setColors(List<Color> colors) {
        this.colors = colors;
        return (T) this;
    }

    public T setColors(Color... colors) {
        this.colors = Arrays.asList(colors);
        return (T) this;
    }

    public T setColors(java.awt.Color... colors) {
        this.colors = Arrays.stream(colors)
                .map(c -> new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()))
                .collect(Collectors.toList());
        return (T) this;
    }

    public T colors(Color... colors) {
        this.colors.addAll(0, Arrays.asList(colors));
        return (T) this;
    }

    public T colors(java.awt.Color... colors) {
        this.colors.addAll(0, Arrays.stream(colors)
                .map(c -> new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()))
                .collect(Collectors.toList()));
        return (T) this;
    }

    public T withSize(String width, String height) {
        setHeight(height);
        setWidth(width);
        return (T) this;
    }

    public T fullWidth() {
        setWidthFull();
        return (T) this;
    }

    public T fullHeight() {
        setHeightFull();
        return (T) this;
    }

    public T build() {
        checkColors();
        this.chartJs = new ChartJs(createChartJson());
        add(chartJs);
        return (T) this;
    }

    public T whenClicked(ComponentEventListener<ClickEvent> listener) {
        chartJs.addClickListener(listener);
        return (T) this;
    }

    private void checkColors() {
        if (colors == null) colors = new ArrayList<>(Arrays.asList(
                new Color(153, 102, 255, colorAlpha),
                new Color(54, 162, 235, colorAlpha),
                new Color(75, 192, 192, colorAlpha),
                new Color(255, 205, 86, colorAlpha),
                new Color(255, 159, 64, colorAlpha),
                new Color(255, 99, 132, colorAlpha),
                new Color(201, 203, 207, colorAlpha)
        ));
        while (colors.size() < data.size()) {
            colors.add(randomColor());
        }
    }

    private Color randomColor() {
        Random random = new Random();
        return new Color(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255),
                colorAlpha);
    }

    public T setNumberSuffix(String numberSuffix) {
        this.numberSuffix = numberSuffix;
        return (T) this;
    }

    public T setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
        return (T) this;
    }

    public JavaScriptFunction createFormatNumberJs() {
        return createJavascriptFunction("function(d,e){const c=d.index;" +
                "const b=(e.datasets[d.datasetIndex].label === undefined)?'':e.datasets[d.datasetIndex].label;" +
                "return b+': " + numberPrefix + "'+Number(d.yLabel).toLocaleString('zh-cn',{" +
                "maximumFractionDigits:" + fractionDigits + ",useGrouping:true})+' " + numberSuffix + "'}"
        );
    }

    public JavaScriptFunction createPieChartFormatNumberJs() {
        return createJavascriptFunction("function(d,e){const c=d.index;" +
                "const b=(e.datasets[d.datasetIndex].label === undefined)?'':e.datasets[d.datasetIndex].label;" +
                "return e.labels[c]+b+': " + numberPrefix + "'+Number(e.datasets[0].data[c]).toLocaleString('zh-cn',{" +
                "maximumFractionDigits:" + fractionDigits + ",useGrouping:true})+' " + numberSuffix + "'}"
        );
    }

    public JavaScriptFunction createLabelTextColor() {
        return createJavascriptFunction("function(t,c){return '#000000'}");
    }


    public AbstractChartJs<T> withIntValue() {
        this.intValue = Boolean.TRUE;
        return this;
    }

    public AbstractChartJs<T> setColorAlpha(double colorAlpha) {
        this.colorAlpha = colorAlpha;
        return this;
    }

    public AbstractChartJs<T> setBarPercentage(double barPercentage) {
        this.barPercentage = barPercentage;
        return this;
    }

    protected JavaScriptFunction createJavascriptFunction(String function) {
        return new JavaScriptFunction("\"" + function + "\"");
    }

    protected JavaScriptFunction createFormatNumberFunction() {
        return createJavascriptFunction("function(b) {let number = Number(b);let middle = '';if(number >= 100000000) { " +
                "number /= 100000000; middle = '亿'; } else if( number >= 10000) { number /= 10000; middle = '万'; " +
                "} return '" + numberPrefix + "' + number.toLocaleString('zh-cn', { maximumFractionDigits: 2, useGrouping: true " +
                "}) + middle + '" + numberSuffix + "'; }");
//        return createJavascriptFunction("function(b){return new Number(b).toLocaleString('zh-cn', {" +
//                "maximumFractionDigits: 2,useGrouping: true})+'" + numberSuffix + "';}");
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public AbstractChartJs<T> setFractionDigits(int fractionDigits) {
        this.fractionDigits = fractionDigits;
        return this;
    }

    @Override
    public AbstractChartJs<T> get() {
        return this;
    }

    protected abstract Chart createChart();

    protected String createChartJson() {
        return createChart().toJson();
    }
}
