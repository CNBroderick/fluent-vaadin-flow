package org.bklab.flow.base;

import com.vaadin.flow.component.splitlayout.GeneratedVaadinSplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinSplitLayoutFactory<C extends GeneratedVaadinSplitLayout<C>, E extends GeneratedVaadinSplitLayoutFactory<C, E>> extends IFlowFactory<C>,
        HasStyleFactory<C, E>,
        ClickNotifierFactory<C, E>,
        HasThemeFactory<C, E> {
    default E removeThemeVariants(SplitLayoutVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E themeVariants(SplitLayoutVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E lumoSmall() {
        get().addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        return (E) this;
    }

    default E lumoMinimal() {
        get().addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        return (E) this;
    }
}
