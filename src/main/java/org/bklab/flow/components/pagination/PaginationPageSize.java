package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;

import java.util.Arrays;
import java.util.stream.Collectors;

@CssImport("./styles/components/pagination/pagination-page-size.css")
@CssImport(value = "./styles/components/pagination/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class PaginationPageSize extends ComboBox<Integer> {

    private static final String CLASS_NAME = "pagination-page-size";

    public PaginationPageSize() {
        items(10, 20, 50, 100);
        labelGenerator("%d条/页");
        addClassName(CLASS_NAME);
        setAllowCustomValue(true);
    }

    public PaginationPageSize tinySize() {
        addClassName(CLASS_NAME + "__tiny");
        removeClassName(CLASS_NAME + "__normal");
        return this;
    }

    public PaginationPageSize normalSize() {
        removeClassName(CLASS_NAME + "__tiny");
        addClassName(CLASS_NAME + "__normal");
        return this;
    }

    public PaginationPageSize labelGenerator(String patten) {
        setItemLabelGenerator(s -> String.format(patten, s));
        return this;
    }

    public PaginationPageSize labelGenerator(ItemLabelGenerator<Integer> itemLabelGenerator) {
        setItemLabelGenerator(itemLabelGenerator);
        return this;
    }

    public PaginationPageSize items(int... sizes) {
        if (sizes.length < 1) {
            setItems();
            clear();
            return this;
        }

        setItems(Arrays.stream(sizes).boxed().collect(Collectors.toList()));
        this.setValue(sizes[0]);
        return this;
    }
}
