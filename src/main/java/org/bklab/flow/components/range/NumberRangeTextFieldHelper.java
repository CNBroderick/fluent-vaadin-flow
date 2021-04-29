/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-25 14:43:25
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.range.NumberRangeTextFieldHelper
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.range;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.factory.NumberFieldFactory;

public class NumberRangeTextFieldHelper extends HorizontalLayout {

    private final NumberField minField;
    private final NumberField maxField;

    {
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    }

    public NumberRangeTextFieldHelper() {
        this(null);
    }

    public NumberRangeTextFieldHelper(String caption) {
        this(caption, createNumberField("最小值"), createNumberField("最大值"));
    }

    public NumberRangeTextFieldHelper(String caption, NumberField minField, NumberField maxField) {
        this.minField = minField;
        this.maxField = maxField;
        add(minField, maxField);
        setMargin(false);
    }

    private static NumberField createNumberField(String placeHolder) {
        return new NumberFieldFactory().lumoSmall().width("10.8em").placeholder(placeHolder).get();
    }

    public Double getMin() {
        if (maxField.getValue() != null && minField.getValue() != null)
            return Math.min(minField.getValue(), maxField.getValue());
        return minField.getValue();
    }

    public Double getMax() {
        if (maxField.getValue() != null && minField.getValue() != null)
            return Math.max(minField.getValue(), maxField.getValue());
        return maxField.getValue();
    }

    public NumberRangeTextFieldHelper withDescription(String description) {
        Tooltips.getCurrent().setTooltip(this, description);
        return this;
    }

    public NumberField getMinField() {
        return minField;
    }

    public NumberField getMaxField() {
        return maxField;
    }

    public NumberRangeTextFieldHelper placeholder(String min, String max) {
        minField.setPlaceholder(min == null ? "" : min);
        maxField.setPlaceholder(max == null ? "" : max);
        return this;
    }

    public NumberRangeTextFieldHelper limit(Double min, Double max) {
        if (min != null) {
            minField.setMin(min);
            maxField.setMin(min);
        }
        if (max != null) {
            minField.setMax(max);
            maxField.setMax(max);
        }
        return this;
    }
}
