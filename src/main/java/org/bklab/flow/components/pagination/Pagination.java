package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import org.bklab.flow.layout.ToolBar;

public class Pagination extends ToolBar {
    private PaginationTotalLabel totalLabel = new PaginationTotalLabel();
    private Section content = new Section();
    private ComboBox<Integer> pageSize = new ComboBox<>();
    private Div jump = new Div();


}
