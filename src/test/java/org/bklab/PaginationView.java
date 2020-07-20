package org.bklab;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.components.pagination.*;

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

        add(new Pagination().totalData(1000).limit(10).init().build());

        add(new Pagination().totalData(1000).limit(10).tinyMode().init().build());

    }
}
