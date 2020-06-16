package org.bklab.flow.factory;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

public class TextFieldFactory extends FlowFactory<TextField, TextFieldFactory> implements
        GeneratedVaadinTextFieldFactory<String, TextField, TextFieldFactory>,
        HasSizeFactory<TextField, TextFieldFactory>,
        HasValidationFactory<TextField, TextFieldFactory>,
        HasValueChangeModeFactory<TextField, TextFieldFactory>,
        HasPrefixAndSuffixFactory<TextField, TextFieldFactory>,
        InputNotifierFactory<TextField, TextFieldFactory>,
        KeyNotifierFactory<TextField, TextFieldFactory>,
        CompositionNotifierFactory<TextField, TextFieldFactory>,
        HasAutocompleteFactory<TextField, TextFieldFactory>,
        HasAutocapitalizeFactory<TextField, TextFieldFactory>,
        HasAutocorrectFactory<TextField, TextFieldFactory> {

    public TextFieldFactory() {
        this(new TextField());
    }

    public TextFieldFactory(String label) {
        this(new TextField(label));
    }

    public TextFieldFactory(String label, String placeholder) {
        this(new TextField(label, placeholder));
    }

    public TextFieldFactory(String label, String initialValue, String placeholder) {
        this(new TextField(label, initialValue, placeholder));
    }

    public TextFieldFactory(TextField component) {
        super(component);
    }

    public TextFieldFactory value(String value) {
        get().setValue(value);
        return this;
    }

    public TextFieldFactory valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return this;
    }

    public TextFieldFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public TextFieldFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public TextFieldFactory autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return this;
    }

    public TextFieldFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public TextFieldFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public TextFieldFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public TextFieldFactory maxLength(int maxLength) {
        get().setMaxLength(maxLength);
        return this;
    }

    public TextFieldFactory pattern(String pattern) {
        get().setPattern(pattern);
        return this;
    }

    public TextFieldFactory title(String title) {
        get().setTitle(title);
        return this;
    }

    public TextFieldFactory minLength(int minLength) {
        get().setMinLength(minLength);
        return this;
    }

    public TextFieldFactory required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public TextFieldFactory preventInvalidInput(boolean preventInvalidInput) {
        get().setPreventInvalidInput(preventInvalidInput);
        return this;
    }

    public TextFieldFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public TextFieldFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public TextFieldFactory valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return this;
    }

}
