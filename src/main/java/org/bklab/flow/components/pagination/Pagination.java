package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.layout.ToolBar;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pagination extends ToolBar {
    private final PaginationTotalLabel totalLabel = new PaginationTotalLabel();
    private final PaginationPageSize pageSize = new PaginationPageSize();
    private final PaginationJumpField jumpField = new PaginationJumpField();
    private final PaginationButtonContainer pagesContainer = new PaginationButtonContainer();
    private final GeneratePagesContainer generatePagesContainer = new GeneratePagesContainer();

    private PageSwitchEvent pageSwitchEvent;
    private CustomPaginationLayout customPaginationLayout;
    private Consumer<PaginationButton> paginationButtonConsumer;
    private boolean tinyMode = false;
    private boolean compactMode = false;
    private int total = 1;
    private int totalData;
    private int limit = 5;
    private int onePageSize = 20;
    private int currentPage = 1;
    private boolean hasInit = false;

    public Pagination() {

    }

    /**
     * 无边框模式
     *
     * @param tinyMode true 无边框 false 有边框
     * @return this
     */
    public Pagination tinyMode(boolean tinyMode) {
        this.tinyMode = tinyMode;
        return this;
    }

    /**
     * 无边框模式
     *
     * @return this
     */
    public Pagination tinyMode() {
        this.tinyMode = true;
        return this;
    }

    /**
     * 紧凑模式 默认false
     *
     * @param compactMode true 全部居左，
     *                    false 左：total 中：页码+跳转 右：单页数量
     * @return this
     */
    public Pagination compactMode(boolean compactMode) {
        this.compactMode = compactMode;
        return this;
    }

    /**
     * 自定义翻页组件布局
     *
     * @param customPaginationLayout CustomPaginationLayout
     * @see org.bklab.flow.components.pagination.CustomPaginationLayout
     * @return this
     */
    public Pagination customLayout(CustomPaginationLayout customPaginationLayout) {
        if (this.customPaginationLayout != null) throw new RuntimeException("禁止重复调用");
        customPaginationLayout.apply(this, totalLabel, pagesContainer, jumpField, pageSize);
        this.customPaginationLayout = customPaginationLayout;
        return this;
    }

    /**
     * @param total 全部数量 默认0
     * @return this;
     */
    public Pagination totalData(int total) {
        this.totalLabel.setTotal(total);
        this.totalData = total;
        return this;
    }

    /**
     * @param limit 最大拥有翻页按钮数量，默认5，例如选择 5：
     *              精简模式：＜ 1 2 3 4 5 ＞
     *              普通模式：＜ 1 ＜＜ 5 6 7 8 9 ＞＞ 100 ＞
     * @return this;
     */
    public Pagination limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * @param onePageSize 单页数量，推荐 10 20 50 100 默认20
     * @return this;
     */
    public Pagination onePageSize(int onePageSize) {
        this.onePageSize = onePageSize;
        return this;
    }

    public Pagination onePageSize(int defaultOnePageSize, int... pageSizes) {
        List<Integer> p = Arrays.stream(pageSizes).boxed()
                .collect(Collectors.toCollection(() -> new ArrayList<>(defaultOnePageSize)));
        this.pageSize.setItems(p.stream().distinct().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList()));
        this.pageSize.setValue(defaultOnePageSize);
        return this;
    }

    /**
     * @param currentPage 当前页
     * @return this;
     */
    public Pagination setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Pagination peekTotalLabel(Consumer<PaginationTotalLabel> totalLabelConsumer) {
        totalLabelConsumer.accept(totalLabel);
        return this;
    }

    public Pagination peekPageSize(Consumer<PaginationPageSize> pageSizeConsumer) {
        pageSizeConsumer.accept(pageSize);
        return this;
    }

    public Pagination peekJumpField(Consumer<PaginationJumpField> jumpFieldConsumer) {
        jumpFieldConsumer.accept(jumpField);
        return this;
    }

    /**
     * 初始化组件
     *
     * @return this;
     */
    public Pagination init() {
        if (hasInit) return this;
        if (tinyMode) initTiny();
        else initNormal();
        if (customPaginationLayout == null) {
            left(totalLabel);
            middle(pagesContainer, jumpField);
            right(pageSize);
        }

        pageSize.addValueChangeListener(e -> build());
        jumpField.addValueChangeConsumer(generatePagesContainer::go);
        this.hasInit = true;
        return this;
    }

    private void initTiny() {
        pageSize.tinySize();
        paginationButtonConsumer = p -> p.border(false);
        jumpField.tinySize();
    }

    private void initNormal() {
        pageSize.normalSize();
        paginationButtonConsumer = p -> p.border(true);
        jumpField.normalSize();
    }

    public Pagination build() {
        if (!hasInit) init();
        this.total = (int) Math.ceil(1d * totalData / pageSize.getValue());
        if (this.currentPage > total) this.currentPage = 1;
        jumpField.getNumberField().setMax(total);
        jumpField.getNumberField().setErrorMessage("请确认页码为 1 - " + total + " 之间的正整数");
        generatePagesContainer.bind();
        return this;
    }

    private class GeneratePagesContainer {
        private final Map<Integer, PaginationButton> pageMap = new LinkedHashMap<>();
        private final List<PaginationButton> allButtons = new ArrayList<>();
        private PaginationButton prev;
        private PaginationButton prevJump;
        private PaginationButton nextJump;
        private PaginationButton next;
        private int lastPage = currentPage;

        public void bind() {
            pagesContainer.setLimit(limit);
            pageMap.clear();
            allButtons.clear();

            prev = apply(new PaginationButton(false, e -> goPrev(e.isFromClient())));
            next = apply(new PaginationButton(true, e -> goNext(e.isFromClient())));
            prevJump = apply(new PaginationButton(e -> goPrevJump(e.isFromClient()), false)).setDescription("向前 " + (limit / 2) + "页");
            nextJump = apply(new PaginationButton(e -> goNextJump(e.isFromClient()), true)).setDescription("向后 " + (limit / 2) + "页");

            IntStream.rangeClosed(1, total).forEachOrdered(current ->
                    pageMap.put(current, apply(new PaginationButton(current, e -> go(e.isFromClient(), current)))));

            addToContainer();
            checkAbilities(false);
            go(false, currentPage);
        }

        private void addToContainer() {
            pagesContainer.setPrev(prev).setFirstPage(pageMap.get(1)).setPrevJump(prevJump)
                    .setNextJump(nextJump).setLastPage(pageMap.get(total)).setNext(next).init();
            int start = (int) (currentPage - (limit / 2.0d));
            if (start < 0) {
                start = 0;
            }
            for (int i = 1; i <= limit && i < total; i++) {
                Optional.ofNullable(pageMap.get(i + start)).ifPresent(pagesContainer::addLast);
            }
        }

        private PaginationButton apply(PaginationButton button) {
            paginationButtonConsumer.accept(button);
            allButtons.add(button);
            return button;
        }

        private void checkAbilities(boolean isFromClient) {
            prev.enable(currentPage > 1);
            next.enable(currentPage < total);
            prevJump.setVisible(pagesContainer.getContainerFirst() > 2);
            nextJump.setVisible(pagesContainer.getContainerLast() < total - 1);
        }

        private void go(boolean isFromClient, int i) {
            if(i == lastPage) return;

            deselectAll();
            lastPage = currentPage;
            currentPage = i;

            int a = currentPage - (limit / 2);
            if (a + limit > total) a = total - limit;
            if (a < limit / 2) a = 2;

            pagesContainer.clearContainer();
            IntStream.range(a, Math.min(a + limit, total)).boxed()
                    .map(pageMap::get).forEachOrdered(pagesContainer::addLast);

            select(isFromClient);
            checkAbilities(isFromClient);
        }

        private void goPrev(boolean isFromClient) {
            deselectAll();
            lastPage = currentPage;
            currentPage = Math.max(1, --currentPage);
            select(isFromClient);
            Optional.ofNullable(pageMap.get(pagesContainer.getContainerFirst() - 1)).ifPresent(pagesContainer::addFirst);
            checkAbilities(isFromClient);
        }

        private void goNext(boolean isFromClient) {
            deselectAll();
            lastPage = currentPage;
            currentPage = Math.min(total, ++currentPage);
            select(isFromClient);
            Optional.ofNullable(pageMap.get(pagesContainer.getContainerLast() + 1)).ifPresent(pagesContainer::addLast);
            checkAbilities(isFromClient);
        }

        private void goPrevJump(boolean isFromClient) {
            go(isFromClient, Math.max(1, currentPage -= limit));
        }

        private void goNextJump(boolean isFromClient) {
            go(isFromClient, Math.min(total, currentPage += limit));
        }

        private void select(boolean isFromClient) {
            Optional.ofNullable(pageMap.get(currentPage)).ifPresent(PaginationButton::select);
            if (pageSwitchEvent != null)
                pageSwitchEvent.accept(currentPage, pageSize.getValue(), lastPage, isFromClient);
        }

        private void deselectAll() {
            allButtons.forEach(PaginationButton::deselect);
        }
    }
}
