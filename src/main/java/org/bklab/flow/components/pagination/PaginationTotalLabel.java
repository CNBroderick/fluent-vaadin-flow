package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;

import java.util.function.Function;

@CssImport("./styles/components/pagination/total-label.css")
public class PaginationTotalLabel extends Span {

    private Function<Integer, String> labelGenerator = total -> total <= 0 ? "无数据" : "共 " + total + " 条";
    private int total = 0;


    public PaginationTotalLabel() {
        super("无数据");
        addClassName("pagination-total-label");
    }

    public PaginationTotalLabel setLabelGenerator(Function<Integer, String> labelGenerator) {
        if (labelGenerator != null) this.labelGenerator = labelGenerator;
        update();
        return this;
    }

    public PaginationTotalLabel setLabelGenerator(String pattern) {
        if (pattern != null) this.labelGenerator = number -> String.format(pattern, number);
        update();
        return this;
    }

    public PaginationTotalLabel setTotal(int total) {
        this.total = Math.max(0, total);
        update();
        return this;
    }

    private void update() {
        setText(labelGenerator.apply(total));
    }
}
