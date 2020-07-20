package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayDeque;
import java.util.Deque;

public class PaginationButtonContainer extends Div {

    private final static String CLASS_NAME = "pagination-button-container";
    private final Div container = new Div();
    private final Deque<PaginationButton> deque = new ArrayDeque<>();
    private PaginationButton prev;
    private PaginationButton firstPage;
    private PaginationButton prevJump;
    private int limit;
    private PaginationButton nextJump;
    private PaginationButton lastPage;
    private PaginationButton next;

    public PaginationButtonContainer() {
        getStyle().set("display", "flex").set("align-items", "flex-end");
    }


    public PaginationButtonContainer clear() {
        removeAll();
        prev = null;
        firstPage = null;
        prevJump = null;
        limit = 3;
        nextJump = null;
        lastPage = null;
        next = null;
        return this;
    }

    public PaginationButtonContainer clearContainer() {
        container.removeAll();
        deque.clear();
        return this;
    }

    public PaginationButtonContainer setPrev(PaginationButton prev) {
        this.prev = prev;
        return this;
    }

    public PaginationButtonContainer setFirstPage(PaginationButton firstPage) {
        this.firstPage = firstPage;
        return this;
    }

    public PaginationButtonContainer setPrevJump(PaginationButton prevJump) {
        this.prevJump = prevJump;
        return this;
    }

    public PaginationButtonContainer setNextJump(PaginationButton nextJump) {
        this.nextJump = nextJump;
        return this;
    }

    public PaginationButtonContainer setLastPage(PaginationButton lastPage) {
        this.lastPage = lastPage;
        return this;
    }

    public PaginationButtonContainer setNext(PaginationButton next) {
        this.next = next;
        return this;
    }

    public PaginationButtonContainer setLimit(int limit) {
        this.limit = Math.max(3, limit);
        return this;
    }

    public PaginationButtonContainer init() {
        removeAll();
        add(prev, firstPage, prevJump, container, nextJump, lastPage, next);
        return this;
    }

    public PaginationButtonContainer addFirst(PaginationButton button) {
        if (button == null) return this;
        if (firstPage == button) return this;
        if (lastPage == button) return this;
        if (deque.size() > limit) removeFromContainer(deque.removeLast());

        deque.push(button);
        container.addComponentAsFirst(button);

        return this;
    }

    public PaginationButtonContainer addLast(PaginationButton button) {
        if (button == null) return this;
        if (firstPage == button) return this;
        if (lastPage == button) return this;
        if (deque.size() > limit) removeFromContainer(deque.removeFirst());

        deque.addLast(button);
        container.add(button);

        return this;
    }

    public void removeFromContainer(Component... components) {
        try {
            container.remove(components);
        } catch (Exception ignore) {

        }
    }

    public int getContainerSize() {
        return deque.size();
    }

    public int getContainerFirst() {
        return deque.getFirst().getPageNo();
    }

    public int getContainerLast() {
        return deque.getLast().getPageNo();
    }
}
