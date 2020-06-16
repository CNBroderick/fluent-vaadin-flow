package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenuBase;
import com.vaadin.flow.component.contextmenu.GeneratedVaadinContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItemBase;
import com.vaadin.flow.component.contextmenu.SubMenuBase;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ContextMenuBaseFactory<C extends ContextMenuBase<C, I, S>, I extends MenuItemBase<C, I, S>, S extends SubMenuBase<C, I, S>, E extends ContextMenuBaseFactory<C, I, S, E>> extends IFlowFactory<C>, GeneratedVaadinContextMenuFactory<C, E>, HasComponentsFactory<C, E> {

    default E add(Component... add) {
        get().add(add);
        return (E) this;
    }

    default E remove(Component... remove) {
        get().remove(remove);
        return (E) this;
    }

    default E close() {
        get().close();
        return (E) this;
    }

    default E target(Component target) {
        get().setTarget(target);
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

    default E openedChangeListener(ComponentEventListener<GeneratedVaadinContextMenu.OpenedChangeEvent<C>> openedChangeListener) {
        get().addOpenedChangeListener(openedChangeListener);
        return (E) this;
    }

    default E item(String item) {
        get().addItem(item);
        return (E) this;
    }

    default E item(Component item) {
        get().addItem(item);
        return (E) this;
    }

    default E openOnClick(boolean openOnClick) {
        get().setOpenOnClick(openOnClick);
        return (E) this;
    }


}
