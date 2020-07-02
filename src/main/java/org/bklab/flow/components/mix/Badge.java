/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.mix.Badge
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.mix;

import com.vaadin.flow.component.html.Span;
import org.bklab.flow.util.UIUtils;
import org.bklab.flow.util.css.lumo.BadgeColor;
import org.bklab.flow.util.css.lumo.BadgeShape;
import org.bklab.flow.util.css.lumo.BadgeSize;

import java.util.StringJoiner;

import static org.bklab.flow.util.css.lumo.BadgeShape.PILL;

public class Badge extends Span {

    public Badge(String text) {
        this(text, BadgeColor.NORMAL);
    }

    public Badge(String text, BadgeColor color) {
        super(text);
        UIUtils.setTheme(color.getThemeName(), this);
    }

    public Badge(String text, BadgeColor color, BadgeSize size, BadgeShape shape) {
        super(text);
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(color.getThemeName());
        if (shape.equals(PILL)) {
            joiner.add(shape.getThemeName());
        }
        if (size.equals(BadgeSize.S)) {
            joiner.add(size.getThemeName());
        }
        UIUtils.setTheme(joiner.toString(), this);
    }

}
