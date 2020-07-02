/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.TextAlign
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum TextAlign {

    CENTER("center"),
    JUSTIFY("justify"),
    LEFT("left"),
    RIGHT("right");

    private final String value;

    TextAlign(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
