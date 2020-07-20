/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.LineHeight
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.lumo;

public enum LineHeight {

    XS("var(--lumo-line-height-xs)"),
    S("var(--lumo-line-height-s)"),
    M("var(--lumo-line-height-m)");

    private final String value;

    LineHeight(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
