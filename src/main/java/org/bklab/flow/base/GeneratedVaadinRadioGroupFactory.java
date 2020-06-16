package org.bklab.flow.base;

import com.vaadin.flow.component.radiobutton.GeneratedVaadinRadioGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinRadioGroupFactory<T, C extends GeneratedVaadinRadioGroup<C, T>, E extends GeneratedVaadinRadioGroupFactory<T, C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<T, C, E>,
        HasStyleFactory<C, E>,
        HasThemeFactory<C, E> {

    default E themeVariants(RadioGroupVariant[] themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E removeThemeVariants(RadioGroupVariant[] removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E lumoVertical() {
        get().addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        return (E) this;
    }

    default E materialVertical() {
        get().addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        return (E) this;
    }

}
