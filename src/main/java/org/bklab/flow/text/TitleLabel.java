/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-18 16:50:25
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.text.TitleLabel
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.text;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;

public class TitleLabel extends Span {

    {
        getStyle()
                .set("margin", "0")
                .set("color", "rgba(0,0,0,.85)")
                .set("font-weight", "500")
                .set("font-size", "16px")
                .set("line-height", "22px")
                .set("word-wrap", "break-word")
                .set("display", "flex")
        ;
    }

    public TitleLabel(String text) {
        super(text);
    }

    public TitleLabel(Component component) {
        super(component);
    }
}
