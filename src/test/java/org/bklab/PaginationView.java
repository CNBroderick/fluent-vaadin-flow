package org.bklab;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.layout.select.DragSelectLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        View.getCurrent().ifPresent(v -> v.getAppBar().setContextIconAsBack(getClass()));

        add(new Pagination().totalData(1000).limit(10).init().build());

        add(new Pagination().totalData(1000).limit(10).tinyMode().init().build());


        Span selectArea = new Span("已选择：0㎡");

        Function<Room, Div> roomDivFunction = room -> new DivFactory(
                new DivFactory().text(room.roomNo).margin(0).textAlign("start").fontSize("var(--lumo-font-size-l)").get(),
                new DivFactory().text(String.format("%.2f ㎡", room.area)).margin(0).textAlign("center").fontSize("var(--lumo-font-size-s)").get()
        ).padding("0.2em").border().margin("0.1em").get();


        DragSelectLayout<Room> dragSelectLayout = new DragSelectLayout<Room>()
                .whenEmpty("请拖动左侧房间到此处").componentRender(roomDivFunction)
                .items(IntStream.range(101, 129).mapToObj(Room::new).collect(Collectors.toSet()));
        dragSelectLayout.containerHeight("400px");
        dragSelectLayout.getSelectedToolBar().right(selectArea);
        dragSelectLayout.addValueChangeListener(e -> {
            String areas = String.format("已选择：%.2f㎡", e.getValue().stream().mapToDouble(r -> r.area).sum());
            Notification.show(areas);
            selectArea.setText(areas);
        });
        dragSelectLayout.addSelectionListener(e -> {
            String areas = String.format("已选择：%.2f㎡", e.getValue().stream().mapToDouble(r -> r.area).sum());
            Notification.show(areas);
            selectArea.setText(areas);
        });

        add(dragSelectLayout);

    }


    public static class Room {

        public String roomNo;
        public double area;

        public Room(int no) {
            this.roomNo = "" + no;
            this.area = BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }

    }

}
