package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenuBase;
import com.vaadin.flow.component.contextmenu.MenuItemBase;
import com.vaadin.flow.component.contextmenu.SubMenuBase;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface SubMenuBaseFactory<C extends ContextMenuBase<C, I, S>, I extends MenuItemBase<C, I, S>, S extends SubMenuBase<C, I, S>, E extends SubMenuBaseFactory<C, I, S, E>> extends Supplier<S> {

    default E add(Component... add) {
        get().add(add);
        return (E) this;
    }

    default E remove(Component... remove) {
        get().remove(remove);
        return (E) this;
    }

    default E removeAll() {
        get().removeAll();
        return (E) this;
    }

    default E componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return (E) this;
    }

    default E item(Component item) {
        get().addItem(item);
        return (E) this;
    }

    default E item(String item) {
        get().addItem(item);
        return (E) this;
    }

}