package org.bklab.flow.components.textfield;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.customfield.CustomField;
import org.bklab.flow.factory.CustomFieldFactory;

public class ComponentButtonField<T, C extends
        Component & HasValue<?, T>> extends CustomField<T> {

    private final C component;
    private final Button button;

    private ComponentButtonField(C component, Button button) {
        this.component = component;
        this.button = button;
        add(component, button);
        getElement().getStyle().set("alignItems", "baseline").set("display", "flex");
    }

    public C getComponent() {
        return component;
    }

    public Button getButton() {
        return button;
    }

    @Override
    protected T generateModelValue() {
        return component.getValue();
    }

    @Override
    protected void setPresentationValue(T newPresentationValue) {
        component.setValue(newPresentationValue);
    }

    public CustomFieldFactory<T> asFactory() {
        return new CustomFieldFactory<>(this);
    }
}