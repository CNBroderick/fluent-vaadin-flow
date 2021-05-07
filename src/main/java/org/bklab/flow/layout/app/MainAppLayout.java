/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-29 10:12:39
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.app.MainAppLayout
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.layout.app;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.Lumo;
import org.bklab.flow.components.navigation.bar.AppBar;
import org.bklab.flow.components.navigation.bar.TabBar;
import org.bklab.flow.components.navigation.drawer.NaviDrawer;
import org.bklab.flow.components.navigation.drawer.NaviItem;
import org.bklab.flow.components.navigation.drawer.NaviMenu;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.util.css.Display;
import org.bklab.flow.util.css.Overflow;
import org.bklab.flow.util.lumo.UIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

@CssImport(value = "./styles/components/charts.css", themeFor = "vaadin-chart", include = "vaadin-chart-default-theme")
@CssImport(value = "./styles/components/floating-action-button.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/components/grid.css", themeFor = "vaadin-grid")
@CssImport("./styles/lumo/border-radius.css")
@CssImport("./styles/lumo/icon-size.css")
@CssImport("./styles/lumo/margin.css")
@CssImport("./styles/lumo/padding.css")
@CssImport("./styles/lumo/shadow.css")
@CssImport("./styles/lumo/spacing.css")
@CssImport("./styles/lumo/typography.css")
@CssImport("./styles/misc/box-shadow-borders.css")
@CssImport(value = "./styles/styles.css", include = "lumo-badge")
@CssImport("./styles/org/bklab/component/fluent-flow-shared-styles.css")
@JsModule("./styles/org/bklab/component/fluent-vaadin-flow-shared-styles.js")
@JsModule("@vaadin/vaadin-lumo-styles/badge")
public abstract class MainAppLayout extends FlexBoxLayout implements RouterLayout, AfterNavigationObserver {

    private static final Logger logger = LoggerFactory.getLogger(MainAppLayout.class);
    private static final String CLASS_NAME = "root";

    private static final Map<UI, List<Consumer<MainAppLayout>>> asynchronousSynchronizationMap = new LinkedHashMap<>();
    private static final Map<UI, MainAppLayout> MAIN_APP_LAYOUT_MAP = new LinkedHashMap<>();

    protected FlexBoxLayout row;
    protected NaviDrawer naviDrawer;
    protected FlexBoxLayout column;

    protected Div appHeaderOuter;
    protected Div appHeaderInner;
    protected Main viewContainer;

    protected Div appFooterInner;
    protected Div appFooterOuter;

    protected TabBar tabBar;
    protected boolean navigationTabs = false;
    protected AppBar appBar;

    public MainAppLayout() {
        addClassName(CLASS_NAME);
        setFlexDirection(FlexDirection.COLUMN);
        setSizeFull();

        // Initialise the UI building blocks
        initStructure();

        // Populate the navigation drawer
        buildNaviMenu(naviDrawer.getMenu());

        // Configure the headers and footers (optional)
        initHeadersAndFooters();

        attachAsynchronousListeners();
    }

    public static void extend(Consumer<MainAppLayout> consumer) {
        extend(UI.getCurrent(), consumer);
    }

    public static void extend(UI ui, Consumer<MainAppLayout> consumer) {
        if (ui == null) return;
        getCurrent(ui).ifPresentOrElse(consumer, () -> {
            List<Consumer<MainAppLayout>> consumers = null;
            if (asynchronousSynchronizationMap.containsKey(ui)) {
                consumers = asynchronousSynchronizationMap.get(ui);
            }
            if (consumers == null) {
                consumers = new ArrayList<>();
                asynchronousSynchronizationMap.put(ui, consumers);
            }
            consumers.add(consumer);
            ui.addDetachListener(detachEvent -> asynchronousSynchronizationMap.remove(ui));
        });
    }

    public static MainAppLayout get() {
        return getCurrent().orElse(null);
    }

    public static Optional<MainAppLayout> getCurrent() {
        return getCurrent(UI.getCurrent());
    }

    public static Optional<MainAppLayout> getCurrent(UI ui) {
        MainAppLayout mainAppLayout = MAIN_APP_LAYOUT_MAP.get(ui);
        return mainAppLayout != null ? Optional.of(mainAppLayout) : ui.getChildren()
                .filter(component -> component.getClass().isAssignableFrom(MainAppLayout.class))
                .findFirst().map(MainAppLayout.class::cast);
    }

    protected void attachAsynchronousListeners() {
        Registration registration = addAttachListener(attachEvent -> {
            MAIN_APP_LAYOUT_MAP.put(attachEvent.getUI(), this);
            if (asynchronousSynchronizationMap.containsKey(attachEvent.getUI())) {
                asynchronousSynchronizationMap.getOrDefault(attachEvent.getUI(), Collections.emptyList())
                        .stream().filter(Objects::nonNull).forEach(a -> a.accept(this));
                asynchronousSynchronizationMap.remove(attachEvent.getUI());
            }
        });

        addDetachListener(detachEvent -> {
            MAIN_APP_LAYOUT_MAP.remove(detachEvent.getUI());
            registration.remove();
        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        appBar.reset();
        buildUserIcon(appBar.getAvatar(), appBar.getUserIconMenu());
    }

    public abstract void buildNaviMenu(NaviMenu naviMenu);

    public abstract void buildUserIcon(Image image, ContextMenu contextMenu);

    protected abstract Class<? extends Component> defaultNavigationTarget();

    public MainAppLayout setNavigationTabs(boolean navigationTabs) {
        this.navigationTabs = navigationTabs;
        return this;
    }

    /**
     * Initialise the required components and containers.
     */
    private void initStructure() {
        naviDrawer = new NaviDrawer();

        viewContainer = new Main();
        viewContainer.addClassName(CLASS_NAME + "__view-container");
        viewContainer.getStyle().set("margin", "0.3em");
        UIUtils.setDisplay(Display.FLEX, viewContainer);
        UIUtils.setFlexGrow(1, viewContainer);
        UIUtils.setOverflow(Overflow.HIDDEN, viewContainer);

        column = new FlexBoxLayout(viewContainer);
        column.addClassName(CLASS_NAME + "__column");
        column.setFlexDirection(FlexDirection.COLUMN);
        column.setFlexGrow(1, viewContainer);
        column.setOverflow(Overflow.HIDDEN);

        row = new FlexBoxLayout(naviDrawer, column);
        row.addClassName(CLASS_NAME + "__row");
        row.setFlexGrow(1, column);
        row.setOverflow(Overflow.HIDDEN);
        add(row);
        setFlexGrow(1, row);
    }

    /**
     * Configure the app's inner and outer headers and footers.
     */
    private void initHeadersAndFooters() {
//		 setAppHeaderOuter();
        // setAppFooterInner();
        // setAppFooterOuter();

        // Default inner header setup:
        // - When using tabbed navigation the view title, user avatar and main menu button will appear in the TabBar.
        // - When tabbed navigation is turned off they appear in the AppBar.

        appBar = new AppBar("");

        naviDrawer.getMenu().getNaviItems().forEach(a -> a.addClickListener(event -> appBar.reset()));

        // Tabbed navigation
        if (navigationTabs) {
            tabBar = new TabBar();
            UIUtils.setTheme(Lumo.DARK, tabBar);

            // Shift-click to add a new tab
            for (NaviItem item : naviDrawer.getMenu().getNaviItems()) {
                item.addClickListener(e -> {
                    if (e.getButton() == 0 && e.isShiftKey()) {
                        tabBar.setSelectedTab(tabBar.addClosableTab(item.getText(), item.getNavigationTarget()));
                    }
                });
            }
            setAppHeaderInner(tabBar, appBar);

            // Default navigation
        } else {
            UIUtils.setTheme(Lumo.DARK, appBar);
            setAppHeaderInner(appBar);
        }
    }

    private void setAppHeaderOuter(Component... components) {
        if (appHeaderOuter == null) {
            appHeaderOuter = new Div();
            appHeaderOuter.addClassName("app-header-outer");
            getElement().insertChild(0, appHeaderOuter.getElement());
        }
        appHeaderOuter.removeAll();
        appHeaderOuter.add(components);
    }

    private void setAppHeaderInner(Component... components) {
        if (appHeaderInner == null) {
            appHeaderInner = new Div();
            appHeaderInner.addClassName("app-header-inner");
            column.getElement().insertChild(0, appHeaderInner.getElement());
        }
        appHeaderInner.removeAll();
        appHeaderInner.add(components);
    }

    private void setAppFooterInner(Component... components) {
        if (appFooterInner == null) {
            appFooterInner = new Div();
            appFooterInner.addClassName("app-footer-inner");
            column.getElement().insertChild(column.getElement().getChildCount(),
                    appFooterInner.getElement());
        }
        appFooterInner.removeAll();
        appFooterInner.add(components);
    }

    private void setAppFooterOuter(Component... components) {
        if (appFooterOuter == null) {
            appFooterOuter = new Div();
            appFooterOuter.addClassName("app-footer-outer");
            getElement().insertChild(getElement().getChildCount(),
                    appFooterOuter.getElement());
        }
        appFooterOuter.removeAll();
        appFooterOuter.add(components);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        this.viewContainer.getElement().appendChild(content.getElement());
    }

    public NaviDrawer getNaviDrawer() {
        return naviDrawer;
    }

    public AppBar getAppBar() {
        return appBar;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (navigationTabs) {
            afterNavigationWithTabs(event);
        } else {
            afterNavigationWithoutTabs(event);
        }
    }

    private void afterNavigationWithTabs(AfterNavigationEvent e) {
        NaviItem active = getActiveItem(e);
        if (active == null) {
            if (tabBar.getTabCount() == 0) {
                tabBar.addClosableTab("", defaultNavigationTarget());
            }
        } else {
            if (tabBar.getTabCount() > 0) {
                tabBar.updateSelectedTab(active.getText(),
                        active.getNavigationTarget());
            } else {
                tabBar.addClosableTab(active.getText(),
                        active.getNavigationTarget());
            }
        }
    }

    private NaviItem getActiveItem(AfterNavigationEvent e) {
        for (NaviItem item : naviDrawer.getMenu().getNaviItems()) {
            if (item.isHighlighted(e)) {
                return item;
            }
        }
        return null;
    }

    private void afterNavigationWithoutTabs(AfterNavigationEvent e) {
        NaviItem active = getActiveItem(e);
        if (active != null) {
            getAppBar().setTitle(active.getText());
        }
    }

}
