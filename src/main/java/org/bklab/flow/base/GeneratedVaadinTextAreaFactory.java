package org.bklab.flow.base;

import com.vaadin.flow.component.textfield.GeneratedVaadinTextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinTextAreaFactory<C extends GeneratedVaadinTextArea<C, String>, E extends GeneratedVaadinTextAreaFactory<C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<String, C, E>,
        HasStyleFactory<C, E>,
        FocusableFactory<C, E>,
        HasThemeFactory<C, E> {
    default E removeThemeVariants(TextAreaVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E themeVariants(TextAreaVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E lumoSmall() {
        get().addThemeVariants(TextAreaVariant.LUMO_SMALL);
        return (E) this;
    }

    default E lumoAlignCenter() {
        get().addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        return (E) this;
    }

    default E lumoAlignRight() {
        get().addThemeVariants(TextAreaVariant.LUMO_ALIGN_RIGHT);
        return (E) this;
    }

    default E materialAlwaysFloatLabel() {
        get().addThemeVariants(TextAreaVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        return (E) this;
    }
}
