/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:40
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.drawer.NaviDrawer
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.drawer;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import elemental.json.JsonObject;
import org.bklab.flow.util.lumo.UIUtils;

@CssImport("./styles/components/navi-drawer.css")
@JsModule("./swipe-away.js")
public class NaviDrawer extends Div implements AfterNavigationObserver {

	private final String CLASS_NAME = "navi-drawer";
	private final static String RAIL = "rail";
	private final String OPEN = "open";

	private Div scrim;

	private Div mainContent;
	private TextField search;

	private Button railButton;
	private final BrandExpression iconBrand;
	private Div beforeMenu;
	private NaviMenu menu;
	private Button logout;
	private final boolean enableSwap = true;

	public NaviDrawer() {
		this.iconBrand = new BrandExpression("");
		setClassName(CLASS_NAME);

		initScrim();
		initMainContent();

		initHeader();
		initSearch();

		initBeforeMenu();
		initMenu();

		initFooter();

		enableSwap(true);
	}

	public BrandExpression getIconBrand() {
		return iconBrand;
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(ui -> ui.getPage().executeJs(
				"window.addSwipeAway($0,$1,$2,$3)",
				mainContent.getElement(), this, "onSwipeAway",
				scrim.getElement()));
	}

	@ClientCallable
	public void onSwipeAway(JsonObject data) {
		close();
	}

	private void initScrim() {
		// Backdrop on small viewports
		scrim = new Div();
		scrim.addClassName(CLASS_NAME + "__scrim");
		scrim.addClickListener(event -> close());
		add(scrim);
	}

	private void initMainContent() {
		mainContent = new Div();
		mainContent.addClassName(CLASS_NAME + "__content");
		add(mainContent);
	}

	private void initHeader() {
		mainContent.add(iconBrand);
	}

	private void initSearch() {
		search = new TextField();
		search.addValueChangeListener(e -> menu.filter(search.getValue()));
		search.setClearButtonVisible(true);
		search.setPlaceholder("搜索菜单");
		search.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
		search.setVisible(false);
		mainContent.add(search);
	}

	private void initBeforeMenu() {
		beforeMenu = new Div();
		beforeMenu.addClassName(CLASS_NAME + "__before_menu");
		mainContent.add(beforeMenu);
	}

	private void initMenu() {
		menu = new NaviMenu();
		mainContent.add(menu);
	}

	private void initFooter() {
		railButton = UIUtils.createSmallButton(VaadinIcon.CHEVRON_LEFT_SMALL);
		railButton.getElement().setAttribute("title", "收起菜单");
		railButton.addClassName(CLASS_NAME + "__footer");
		railButton.addClickListener(event -> toggleRailMode());
		railButton.getElement().setAttribute("aria-label", "收起菜单");

		logout = UIUtils.createSmallButton("退出登录", VaadinIcon.SIGN_OUT);
		logout.addClassNames(CLASS_NAME + "__logout", CLASS_NAME + "__footer");
		logout.getElement().setAttribute("aria-label", "退出登录");
		logout.setVisible(false);

		mainContent.add(logout, railButton);
	}

	public Button getLogout() {
		return logout;
	}

	public void whenLogoutClick(ComponentEventListener<ClickEvent<Button>> listener) {
		logout.addClickListener(listener);
		logout.setVisible(true);
	}

	private void toggleRailMode() {
		if (getElement().hasAttribute(RAIL)) {
			toggleRailModeExpand();
		} else {
			toggleRailModeCollapse();
		}
		getElement().setAttribute(RAIL, !getElement().hasAttribute(RAIL));
	}

	private void toggleRailModeExpand() {
		railButton.setIcon(new Icon(VaadinIcon.CHEVRON_LEFT_SMALL));
		UIUtils.setAriaLabel("收起菜单", railButton);
		railButton.getElement().setAttribute("title", "收起菜单");

		logout.setIcon(new Icon(VaadinIcon.SIGN_OUT));
		UIUtils.setAriaLabel("退出登录", logout);
		iconBrand.toggle(true);
	}

	private void toggleRailModeCollapse() {
		railButton.setIcon(new Icon(VaadinIcon.CHEVRON_RIGHT_SMALL));
		UIUtils.setAriaLabel("展开菜单", railButton);
		railButton.getElement().setAttribute("title", "展开菜单");

		logout.setIcon(new Icon(VaadinIcon.SIGN_OUT));
		UIUtils.setAriaLabel("退出登录", logout);

		getUI().ifPresent(ui -> ui.getPage().executeJs(
				"var originalStyle = getComputedStyle($0).pointerEvents;" //
						+ "$0.style.pointerEvents='none';" //
						+ "setTimeout(function() {$0.style.pointerEvents=originalStyle;}, 170);",
				getElement()));
		iconBrand.toggle(false);
	}

	public void toggle() {
		if (getElement().hasAttribute(OPEN)) {
			close();
		} else {
			open();
		}
	}

	private void open() {
		getElement().setAttribute(OPEN, true);
	}

	private void close() {
		getElement().setAttribute(OPEN, false);
		applyIOS122Workaround();
	}

	private void applyIOS122Workaround() {
		// iOS 12.2 sometimes fails to animate the menu away.
		// It should be gone after 240ms
		// This will make sure it disappears even when the browser fails.
		getUI().ifPresent(ui -> ui.getPage().executeJs(
				"var originalStyle = getComputedStyle($0).transitionProperty;" //
						+ "setTimeout(function() {$0.style.transitionProperty='padding'; requestAnimationFrame(function() {$0.style.transitionProperty=originalStyle})}, 250);",
				mainContent.getElement()));
	}

	public NaviMenu getMenu() {
		return menu;
	}

	public NaviDrawer enableSwap() {
		this.enableSwap(true);
		return this;
	}

	public NaviDrawer enableSwap(boolean enable) {
		getElement().setAttribute("enable-swap", enable);
		return this;
	}

	public NaviDrawer disableSwap() {
		this.enableSwap(false);
		return this;
	}

	@Override
	public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
		close();
	}

	public NaviDrawer addBeforeMenu(Component... components) {
		beforeMenu.add(components);
		return this;
	}

	public Div getScrim() {
		return scrim;
	}

	public Div getMainContent() {
		return mainContent;
	}

	public TextField getSearch() {
		return search;
	}

	public Button getRailButton() {
		return railButton;
	}

	public Div getBeforeMenu() {
		return beforeMenu;
	}
}
