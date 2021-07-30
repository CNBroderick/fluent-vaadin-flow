/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-21 14:21:31
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.page.PagedGrid
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.layout.page;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.components.pagination.PageSwitchEvent;
import org.bklab.flow.components.pagination.Pagination;
import org.bklab.flow.components.pagination.layout.MiddleCustomPaginationLayout;
import org.bklab.flow.factory.VerticalLayoutFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PagedGrid<T, G extends Grid<T>> extends VerticalLayout {

    private final List<T> items = new ArrayList<>();
    private final G grid;
    private int onePageSize = 20;
    private final Pagination pagination = new Pagination().onePageSize(onePageSize).limit(10).customLayout(new MiddleCustomPaginationLayout());

    public PagedGrid(G grid) {
        this.grid = grid;
        add(grid, pagination);

        pagination.pageSwitchEvent(createPageSwitchEvent());
        new VerticalLayoutFactory(this)
                .alignItemsCenter()
                .defaultHorizontalComponentAlignmentBaseline()
                .margin(false).padding(false).sizeFull();
        pagination.build();
    }

    public PagedGrid<T, G> setOnePageSize(int onePageSize) {
        this.onePageSize = onePageSize;
        pagination.build().refresh();
        return this;
    }

    private PageSwitchEvent createPageSwitchEvent() {
        return (currentPageNumber, pageSize, lastPageNumber, isFromClient) -> {
            grid.setItems(
                    items.size() > onePageSize
                    ? this.items.stream().skip((long) (Math.max(Math.min(currentPageNumber,
                            this.items.size() / pageSize + 1), 1) - 1) * pageSize).limit(pageSize).collect(Collectors.toList())
                    : items
            );
            grid.recalculateColumnWidths();
        };
    }

    public List<T> getItems() {
        return items;
    }

    public PagedGrid<T, G> setItems(Collection<T> collection) {
        this.items.clear();
        this.items.addAll(collection);
        pagination.totalData(collection.size()).refresh();
        grid.recalculateColumnWidths();
        return this;
    }

    public G getGrid() {
        return grid;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
