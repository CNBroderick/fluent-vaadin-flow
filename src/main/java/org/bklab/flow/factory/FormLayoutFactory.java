/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-27 13:58:07
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.FormLayoutFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.GeneratedVaadinFormLayoutFactory;
import org.bklab.flow.base.HasComponentsFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
