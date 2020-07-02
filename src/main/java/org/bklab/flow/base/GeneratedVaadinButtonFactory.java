/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-17 20:35:31
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.GeneratedVaadinButtonFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.button.GeneratedVaadinButton;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinButtonFactory<C extends GeneratedVaadinButton<C>, E extends GeneratedVaadinButtonFactory<C, E>> extends IFlowFactory<C>,
        HasStyleFactory<C, E>,
        ClickNotifierFactory<C, E>,
        HasTextFactory<C, E>,
        FocusableFactory<C, E>,
        HasThemeFactory<C, E> {

    default E removeThemeVariants(ButtonVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E themeVariants(ButtonVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E lumoSmall() {
        get().addThemeVariants(ButtonVariant.LUMO_SMALL);
        return (E) this;
    }

    default E lumoLarge() {
        get().addThemeVariants(ButtonVariant.LUMO_LARGE);
        return (E) this;
    }

    default E lumoTertiary() {
        get().addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return (E) this;
    }

    default E lumoTertiaryInline() {
        get().addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        return (E) this;
    }

    default E lumoPrimary() {
        get().addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return (E) this;
    }

    default E lumoSuccess() {
        get().addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        return (E) this;
    }

    default E lumoError() {
        get().addThemeVariants(ButtonVariant.LUMO_ERROR);
        return (E) this;
    }

    default E lumoContrast() {
        get().addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        return (E) this;
    }

    default E lumoIcon() {
        get().addThemeVariants(ButtonVariant.LUMO_ICON);
        return (E) this;
    }

    default E materialContained() {
        get().addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        return (E) this;
    }

    default E materialOutlined() {
        get().addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
        return (E) this;
    }

}
