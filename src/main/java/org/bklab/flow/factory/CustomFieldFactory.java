package org.bklab.flow.factory;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.customfield.CustomField;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.AbstractFieldFactory;
import org.bklab.flow.base.HasHelperFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.base.HasValidationFactory;

public class CustomFieldFactory<T> extends FlowFactory<CustomField<T>, CustomFieldFactory<T>> implements
        AbstractFieldFactory<T, CustomField<T>, CustomFieldFactory<T>>,
        HasSizeFactory<CustomField<T>, CustomFieldFactory<T>>,
        HasHelperFactory<CustomField<T>, CustomFieldFactory<T>>,
        HasValidationFactory<CustomField<T>, CustomFieldFactory<T>> {

    public CustomFieldFactory(CustomField<T> component) {
        super(component);
    }

    public CustomFieldFactory<T> label(String label) {
        get().setLabel(label);
        return this;
    }

    public CustomFieldFactory<T> invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public CustomFieldFactory<T> errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public CustomFieldFactory<T> focusShortcut(Key key, KeyModifier... keyModifiers) {
        get().addFocusShortcut(key, keyModifiers);
        return this;
    }

    public CustomFieldFactory<T> tabIndex(int tabIndex) {
        get().setTabIndex(tabIndex);
        return this;
    }

    public CustomFieldFactory<T> blur() {
        get().blur();
        return this;
    }

    public CustomFieldFactory<T> focus() {
        get().focus();
        return this;
    }
}
