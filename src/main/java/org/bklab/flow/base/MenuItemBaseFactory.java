package org.bklab.flow.base;

import com.vaadin.flow.component.contextmenu.ContextMenuBase;
import com.vaadin.flow.component.contextmenu.MenuItemBase;
import com.vaadin.flow.component.contextmenu.SubMenuBase;
import org.bklab.flow.IFlowFactory;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface MenuItemBaseFactory<C extends ContextMenuBase<C, I, S>, I extends MenuItemBase<C, I, S>, S extends SubMenuBase<C, I, S>, E extends MenuItemBaseFactory<C, I, S, E>> extends Supplier<I>,
        HasTextFactory<I, E>,
        HasComponentsFactory<I, E>,
        HasEnabledFactory<I, E> {
    default E checked(boolean checked) {
        get().setChecked(checked);
        return (E) this;
    }

    default E checkable(boolean checkable) {
        get().setCheckable(checkable);
        return (E) this;
    }
}
