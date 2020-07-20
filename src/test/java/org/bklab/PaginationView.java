package org.bklab;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.components.pagination.PaginationButton;
import org.bklab.flow.components.pagination.PaginationPageSize;
import org.bklab.flow.components.pagination.PaginationTotalLabel;

@Route(value = "pagination", layout = View.class)
@PageTitle("翻页 --Broderick Labs")
public class PaginationView extends VerticalLayout {
    public PaginationView() {
        getStyle().set("background-color", "white");
    }

    private ComponentEventListener<ClickEvent<Button>> listener() {
        return e -> {
        };
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        add(new PaginationTotalLabel().setLabelGenerator("共 %d 条数据").setTotal(12543));
        add(new HorizontalLayout(
                new PaginationTotalLabel().setLabelGenerator("共 %d 条数据").setTotal(12543),
                new PaginationButton(false, listener()).border(true),
                new PaginationButton(event -> {
                }, false).normalSize(true),
                new PaginationButton(1, listener()).border(true),
                new PaginationButton(2, listener()).border(true),
                new PaginationButton(3, listener()).border(true),
                new PaginationButton(4, listener()).border(true),
                new PaginationButton(5, listener()).border(true),
                new PaginationButton(listener(), true).normalSize(true),
                new PaginationButton(true, listener()).border(true),
                new PaginationPageSize().normalSize()
        ));

        add(new HorizontalLayout(
                new PaginationTotalLabel().setLabelGenerator("共 %d 条数据").setTotal(12543),
                new PaginationButton(false, listener()).border(false),
                new PaginationButton(listener(), false).border(false),
                new PaginationButton(1, listener()).border(false),
                new PaginationButton(2, listener()).border(false),
                new PaginationButton(3, listener()).border(false),
                new PaginationButton(4, listener()).border(false),
                new PaginationButton(5, listener()).border(false),
                new PaginationButton(listener(), true).border(false),
                new PaginationButton(true, listener()).border(false),
                new PaginationPageSize().tinySize()
        ));

        add(new HorizontalLayout(
                new PaginationTotalLabel().setLabelGenerator("共 %d 条数据").setTotal(12543),
                new PaginationButton(false, listener()).border(false).disable(),
                new PaginationButton(listener(), false).border(false),
                new PaginationButton(1, listener()).border(false),
                new PaginationButton(2, listener()).border(false),
                new PaginationButton(3, listener()).border(false).select(),
                new PaginationButton(4, listener()).border(false),
                new PaginationButton(5, listener()).border(false),
                new PaginationButton(listener(), true).border(false),
                new PaginationButton(true, listener()).border(false),
                new PaginationPageSize().tinySize()
        ));

    }
}
