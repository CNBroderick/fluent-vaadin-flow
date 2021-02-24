package org.bklab.flow.factory;

import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

import java.math.BigDecimal;
import java.util.Locale;

public class BigDecimalFieldFactory extends FlowFactory<BigDecimalField, BigDecimalFieldFactory> implements
        GeneratedVaadinTextFieldFactory<BigDecimal, BigDecimalField, BigDecimalFieldFactory>,
        HasSizeFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasValidationFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasValueChangeModeFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasPrefixAndSuffixFactory<BigDecimalField, BigDecimalFieldFactory>,
        InputNotifierFactory<BigDecimalField, BigDecimalFieldFactory>,
        KeyNotifierFactory<BigDecimalField, BigDecimalFieldFactory>,
        CompositionNotifierFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasAutocompleteFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasAutocapitalizeFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasAutocorrectFactory<BigDecimalField, BigDecimalFieldFactory>,
        HasHelperFactory<BigDecimalField, BigDecimalFieldFactory> {

    public BigDecimalFieldFactory() {
        this(new BigDecimalField());
    }

    public BigDecimalFieldFactory(String label) {
        this(new BigDecimalField(label));
    }

    public BigDecimalFieldFactory(String label, String placeholder) {
        this(new BigDecimalField(label, placeholder));
    }

    public BigDecimalFieldFactory(String label, BigDecimal initialValue, String placeholder) {
        this(new BigDecimalField(label, initialValue, placeholder));
    }


    public BigDecimalFieldFactory(BigDecimalField component) {
        super(component);
    }

    public BigDecimalFieldFactory value(BigDecimal value) {
        get().setValue(value);
        return this;
    }

    public BigDecimalFieldFactory locale(Locale locale) {
        get().setLocale(locale);
        return this;
    }

    public BigDecimalFieldFactory clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return this;
    }

    public BigDecimalFieldFactory requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public BigDecimalFieldFactory valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return this;
    }

    public BigDecimalFieldFactory invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public BigDecimalFieldFactory valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return this;
    }

    public BigDecimalFieldFactory errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public BigDecimalFieldFactory label(String label) {
        get().setLabel(label);
        return this;
    }

    public BigDecimalFieldFactory placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public BigDecimalFieldFactory autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return this;
    }

    public BigDecimalFieldFactory autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public BigDecimalFieldFactory title(String title) {
        get().setTitle(title);
        return this;
    }

}
