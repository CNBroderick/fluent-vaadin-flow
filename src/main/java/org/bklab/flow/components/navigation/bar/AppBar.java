/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 15:16:28
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.bar.AppBar
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.bar;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.Lumo;
import org.bklab.flow.components.navigation.tab.NaviTab;
import org.bklab.flow.components.navigation.tab.NaviTabs;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.image.ImageBase;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.util.lumo.LumoStyles;
import org.bklab.flow.util.lumo.UIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CssImport("./styles/components/app-bar.css")
public class AppBar extends Header {

	private final String CLASS_NAME = "app-bar";
	private static final Logger logger = LoggerFactory.getLogger(AppBar.class);

	private FlexBoxLayout container;

	private Button menuIcon;
	private Button contextIcon;

	private H1 title;
	private FlexBoxLayout leftActionItems;
	private FlexBoxLayout middleActionItems;
	private FlexBoxLayout actionItems;
	private Image avatar;

	private FlexBoxLayout tabContainer;
	private NaviTabs tabs;
	private ArrayList<Registration> tabSelectionListeners;
	private Button addTab;
	private final List<Registration> registrations = new ArrayList<>();
	private Span avatarName;
	private Button logout;

	private TextField search;
	private Registration searchRegistration;

	public AppBar(String title, NaviTab... tabs) {
		setClassName(CLASS_NAME);

		initMenuIcon();
		initContextIcon();
		initTitle(title);
		initSearch();
		initAvatar();
		initActionItems();
		initContainer();
		initTabs(tabs);
	}

	public AppBar(String title, Class<? extends Component> defaultNavigate, NaviTab... tabs) {
		setClassName(CLASS_NAME);

		initMenuIcon();
		initContextIcon();
		initTitle(title);
		initSearch();
		initAvatar();
		initActionItems();
		initContainer();
		initTabs(defaultNavigate, tabs);
	}

	public AppBar lightTheme() {
		UIUtils.setTheme(Lumo.LIGHT, this);
		return this;
	}

	public AppBar darkTheme() {
		UIUtils.setTheme(Lumo.DARK, this);
		return this;
	}

	public void setNaviMode(NaviMode mode) {
		if (mode.equals(NaviMode.MENU)) {
			menuIcon.setVisible(true);
			contextIcon.setVisible(false);
		} else {
			menuIcon.setVisible(false);
			contextIcon.setVisible(true);
		}
	}

	private void initMenuIcon() {
		menuIcon = UIUtils.createTertiaryInlineButton(VaadinIcon.MENU);
		menuIcon.addClassName(CLASS_NAME + "__navi-icon");

		UIUtils.setAriaLabel("Menu", menuIcon);
		UIUtils.setLineHeight("1", menuIcon);
	}

	public AppBar ifMenuIconClick(ComponentEventListener<ClickEvent<Button>> listener) {
		menuIcon.addClickListener(listener);
		return this;
	}

	private void initTitle(String title) {
		this.title = new H1(title);
		this.title.setClassName(CLASS_NAME + "__title");
	}

	private void initSearch() {
		search = new TextField();
		search.setPlaceholder("Search");
		search.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
		search.setVisible(false);
	}

	private ContextMenu userIconMenu;

	private void initContextIcon() {
		contextIcon = UIUtils
				.createTertiaryInlineButton(VaadinIcon.ARROW_LEFT);
		contextIcon.addClassNames(CLASS_NAME + "__context-icon");
		contextIcon.setVisible(false);
		UIUtils.setAriaLabel("返回", contextIcon);
		UIUtils.setLineHeight("1", contextIcon);
	}

	public ContextMenu getUserIconMenu() {
		return userIconMenu;
	}

	private void initActionItems() {
		actionItems = new FlexBoxLayout();
		actionItems.addClassName(CLASS_NAME + "__action-items");
		actionItems.setVisible(false);
		leftActionItems = new FlexBoxLayout();
		leftActionItems.addClassName(CLASS_NAME + "__left-action-items");
		leftActionItems.setVisible(false);
		middleActionItems = new FlexBoxLayout();
		middleActionItems.addClassName(CLASS_NAME + "__middle-action-items");
	}

	private void initContainer() {
		container = new FlexBoxLayout(menuIcon, contextIcon, this.title,
				leftActionItems, middleActionItems, search, actionItems, avatarName, avatar, logout);
		container.addClassName(CLASS_NAME + "__container");
		container.setAlignItems(FlexComponent.Alignment.CENTER);
		container.setFlexGrow(1, search);
		add(container);
	}

	private void initTabs(NaviTab... tabs) {
		initTabs(null, tabs);
	}

	private void initTabs(Class<? extends Component> defaultNavigate, NaviTab... tabs) {
		addTab = UIUtils.createSmallButton(VaadinIcon.PLUS);
		if (defaultNavigate != null) {
			addTab.addClickListener(e -> this.tabs.setSelectedTab(addClosableNaviTab("New Tab", defaultNavigate)));
		}
		addTab.setVisible(false);

		this.tabs = tabs.length > 0 ? new NaviTabs(tabs) : new NaviTabs();
		this.tabs.setClassName(CLASS_NAME + "__tabs");
		this.tabs.setVisible(false);
		for (NaviTab tab : tabs) {
			configureTab(tab);
		}

		this.tabSelectionListeners = new ArrayList<>();

		tabContainer = new FlexBoxLayout(this.tabs, addTab);
		tabContainer.addClassName(CLASS_NAME + "__tab-container");
		tabContainer.setAlignItems(FlexComponent.Alignment.CENTER);
		add(tabContainer);
	}

	public Button getMenuIcon() {
		return menuIcon;
	}

	/* === MENU ICON === */

	public Button getContextIcon() {
		return contextIcon;
	}

	/* === CONTEXT ICON === */

	public void setContextIcon(Icon icon) {
		contextIcon.setIcon(icon);
	}

	public Optional<String> getTitle() {
		return Optional.ofNullable(this.title.getText());
	}

	/* === TITLE === */

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public Component addActionItem(Component component) {
		actionItems.add(component);
		updateActionItemsVisibility();
		return component;
	}

	public Component addLeftActionItem(Component component) {
		leftActionItems.add(component);
		updateActionItemsVisibility();
		return component;
	}

	public Component addMiddleActionItem(Component component) {
		middleActionItems.add(component);
		updateActionItemsVisibility();
		return component;
	}

	/* === ACTION ITEMS === */

	public Button addActionItem(VaadinIcon icon) {
		Button button = UIUtils.createButton(icon, ButtonVariant.LUMO_SMALL,
				ButtonVariant.LUMO_TERTIARY);
		addActionItem(button);
		return button;
	}

	public Button addLeftActionItem(VaadinIcon icon) {
		Button button = UIUtils.createButton(icon, ButtonVariant.LUMO_SMALL,
				ButtonVariant.LUMO_TERTIARY);
		addLeftActionItem(button);
		return button;
	}

	public Button addMiddleActionItem(VaadinIcon icon) {
		Button button = UIUtils.createButton(icon, ButtonVariant.LUMO_SMALL,
				ButtonVariant.LUMO_TERTIARY);
		addMiddleActionItem(button);
		return button;
	}

	public void removeAllActionItems() {
		leftActionItems.removeAll();
		middleActionItems.removeAll();
		actionItems.removeAll();
		updateActionItemsVisibility();
	}

	public Image getAvatar() {
		return avatar;
	}

	/* === AVATAR == */

	public void centerTabs() {
		tabs.addClassName(LumoStyles.Margin.Horizontal.AUTO);
	}

	/* === TABS === */

	private void configureTab(Tab tab) {
		tab.addClassName(CLASS_NAME + "__tab");
		updateTabsVisibility();
	}

	public Tab addTab(String text) {
		Tab tab = tabs.addTab(text);
		configureTab(tab);
		return tab;
	}

	public Tab addTab(String text,
					  Class<? extends Component> navigationTarget) {
		Tab tab = tabs.addTab(text, navigationTarget);
		configureTab(tab);
		return tab;
	}

	public Tab addClosableNaviTab(String text,
								  Class<? extends Component> navigationTarget) {
		Tab tab = tabs.addClosableTab(text, navigationTarget);
		configureTab(tab);
		return tab;
	}

	public Tab getSelectedTab() {
		return tabs.getSelectedTab();
	}

	public void setSelectedTab(Tab selectedTab) {
		tabs.setSelectedTab(selectedTab);
	}

	public void updateSelectedTab(String text,
								  Class<? extends Component> navigationTarget) {
		tabs.updateSelectedTab(text, navigationTarget);
	}

	public void navigateToSelectedTab() {
		tabs.navigateToSelectedTab();
	}

	public void addTabSelectionListener(
			ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
		Registration registration = tabs.addSelectedChangeListener(listener);
		tabSelectionListeners.add(registration);
	}

	public int getTabCount() {
		return tabs.getTabCount();
	}

	public void removeAllTabs() {
		tabSelectionListeners.forEach(Registration::remove);
		tabSelectionListeners.clear();
		tabs.removeAll();
		updateTabsVisibility();
	}

	public void setAddTabVisible(boolean visible) {
		addTab.setVisible(visible);
	}

	/* === ADD TAB BUTTON === */

	public void searchModeOn() {
		menuIcon.setVisible(false);
		title.setVisible(false);
		actionItems.setVisible(false);
		tabContainer.setVisible(false);

		contextIcon.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
		contextIcon.setVisible(true);
		searchRegistration = contextIcon
				.addClickListener(e -> searchModeOff());

		search.setVisible(true);
		search.focus();
	}

	/* === SEARCH === */

	public void addSearchListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
		search.addValueChangeListener(listener);
	}

	public void setSearchPlaceholder(String placeholder) {
		search.setPlaceholder(placeholder);
	}

	private void searchModeOff() {
		menuIcon.setVisible(true);
		title.setVisible(true);
		tabContainer.setVisible(true);

		updateActionItemsVisibility();
		updateTabsVisibility();

		contextIcon.setVisible(false);
		searchRegistration.remove();

		search.clear();
		search.setVisible(false);
	}

	private void initAvatar() {
		avatar = ImageBase.getImage("default-user-icon.svg");
		avatar.setClassName(CLASS_NAME + "__avatar");
		avatar.setAlt("用户菜单");


		avatarName = new Span();
		avatarName.addClassName(CLASS_NAME + "__avatar-name");

		logout = new ButtonFactory()
				.className(CLASS_NAME + "__logout")
				.icon(VaadinIcon.SIGN_OUT).visible(false)
				.lumoLarge().lumoTertiary().attributeTitle("退出登录").get();
		logout.getStyle().set("color", "var(--lumo-contrast-60pct)");

		this.userIconMenu = new ContextMenu(avatar);
		userIconMenu.setOpenOnClick(true);
	}

	public AppBar avatarName(String name) {
		avatarName.setText(name);
		return this;
	}

	public AppBar logout(ComponentEventListener<ClickEvent<Button>> eventListener) {
		logout.addClickListener(eventListener);
		logout.setVisible(true);
		return this;
	}

	public Span getAvatarName() {
		return avatarName;
	}

	public Button getLogout() {
		return logout;
	}

	public void setContextIconAsBack(Class<? extends Component> navigationTarget) {
		contextIcon.setVisible(true);
		registrations.add(contextIcon.addClickListener(e -> UI.getCurrent().navigate(navigationTarget)));
	}

	public <T, C extends Component & HasUrlParameter<T>>
	void setContextIconAsBack(Class<? extends C> navigationTarget, T parameter) {
		contextIcon.setVisible(true);
		registrations.add(contextIcon.addClickListener(e -> UI.getCurrent().navigate(navigationTarget, parameter)));
	}

	public void reset() {
		title.setText("");
		setNaviMode(NaviMode.MENU);
		removeAllActionItems();
		removeAllTabs();
		registrations.forEach(Registration::remove);
	}

	/* === RESET === */

	private void updateActionItemsVisibility() {
		actionItems.setVisible(actionItems.getComponentCount() > 0);
		leftActionItems.setVisible(actionItems.getComponentCount() > 0);
	}

	/* === UPDATE VISIBILITY === */

	private void updateTabsVisibility() {
		tabs.setVisible(tabs.getComponentCount() > 0);
	}

	public FlexBoxLayout getContainer() {
		return container;
	}

	public enum NaviMode {
		MENU, CONTEXTUAL
	}
}
