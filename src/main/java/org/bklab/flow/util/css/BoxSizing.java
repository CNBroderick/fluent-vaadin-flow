/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.BoxSizing
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum BoxSizing {

    BORDER_BOX("border-box"), CONTENT_BOX("content-box");

    private final String value;

    BoxSizing(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
