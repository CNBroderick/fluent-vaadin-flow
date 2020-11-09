/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:36
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.tab.NaviTabs
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.tab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.bklab.flow.util.lumo.UIUtils;
import org.bklab.flow.util.css.Overflow;

import java.util.function.Consumer;

/**
 * NaviTabs supports tabs that can be closed, and that can navigate to a
 * specific target when clicked.
 */
public class NaviTabs extends Tabs {

	private final ComponentEventListener<SelectedChangeEvent> listener = (ComponentEventListener<SelectedChangeEvent>) selectedChangeEvent -> navigateToSelectedTab();

	public NaviTabs() {
		addSelectedChangeListener(listener);
		getElement().setAttribute("overflow", "end");
		UIUtils.setOverflow(Overflow.HIDDEN, this);
	}

	/**
	 * When adding the first tab, the selection change event is triggered. This
	 * will cause the app to navigate to that tab's navigation target (if any).
	 * This constructor allows you to add the tabs before the event listener is
	 * set.
	 * @param naviTabs tabs
	 */
	public NaviTabs(NaviTab... naviTabs) {
		this();
		add(naviTabs);
	}

	/**
	 * Creates a regular tab without any click listeners.
	 * @param text text
	 * @return tab
	 */
	public Tab addTab(String text) {
		Tab tab = new Tab(text);
		add(tab);
		return tab;
	}

	/**
	 * Creates a tab that when clicked navigates to the specified target.
	 *
	 * @param text text
	 * @param navigationTarget Class? extends Component navigationTarget
	 * @return tab
	 */
	public Tab addTab(String text,
					  Class<? extends Component> navigationTarget) {
		Tab tab = new NaviTab(text, navigationTarget);
		add(tab);
		return tab;
	}

	/**
	 * Creates a (closable) tab that when clicked navigates to the specified
	 * target.
	 * @param text text
	 * @param navigationTarget  Class? extends Component navigationTarget
	 * @return tab
	 */
	public Tab addClosableTab(String text,
							  Class<? extends Component> navigationTarget) {
		ClosableNaviTab tab = new ClosableNaviTab(text, navigationTarget);
		add(tab);

		tab.getCloseButton().addClickListener(event -> {
			remove(tab);
			navigateToSelectedTab();
		});

		return tab;
	}

	/**
	 * Navigates to the selected tab's navigation target if available.
	 */
	public void navigateToSelectedTab() {
		navigateToSelectedTab(null);
	}

	public void navigateToSelectedTab(Consumer<UI> ifException) {
		if (getSelectedTab() instanceof NaviTab) {
			try {
				UI.getCurrent().navigate(
						((NaviTab) getSelectedTab()).getNavigationTarget());
			} catch (Exception e) {
				if (getTabCount() > 0) {
					setSelectedIndex(getTabCount() - 1);
				} else if (ifException != null) {
					ifException.accept(UI.getCurrent());
				} else {
					UI.getCurrent().navigate("");
				}
			}
		}
	}

	/*
	 * Updates the current tab's name and navigation target.
	 */
	public void updateSelectedTab(String text,
								  Class<? extends Component> navigationTarget) {
		Tab tab = getSelectedTab();
		tab.setLabel(text);

		if (tab instanceof NaviTab) {
			((NaviTab) tab).setNavigationTarget(navigationTarget);
		}

		if (tab instanceof ClosableNaviTab) {
			tab.add(((ClosableNaviTab) tab).getCloseButton());
		}

		navigateToSelectedTab();
	}

	/*
	 * Returns the number of tabs.
	 */
	public int getTabCount() {
		return Math.toIntExact(getChildren()
				.filter(component -> component instanceof Tab).count());
	}

}
