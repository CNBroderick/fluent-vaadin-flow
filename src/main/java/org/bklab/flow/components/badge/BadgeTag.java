package org.bklab.flow.components.badge;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;

@CssImport("./styles/components/badge/badge-tag.css")
public class BadgeTag extends Span {

    {
        addClassName("badge-tag");
    }

    public BadgeTag(String text) {
        super(text);
    }

    public BadgeTag(String text, BadgeTagStyle style) {
        super(text);
        addClassName(style.style);
    }

    public BadgeTag(String text, String background) {
        super(text);
        getStyle().set("background-color", background).set("color", "white");
    }

    public BadgeTag(String text, String backgroundColor, String borderColor, String color) {
        super(text);
        getStyle()
                .set("background-color", backgroundColor)
                .set("border", "1px solid " + borderColor)
                .set("color", color);
    }
}
