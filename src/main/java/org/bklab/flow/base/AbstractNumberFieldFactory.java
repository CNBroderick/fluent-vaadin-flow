package org.bklab.flow.base;

import com.vaadin.flow.component.textfield.AbstractNumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface AbstractNumberFieldFactory<T extends Number, C extends AbstractNumberField<C, T>, E extends AbstractNumberFieldFactory<T, C, E>> extends IFlowFactory<C>,
        HasSizeFactory<C, E>,
        HasValidationFactory<C, E>,
        HasValueChangeModeFactory<C, E>,
        HasPrefixAndSuffixFactory<C, E>,
        InputNotifierFactory<C, E>,
        KeyNotifierFactory<C, E>,
        CompositionNotifierFactory<C, E>,
        HasAutocompleteFactory<C, E>,
        HasAutocapitalizeFactory<C, E>,
        HasAutocorrectFactory<C, E> {

    default E value(T value) {
        get().setValue(value);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }

    default E valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return (E) this;
    }

    default E clearButtonVisible(boolean clearButtonVisible) {
        get().setClearButtonVisible(clearButtonVisible);
        return (E) this;
    }

    default E invalid(boolean invalid) {
        get().setInvalid(invalid);
        return (E) this;
    }

    default E errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return (E) this;
    }

    default E autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return (E) this;
    }

    default E hasControls(boolean hasControls) {
        get().setHasControls(hasControls);
        return (E) this;
    }

    default E label(String label) {
        get().setLabel(label);
        return (E) this;
    }

    default E autoselect(boolean autoselect) {
        get().setAutoselect(autoselect);
        return (E) this;
    }

    default E title(String title) {
        get().setTitle(title);
        return (E) this;
    }

    default E valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return (E) this;
    }

    default E hasControls() {
        get().hasControls();
        return (E) this;
    }

    default E placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return (E) this;
    }
}
