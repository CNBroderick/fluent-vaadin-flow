/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 15:16:28
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.bar.TabBar
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.bar;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.theme.lumo.Lumo;
import org.bklab.flow.components.navigation.tab.NaviTabs;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.util.lumo.LumoStyles;
import org.bklab.flow.util.lumo.UIUtils;

import static org.bklab.flow.util.lumo.UIUtils.IMG_PATH;

@CssImport("./styles/components/tab-bar.css")
public class TabBar extends FlexBoxLayout {

	private final String CLASS_NAME = "tab-bar";

	private final Button menuIcon;
	private final NaviTabs tabs;
	private final Button addTab;
	private final Image avatar;

	public TabBar() {
		setClassName(CLASS_NAME);

		menuIcon = UIUtils.createTertiaryInlineButton(VaadinIcon.MENU);
		menuIcon.addClassName(CLASS_NAME + "__navi-icon");

		avatar = new Image();
		avatar.setClassName(CLASS_NAME + "__avatar");
		avatar.setSrc(IMG_PATH + "avatar.png");

		ContextMenu contextMenu = new ContextMenu(avatar);
		contextMenu.setOpenOnClick(true);
		contextMenu.addItem("Settings",
				e -> Notification.show("Not implemented yet.", 3000,
						Notification.Position.BOTTOM_CENTER));
		contextMenu.addItem("Log Out",
				e -> Notification.show("Not implemented yet.", 3000,
						Notification.Position.BOTTOM_CENTER));

		addTab = UIUtils.createSmallButton(VaadinIcon.PLUS);
		addTab.setVisible(false);
		addTab.setClassName(CLASS_NAME + "__add-tab");

		tabs = new NaviTabs();
		tabs.setClassName(CLASS_NAME + "__tabs");

		add(menuIcon, tabs, addTab, avatar);
	}


	public TabBar lightTheme() {
		UIUtils.setTheme(Lumo.LIGHT, this);
		return this;
	}

	public TabBar darkTheme() {
		UIUtils.setTheme(Lumo.DARK, this);
		return this;
	}

	public TabBar whenAddTabClicked(ComponentEventListener<ClickEvent<Button>> listener) {
		addTab.addClickListener(listener);
		addTab.setVisible(true);
		return this;
	}

	public TabBar whenMenuClicked(ComponentEventListener<ClickEvent<Button>> listener) {
		menuIcon.addClickListener(listener);
		return this;
	}

	/* === MENU ICON === */

	public Button getMenuIcon() {
		return menuIcon;
	}

	/* === TABS === */

	public void centerTabs() {
		tabs.addClassName(LumoStyles.Margin.Horizontal.AUTO);
	}

	private void configureTab(Tab tab) {
		tab.addClassName(CLASS_NAME + "__tab");
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

	public Tab addClosableTab(String text,
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

	public void addTabSelectionListener(
			ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
		tabs.addSelectedChangeListener(listener);
	}

	public int getTabCount() {
		return tabs.getTabCount();
	}

	public void removeAllTabs() {
		tabs.removeAll();
	}

	/* === ADD TAB BUTTON === */

	public void setAddTabVisible(boolean visible) {
		addTab.setVisible(visible);
	}

	public NaviTabs getTabs() {
		return tabs;
	}

	public Button getAddTab() {
		return addTab;
	}

	public Image getAvatar() {
		return avatar;
	}
}
