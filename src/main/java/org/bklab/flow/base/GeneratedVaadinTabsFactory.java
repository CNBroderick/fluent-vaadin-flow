package org.bklab.flow.base;

import com.vaadin.flow.component.tabs.GeneratedVaadinTabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface GeneratedVaadinTabsFactory<C extends GeneratedVaadinTabs<C>, E extends GeneratedVaadinTabsFactory<C, E>> extends IFlowFactory<C>,
        HasStyleFactory<C, E>, HasThemeFactory<C, E> {
    default E themeVariants(TabsVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return (E) this;
    }

    default E removeThemeVariants(TabsVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return (E) this;
    }

    default E lumoIconOnTop() {
        get().addThemeVariants(TabsVariant.LUMO_ICON_ON_TOP);
        return (E) this;
    }

    default E lumoCentered() {
        get().addThemeVariants(TabsVariant.LUMO_CENTERED);
        return (E) this;
    }

    default E lumoSmall() {
        get().addThemeVariants(TabsVariant.LUMO_SMALL);
        return (E) this;
    }

    default E lumoMinimal() {
        get().addThemeVariants(TabsVariant.LUMO_MINIMAL);
        return (E) this;
    }

    default E lumoHideScrollButtons() {
        get().addThemeVariants(TabsVariant.LUMO_HIDE_SCROLL_BUTTONS);
        return (E) this;
    }

    default E lumoEqualWidthTabs() {
        get().addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        return (E) this;
    }

    default E materialFixed() {
        get().addThemeVariants(TabsVariant.MATERIAL_FIXED);
        return (E) this;
    }
}
