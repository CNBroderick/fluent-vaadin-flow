/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.factory.FormLayoutFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.renderer.TextRenderer;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.GeneratedVaadinFormLayoutFactory;
import org.bklab.flow.base.HasComponentsFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.util.number.DigitalFormatter;
import org.slf4j.LoggerFactory;

import java.util.*;

public class FormLayoutFactory extends FlowFactory<FormLayout, FormLayoutFactory> implements
        GeneratedVaadinFormLayoutFactory<FormLayout, FormLayoutFactory>,
        HasSizeFactory<FormLayout, FormLayoutFactory>,
        HasComponentsFactory<FormLayout, FormLayoutFactory>,
        ClickNotifierFactory<FormLayout, FormLayoutFactory> {

    public FormLayoutFactory() {
        this(new FormLayout());
    }

    public FormLayoutFactory(Component... components) {
        this(new FormLayout(components));
    }

    public FormLayoutFactory(FormLayout component) {
        super(component);
    }

    public FormLayoutFactory add(Component component, int colspan) {
        get().add(component, colspan);
        return this;
    }

    public FormLayoutFactory responsiveSteps(List<FormLayout.ResponsiveStep> responsiveSteps) {
        get().setResponsiveSteps(responsiveSteps);
        return this;
    }

    public FormLayoutFactory responsiveSteps(FormLayout.ResponsiveStep... responsiveSteps) {
        get().setResponsiveSteps(responsiveSteps);
        return this;
    }

    public FormLayoutFactory formItem(Component field, String label) {
        get().addFormItem(field, label);
        return this;
    }

    public FormLayoutFactory formItem(Component field, Component label) {
        get().addFormItem(field, label);
        return this;
    }

    public FormLayoutFactory formItem(boolean canAdd, Component field, String label) {
        return canAdd ? formItem(field, label) : this;
    }

    public FormLayoutFactory formItem(boolean canAdd, Component field, Component label) {
        return canAdd ? formItem(field, label) : this;
    }

    public FormLayoutFactory formItemReadField(String value, String label) {
        return formItem(new TextFieldFactory().value(value).tooltip(value).widthFull().readOnly().lumoSmall().get(), label);
    }

    public FormLayoutFactory formItemReadField(String value, String tooltip, String label) {
        return formItem(new TextFieldFactory().value(value).tooltip(tooltip).widthFull().readOnly().lumoSmall().get(), label);
    }

    public FormLayoutFactory formItemReadArea(String value, String label) {
        return formItem(new TextAreaFactory().value(value).tooltip(value).widthFull().readOnly().lumoSmall().get(), label);
    }

    public FormLayoutFactory formItemReadField(long value, String label) {
        return formItemReadField(new DigitalFormatter(value).toInteger(), label);
    }

    public FormLayoutFactory formItemReadField(double value, String label) {
        return formItemReadField(new DigitalFormatter(value).toFormatted(), label);
    }

    public FormLayoutFactory formItemReadComboBox(String value, String label) {
        return formItem(new ComboBoxFactory<String>().items(value).value(value).widthFull().readOnly().lumoSmall().get(), label);
    }

    public FormLayoutFactory formItemReadRadioGroup(Collection<String> items, String value, String label) {
        return formItemReadRadioGroup(items, value, a -> a, label);
    }

    public FormLayoutFactory formItemReadCheckboxGroup(Collection<String> items, Collection<String> value, String label) {
        return formItemReadCheckboxGroup(items, value, a -> a, label);
    }

    public <R> FormLayoutFactory formItemReadRadioGroup(Collection<R> items, R value, ItemLabelGenerator<R> function, String label) {
        return formItem(new RadioButtonGroupFactory<R>().items(items).renderer(new TextRenderer<>(function)).value(value)
                .valueChangeListener(e -> Optional.ofNullable(value).ifPresentOrElse(e.getSource()::setValue, e.getSource()::clear))
                .lumoSmall().get(), label);
    }

    public <R> FormLayoutFactory formItemReadCheckboxGroup(Collection<R> items, Collection<R> value, ItemLabelGenerator<R> function, String label) {
        Set<R> set = value == null ? Collections.emptySet() : Collections.unmodifiableSet(new LinkedHashSet<>(value));
        return formItem(new CheckboxGroupFactory<R>().items(items).itemLabelGenerator(function).value(set)
                .valueChangeListener(e -> e.getSource().setValue(set)).lumoSmall().get(), label);
    }

    public FormLayoutFactory colspan(Component component, int colspan) {
        get().setColspan(component, colspan);
        return this;
    }

    /**
     * Align right for label, must be calling after add all component.
     *
     * @return this
     *
     * @see FormLayout.FormItem only support change has been added label to align end
     */
    public FormLayoutFactory formItemAlignEnd() {
        getItemLabels().forEach(label -> label.getStyle().set("display", "flex").set("flex-direction", "row-reverse").set("text-align", "right"));
        return this;
    }

    public FormLayoutFactory formLabelsWidth(String width) {
        getItemLabels().forEach(a -> a.setWidth(width));
        return this;
    }

    public FormLayoutFactory formLabelsWidth(String minWidth, String maxWidth) {
        getItemLabels().forEach(a -> {
            a.setMinWidth(minWidth);
            a.setMaxWidth(maxWidth);
        });
        return this;
    }

    public FormLayoutFactory formLabelsWidth(String minWidth, String width, String maxWidth) {
        getItemLabels().forEach(a -> {
            a.setMinWidth(minWidth);
            a.setWidth(width);
            a.setMaxWidth(maxWidth);
        });
        return this;
    }

    /**
     * @return form layout FormLayout.FormItem labels
     *
     * @see FormLayout.FormItem only support get form layout labels.
     */
    public List<Label> getItemLabels() {
        List<Label> labels = new ArrayList<>();
        get().getChildren().forEach(component -> {
            if (component instanceof FormLayout.FormItem) {
                component.getElement().getChildren().forEach(s -> {
                    if (Objects.equals("label", s.getAttribute("slot"))) {
                        try {
                            labels.add(s.as(Label.class));
                        } catch (Exception e) {
                            LoggerFactory.getLogger(getClass()).warn("获取Form Layout Labels失败。", e);
                        }
                    }
                });
            }
        });
        return labels;
    }

    public FormLayoutFactory formItemAlignVerticalCenter() {
        get().getChildren().forEach(component -> {
            if (component instanceof FormLayout.FormItem) {
                ((FormLayout.FormItem) component).getStyle().set("align-items", "center");
            }
        });
        return this;
    }

    public FormLayoutFactory warpWhenOverflow() {
        get().getChildren().forEach(component -> component.getElement().getStyle().set("flex-wrap", "warp"));
        return this;
    }

    public FormLayoutFactory componentFullWidth() {
        get().getChildren().forEach(component -> get().setColspan(component, 3));
        return this;
    }

    public FormLayoutFactory initFormLayout() {
        return formItemAlignEnd().formItemAlignVerticalCenter().warpWhenOverflow().componentFullWidth();
    }

    public FormLayoutFactory fitModalDialogWidth() {
        return width("fit-content").style("margin-right", "3em");
    }
}
