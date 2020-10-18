package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.util.Optional;

public class TextAreaFactory extends FlowFactory<TextArea, TextAreaFactory> implements
        GeneratedVaadinTextAreaFactory<TextArea, TextAreaFactory>,
        HasSizeFactory<TextArea, TextAreaFactory>,
        HasValidationFactory<TextArea, TextAreaFactory>,
        HasValueChangeModeFactory<TextArea, TextAreaFactory>,
        HasPrefixAndSuffixFactory<TextArea, TextAreaFactory>,
        InputNotifierFactory<TextArea, TextAreaFactory>,
        KeyNotifierFactory<TextArea, TextAreaFactory>,
        CompositionNotifierFactory<TextArea, TextAreaFactory>,
        HasAutocompleteFactory<TextArea, TextAreaFactory>,
        HasAutocapitalizeFactory<TextArea, TextAreaFactory>,
        HasHelperFactory<TextArea, TextAreaFactory>,
        HasAutocorrectFactory<TextArea, TextAreaFactory> {

    public TextAreaFactory() {
        this(new TextArea());
    }

    public TextAreaFactory(String label) {
        this(new TextArea(label));
    }

    public TextAreaFactory(String label, String placeholder) {
        this(new TextArea(label, placeholder));
    }

    public TextAreaFactory(String label, String initialValue, String placeholder) {
        this(new TextArea(label, initialValue, placeholder));
    }

    public TextAreaFactory(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextArea, String>> listener) {
        this(new TextArea(listener));
    }

    public TextAreaFactory(String label, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextArea, String>> listener) {
        this(new TextArea(label, listener));
    }

    public TextAreaFactory(String label, String initialValue, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextArea, String>> listener) {
        this(new TextArea(label, initialValue, listener));
    }

    public TextAreaFactory(TextArea component) {
        super(component);
    }

    public TextAreaFactory value(String value) {
        get().setValue(Optional.ofNullable(value).orElse(""));
        return this;
    }

    public TextAreaFactory valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return this;
    }

    public TextAreaFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public TextAreaFactory preventInvalidInput(boolean preventInvalidInput) {
        get().setPreventInvalidInput(preventInvalidInput);
        return this;
    }

    public TextAreaFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public TextAreaFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public TextAreaFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public TextAreaFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public TextAreaFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public TextAreaFactory autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return this;
    }

    public TextAreaFactory maxLength(int maxLength) {
        get().setMaxLength(maxLength);
        return this;
    }

    public TextAreaFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public TextAreaFactory minLength(int minLength) {
        get().setMinLength(minLength);
        return this;
    }

    public TextAreaFactory required(boolean required) {
        get().setRequired(required);
        return this;
    }

    public TextAreaFactory valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return this;
    }

}
