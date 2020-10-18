package org.bklab.flow.base;

import com.vaadin.flow.component.progressbar.GeneratedVaadinProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinProgressBarFactory<
        C extends GeneratedVaadinProgressBar<C>,
        E extends GeneratedVaadinProgressBarFactory<C, E>> extends IFlowFactory<C> {
    default E themeVariants(ProgressBarVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E removeThemeVariants(ProgressBarVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E lumoContrast() {
        get().addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);
        return (E) this;
    }

    default E lumoError() {
        get().addThemeVariants(ProgressBarVariant.LUMO_ERROR);
        return (E) this;
    }

    default E lumoSuccess() {
        get().addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);
        return (E) this;
    }
}
