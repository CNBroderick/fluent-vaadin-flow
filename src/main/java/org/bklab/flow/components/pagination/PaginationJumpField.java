package org.bklab.flow.components.pagination;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.factory.NumberFieldFactory;
import org.bklab.flow.factory.SpanFactory;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@CssImport("./styles/components/pagination/pagination-jump-field.css")
@CssImport(value = "./styles/components/pagination/pagination-jump-field.css", themeFor = "vaadin-text-field")
public class PaginationJumpField extends Span {

    private final static String CLASS_NAME = "pagination-jump-field";
    private final Span prefix = new SpanFactory("跳至").className(CLASS_NAME + "__jump-prefix").get();
    private final Span suffix = new SpanFactory("页").className(CLASS_NAME + "__jump-suffix").get();

    private final NumberField numberField = new NumberFieldFactory()
            .minWidth("4em").maxWidth("5em").step(1.0).min(1).errorMessage("请输入整数页码")
            .backgroundColor("white").fontSizeS().lumoAlignCenter().lumoSmall()
            .className(CLASS_NAME + "__jump-text").get();

    public PaginationJumpField() {
        add(prefix, numberField, suffix);
        numberField.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        numberField.addThemeName("pagination");
        addClassName(CLASS_NAME);
    }

    public PaginationJumpField tinySize() {
        addClassName(CLASS_NAME + "__tiny");
        numberField.setHeight("22px");
        numberField.getStyle().set("line-height", "24px");
        return this;
    }

    public PaginationJumpField normalSize() {
        addClassName(CLASS_NAME + "__normal");
        numberField.setHeight("30px");
        numberField.getStyle().set("line-height", "32px");
        return this;
    }

    public NumberField getNumberField() {
        return numberField;
    }

    public PaginationJumpField setMax(int total) {
        numberField.setMax(total);
        numberField.setErrorMessage("请确认页码为 1 - " + total + " 之间的正整数");
        numberField.setMaxWidth(String.valueOf(total).length() + 2 + "em");
        return this;
    }

    public PaginationJumpField addValueChangeConsumer(BiConsumer<Boolean, Integer> consumer) {
        Consumer<ComponentEvent<?>> componentEventConsumer = e ->
                Optional.ofNullable(numberField.getValue()).map(Double::intValue)
                        .ifPresent(a -> consumer.accept(e.isFromClient(), a));
        numberField.addValueChangeListener(componentEventConsumer::accept);
        numberField.addKeyUpListener(Key.ENTER, componentEventConsumer::accept);
        return this;
    }


}
