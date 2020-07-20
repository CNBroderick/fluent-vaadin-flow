package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.html.Div;
import org.bklab.flow.layout.ToolBar;

public interface CustomPaginationLayout {

    /**
     * 自定义翻页组件布局
     * @param toolBar       tool bar 左中右布局
     * @param totalLabel    全部数据文本
     * @param pageContainer 翻页按钮
     * @param jumpField     跳转输入框
     * @param pageSize      单页数量选择框
     */
    void apply(ToolBar toolBar,
               PaginationTotalLabel totalLabel,
               Div pageContainer,
               PaginationJumpField jumpField,
               PaginationPageSize pageSize
    );
}
