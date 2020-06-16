package org.bklab.flow.factory;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ContextMenuBaseFactory;

public class ContextMenuFactory extends FlowFactory<ContextMenu, ContextMenuFactory> implements
        ContextMenuBaseFactory<ContextMenu, MenuItem, SubMenu, ContextMenuFactory> {
    public ContextMenuFactory() {
        this(new ContextMenu());
    }

    public ContextMenuFactory(Component target) {
        this(new ContextMenu(target));
    }

    public ContextMenuFactory(ContextMenu component) {
        super(component);
    }

    public ContextMenuFactory item(String string, ComponentEventListener<ClickEvent<MenuItem>> menuItem) {
        get().addItem(string, menuItem);
        return this;
    }

    public ContextMenuFactory item(Component component, ComponentEventListener<ClickEvent<MenuItem>> menuItem) {
        get().addItem(component, menuItem);
        return this;
    }
}
