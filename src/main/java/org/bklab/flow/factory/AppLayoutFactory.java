package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.RouterLayoutFactory;

public class AppLayoutFactory extends FlowFactory<AppLayout, AppLayoutFactory> implements RouterLayoutFactory<AppLayout, AppLayoutFactory> {
    public AppLayoutFactory() {
        this(new AppLayout());
    }

    public AppLayoutFactory(AppLayout component) {
        super(component);
    }

    public AppLayoutFactory remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public AppLayoutFactory showRouterLayoutContent(HasElement showRouterLayoutContent) {
        get().showRouterLayoutContent(showRouterLayoutContent);
        return this;
    }

    public AppLayoutFactory primarySection(AppLayout.Section primarySection) {
        get().setPrimarySection(primarySection);
        return this;
    }

    public AppLayoutFactory drawerOpened(boolean drawerOpened) {
        get().setDrawerOpened(drawerOpened);
        return this;
    }

    public AppLayoutFactory toDrawer(Component... toDrawer) {
        get().addToDrawer(toDrawer);
        return this;
    }

    public AppLayoutFactory toNavbar(boolean touchOptimized, Component... component) {
        get().addToNavbar(touchOptimized, component);
        return this;
    }

    public AppLayoutFactory toNavbar(Component... toNavbar) {
        get().addToNavbar(toNavbar);
        return this;
    }

    public AppLayoutFactory content(Component content) {
        get().setContent(content);
        return this;
    }
}
