/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-01 12:53:31
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.GeneratedVaadinRadioGroupFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.radiobutton.GeneratedVaadinRadioGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinRadioGroupFactory<T, C extends GeneratedVaadinRadioGroup<C, T>, E extends GeneratedVaadinRadioGroupFactory<T, C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<T, C, E>,
        HasStyleFactory<C, E>,
        HasThemeFactory<C, E> {

    default E themeVariants(RadioGroupVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E removeThemeVariants(RadioGroupVariant... removeThemeVariants) {
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
