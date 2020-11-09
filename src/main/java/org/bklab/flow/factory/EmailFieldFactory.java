package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.EmailField;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

public class EmailFieldFactory extends FlowFactory<EmailField, EmailFieldFactory> implements
        GeneratedVaadinEmailFieldFactory<EmailField, String, EmailFieldFactory>,
        HasSizeFactory<EmailField, EmailFieldFactory>,
        HasValidationFactory<EmailField, EmailFieldFactory>,
        HasValueChangeModeFactory<EmailField, EmailFieldFactory>,
        HasPrefixAndSuffixFactory<EmailField, EmailFieldFactory>,
        InputNotifierFactory<EmailField, EmailFieldFactory>,
        KeyNotifierFactory<EmailField, EmailFieldFactory>,
        CompositionNotifierFactory<EmailField, EmailFieldFactory>,
        HasAutocompleteFactory<EmailField, EmailFieldFactory>,
        HasAutocapitalizeFactory<EmailField, EmailFieldFactory>,
        HasAutocorrectFactory<EmailField, EmailFieldFactory> {

    public EmailFieldFactory() {
        this(new EmailField());
    }

    public EmailFieldFactory(String label) {
        this(new EmailField(label));
    }

    public EmailFieldFactory(String label, String placeholder) {
        this(new EmailField(label, placeholder));
    }

    public EmailFieldFactory(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<EmailField, String>> listener) {
        this(new EmailField(listener));
    }

    public EmailFieldFactory(String label, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<EmailField, String>> listener) {
        this(new EmailField(label, listener));
    }

    public EmailFieldFactory(String label, String initialValue, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<EmailField, String>> listener) {
        this(new EmailField(label, initialValue, listener));
    }

    public EmailFieldFactory(EmailField component) {
        super(component);
    }
}
