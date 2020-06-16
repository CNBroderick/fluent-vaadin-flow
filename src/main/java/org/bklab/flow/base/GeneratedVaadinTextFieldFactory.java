package org.bklab.flow.base;

import com.vaadin.flow.component.textfield.GeneratedVaadinTextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinTextFieldFactory<T, C extends GeneratedVaadinTextField<C, T>, E extends GeneratedVaadinTextFieldFactory<T, C, E>> extends
        AbstractSinglePropertyFieldFactory<T, C, E>,
        IFlowFactory<C>,
        HasStyleFactory<C, E>,
        FocusableFactory<C, E>,
        HasThemeFactory<C, E> {

    default E removeThemeVariants(TextFieldVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E themeVariants(TextFieldVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E lumoSmall() {
        get().addThemeVariants(TextFieldVariant.LUMO_SMALL);
        return (E) this;
    }

    default E lumoAlignCenter() {
        get().addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        return (E) this;
    }

    default E lumoAlignRight() {
        get().addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        return (E) this;
    }

    default E materialAlwaysFloatLabel() {
        get().addThemeVariants(TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        return (E) this;
    }
}
