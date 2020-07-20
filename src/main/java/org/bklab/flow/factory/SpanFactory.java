package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class SpanFactory extends FlowFactory<Span, SpanFactory> implements
        HtmlContainerFactory<Span, SpanFactory>, ClickNotifierFactory<Span, SpanFactory> {

    public SpanFactory() {
        this(new Span());
    }

    public SpanFactory(Component... component) {
        this(new Span(component));
    }

    public SpanFactory(String text) {
        this(new Span(text));
    }

    public SpanFactory(Span component) {
        super(component);
    }
}
