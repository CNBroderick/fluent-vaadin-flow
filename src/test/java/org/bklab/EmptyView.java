package org.bklab;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.layout.EmptyLayout;

@Route(value = "empty", layout = View.class)
@PageTitle("工具栏&对话框 --Broderick Labs")
public class EmptyView extends Div {

    public EmptyView() {
        add(new EmptyLayout("暂无数据"));
        setSizeFull();
    }
}
