package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

public class PasswordFieldFactory extends FlowFactory<PasswordField, PasswordFieldFactory> implements
        GeneratedVaadinPasswordFieldFactory<String, PasswordField, PasswordFieldFactory>,
        HasSizeFactory<PasswordField, PasswordFieldFactory>,
        HasValidationFactory<PasswordField, PasswordFieldFactory>,
        HasValueChangeModeFactory<PasswordField, PasswordFieldFactory>,
        HasPrefixAndSuffixFactory<PasswordField, PasswordFieldFactory>,
        InputNotifierFactory<PasswordField, PasswordFieldFactory>,
        KeyNotifierFactory<PasswordField, PasswordFieldFactory>,
        CompositionNotifierFactory<PasswordField, PasswordFieldFactory>,
        HasAutocompleteFactory<PasswordField, PasswordFieldFactory>,
        HasAutocapitalizeFactory<PasswordField, PasswordFieldFactory>,
        HasAutocorrectFactory<PasswordField, PasswordFieldFactory> {

    public PasswordFieldFactory() {
        this(new PasswordField());
    }

    public PasswordFieldFactory(String label) {
        this(new PasswordField(label));
    }

    public PasswordFieldFactory(String label, String placeholder) {
        this(new PasswordField(label, placeholder));
    }

    public PasswordFieldFactory(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<PasswordField, String>> listener) {
        this(new PasswordField(listener));
    }

    public PasswordFieldFactory(String label, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<PasswordField, String>> listener) {
        this(new PasswordField(label, listener));
    }

    public PasswordFieldFactory(String label, String initialValue, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<PasswordField, String>> listener) {
        this(new PasswordField(label, initialValue, listener));
    }

    public PasswordFieldFactory(PasswordField component) {
        super(component);
    }

    public PasswordFieldFactory value(String value) {
        get().setValue(value);
        return this;
    }

    public PasswordFieldFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public PasswordFieldFactory valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return this;
    }

    public PasswordFieldFactory preventInvalidInput(boolean preventInvalidInput) {
        get().setPreventInvalidInput(preventInvalidInput);
        return this;
    }

    public PasswordFieldFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public PasswordFieldFactory revealButtonVisible(boolean revealButtonVisible) {
        get().setRevealButtonVisible(revealButtonVisible);
        return this;
    }

    public PasswordFieldFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public PasswordFieldFactory minLength(int minLength) {
        get().setMinLength(minLength);
        return this;
    }

    public PasswordFieldFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public PasswordFieldFactory valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return this;
    }

    public PasswordFieldFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public PasswordFieldFactory required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public PasswordFieldFactory maxLength(int maxLength) {
        get().setMaxLength(maxLength);
        return this;
    }

    public PasswordFieldFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public PasswordFieldFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public PasswordFieldFactory title(String title) {
        get().setTitle(title);
        return this;
    }

    public PasswordFieldFactory pattern(String pattern) {
        get().setPattern(pattern);
        return this;
    }

    public PasswordFieldFactory autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return this;
    }
}
