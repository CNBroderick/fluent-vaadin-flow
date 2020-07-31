package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.checkbox.Checkbox;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinCheckboxFactory;
import org.bklab.flow.base.HasSizeFactory;

public class CheckboxFactory extends FlowFactory<Checkbox, CheckboxFactory> implements
        GeneratedVaadinCheckboxFactory<Boolean, Checkbox, CheckboxFactory>, HasSizeFactory<Checkbox, CheckboxFactory> {
    public CheckboxFactory() {
        super(new Checkbox());
    }

    public CheckboxFactory(boolean initialValue) {
        super(new Checkbox(initialValue));
    }

    public CheckboxFactory(String labelText, boolean initialValue) {
        super(new Checkbox(labelText, initialValue));
    }

    public CheckboxFactory(String label, HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Checkbox, Boolean>> listener) {
        super(new Checkbox(label, listener));
    }

    public CheckboxFactory(String label) {
        super(new Checkbox(label));
    }

    public CheckboxFactory labelAsHtml(String labelAsHtml) {
        get().setLabelAsHtml(labelAsHtml);
        return this;
    }

    public CheckboxFactory ariaLabel(String ariaLabel) {
        get().setAriaLabel(ariaLabel);
        return this;
    }

    public CheckboxFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public CheckboxFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public CheckboxFactory indeterminate(boolean indeterminate) {
        get().setIndeterminate(indeterminate);
        return this;
    }

    public CheckboxFactory autofocus() {
        get().setAutofocus(true);
        return this;
    }

    public CheckboxFactory indeterminate() {
        get().setIndeterminate(true);
        return this;
    }

}
