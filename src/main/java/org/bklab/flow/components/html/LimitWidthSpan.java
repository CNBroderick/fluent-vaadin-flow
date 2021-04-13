package org.bklab.flow.components.html;

import com.vaadin.flow.component.html.Span;
import org.bklab.flow.factory.SpanFactory;

public class LimitWidthSpan extends Span {

    public LimitWidthSpan(String text, String maxWidth) {
        super(text);
        getStyle()
                .set("width", maxWidth)
                .set("text-overflow", "ellipsis")
                .set("display", "block")
                .set("white-space", "nowrap")
                .set("overflow", "hidden");
    }
}