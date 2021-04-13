package org.bklab.flow.base;

import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.checkbox.GeneratedVaadinCheckboxGroup;
import org.bklab.flow.IFluentFlowFactory;

import java.util.Set;

public interface GeneratedVaadinCheckboxGroupFactory<T, C extends GeneratedVaadinCheckboxGroup<C, Set<T>>,
        E extends GeneratedVaadinCheckboxGroupFactory<T, C, E>> extends
        AbstractSinglePropertyFieldFactory<Set<T>, C, E>, HasStyleFactory<C, E>, HasThemeFactory<C, E>, IFluentFlowFactory<C, E> {

    default E addThemeVariants(CheckboxGroupVariant... variants) {
        get().addThemeVariants(variants);
        return thisObject();
    }

    default E lumoVertical() {
        get().addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        return thisObject();
    }

    default E lumoHelperAboveField() {
        get().addThemeVariants(CheckboxGroupVariant.LUMO_HELPER_ABOVE_FIELD);
        return thisObject();
    }

    default E materialVertical() {
        get().addThemeVariants(CheckboxGroupVariant.MATERIAL_VERTICAL);
        return thisObject();
    }
}
