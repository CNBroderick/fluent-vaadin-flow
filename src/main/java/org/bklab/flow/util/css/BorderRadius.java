/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.BorderRadius
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum BorderRadius {

    S("var(--lumo-border-radius-s)"), M("var(--lumo-border-radius-m)"), L(
            "var(--lumo-border-radius-l)"),

    _50("50%");

    private final String value;

    BorderRadius(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
