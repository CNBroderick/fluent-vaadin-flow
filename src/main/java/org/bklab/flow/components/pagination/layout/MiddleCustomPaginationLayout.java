package org.bklab.flow.components.pagination.layout;

import com.vaadin.flow.component.html.Div;
import org.bklab.flow.components.pagination.ICustomPaginationLayout;
import org.bklab.flow.components.pagination.PaginationJumpField;
import org.bklab.flow.components.pagination.PaginationPageSize;
import org.bklab.flow.components.pagination.PaginationTotalLabel;
import org.bklab.flow.layout.ToolBar;

public class MiddleCustomPaginationLayout implements ICustomPaginationLayout {
    @Override
    public void apply(ToolBar toolBar, PaginationTotalLabel totalLabel, Div pageContainer, PaginationJumpField jumpField, PaginationPageSize pageSize) {
        toolBar.middle(totalLabel, pageContainer, pageSize);
    }
}
