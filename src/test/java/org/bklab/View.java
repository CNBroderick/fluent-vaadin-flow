/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 17:19:45
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.View
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.components.navigation.drawer.NaviMenu;
import org.bklab.flow.layout.app.MainAppLayout;

@Route("")
@PageTitle("布克约森实验室")
public class View extends MainAppLayout {

    public View() {
        appBar.lightTheme();
        appBar.logout(e -> UI.getCurrent().navigate(LoginView.class)).avatarName("Broderick Labs");
        naviDrawer.disableSwap();
        appBar.getContextIcon().setVisible(true);
        setWidthFull();
    }

    @Override
    public void buildNaviMenu(NaviMenu naviMenu) {
        naviMenu.addNaviItem(VaadinIcon.VAADIN_H, "组件库", ComponentDemoView.class);
        naviMenu.addNaviItem(VaadinIcon.DATABASE, "暂无数据", EmptyView.class);
        naviMenu.addNaviItem(VaadinIcon.PACKAGE, "翻页工具", PaginationView.class);
        naviMenu.addNaviItem(VaadinIcon.PACKAGE, "颜色管理", CrudView.class);
        naviMenu.addNaviItem(VaadinIcon.PACKAGE, "动态表单", FluentGenerateFormLayoutView.class);
    }

    @Override
    public void buildUserIcon(Image image, ContextMenu contextMenu) {
        contextMenu.addItem("登录", e -> UI.getCurrent().navigate(LoginView.class));
    }

    @Override
    protected Class<? extends Component> defaultNavigationTarget() {
        return ComponentDemoView.class;
    }
}
