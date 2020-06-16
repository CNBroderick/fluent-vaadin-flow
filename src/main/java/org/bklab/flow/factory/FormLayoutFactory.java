package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.GeneratedVaadinFormLayoutFactory;
import org.bklab.flow.base.HasComponentsFactory;
import org.bklab.flow.base.HasSizeFactory;

import java.util.List;

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

    public FormLayoutFactory colspan(Component component, int colspan) {
        get().setColspan(component, colspan);
        return this;
    }

}
