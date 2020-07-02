/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.TextOverflow
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum TextOverflow {

    CLIP("clip"),
    ELLIPSIS("ellipsis");

    private final String value;

    TextOverflow(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
