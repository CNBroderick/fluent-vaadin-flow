package org.bklab.flow.factory;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasMenuItemsFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.base.HasStyleFactory;
import org.bklab.flow.base.HasThemeFactory;

public class MenuBarFactory extends FlowFactory<MenuBar, MenuBarFactory> implements
        HasMenuItemsFactory<MenuBar, MenuBarFactory>,
        HasSizeFactory<MenuBar, MenuBarFactory>,
        HasStyleFactory<MenuBar, MenuBarFactory>,
        HasThemeFactory<MenuBar, MenuBarFactory> {
    public MenuBarFactory() {
        this(new MenuBar());
    }

    public MenuBarFactory(MenuBar component) {
        super(component);
    }

    public MenuBarFactory remove(MenuItem[] remove) {
        get().remove(remove);
        return this;
    }

    public MenuBarFactory removeAll() {
        get().removeAll();
        return this;
    }

    public MenuBarFactory removeThemeVariants(MenuBarVariant[] removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return this;
    }

    public MenuBarFactory item(String item) {
        get().addItem(item);
        return this;
    }

    public MenuBarFactory item(Component item) {
        get().addItem(item);
        return this;
    }

    public MenuBarFactory item(Component component, ComponentEventListener<ClickEvent<MenuItem>> menuItem) {
        get().addItem(component, menuItem);
        return this;
    }

    public MenuBarFactory item(String string, ComponentEventListener<ClickEvent<MenuItem>> menuItem) {
        get().addItem(string, menuItem);
        return this;
    }

    public MenuBarFactory openOnHover(boolean openOnHover) {
        get().setOpenOnHover(openOnHover);
        return this;
    }

    public MenuBarFactory themeVariants(MenuBarVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return this;
    }

    public MenuBarFactory lumoSmall() {
        get().addThemeVariants(MenuBarVariant.LUMO_SMALL);
        return this;
    }

    public MenuBarFactory lumoLarge() {
        get().addThemeVariants(MenuBarVariant.LUMO_LARGE);
        return this;
    }

    public MenuBarFactory lumoTertiary() {
        get().addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        return this;
    }

    public MenuBarFactory lumoTertiaryInline() {
        get().addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        return this;
    }

    public MenuBarFactory lumoPrimary() {
        get().addThemeVariants(MenuBarVariant.LUMO_PRIMARY);
        return this;
    }

    public MenuBarFactory lumoContrast() {
        get().addThemeVariants(MenuBarVariant.LUMO_CONTRAST);
        return this;
    }

    public MenuBarFactory lumoIcon() {
        get().addThemeVariants(MenuBarVariant.LUMO_ICON);
        return this;
    }

    public MenuBarFactory materialContained() {
        get().addThemeVariants(MenuBarVariant.MATERIAL_CONTAINED);
        return this;
    }

    public MenuBarFactory materialOutlined() {
        get().addThemeVariants(MenuBarVariant.MATERIAL_OUTLINED);
        return this;
    }
}
