/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.Shadow
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum Shadow {

    XS("var(--lumo-box-shadow-xs)"), S("var(--lumo-box-shadow-s)"), M(
            "var(--lumo-box-shadow-m)"), L("var(--lumo-box-shadow-l)"), XL(
            "var(--lumo-box-shadow-xl)");

    private final String value;

    Shadow(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
