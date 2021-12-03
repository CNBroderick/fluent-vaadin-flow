/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 17:01:51
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.components.badge.BadgeTag
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.badge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.factory.SpanFactory;

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

    public BadgeTag(Component component, BadgeTagStyle style) {
        super(component);
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

    public BadgeTag tooltip(String text) {
        Tooltips.getCurrent().setTooltip(this, text);
        return this;
    }

    public SpanFactory asFactory() {
        return new SpanFactory(this);
    }
}
