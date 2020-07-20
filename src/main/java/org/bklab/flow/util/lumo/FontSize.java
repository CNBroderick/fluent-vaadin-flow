/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.FontSize
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.lumo;

public enum FontSize {

    XXS("var(--lumo-font-size-xxs)"),
    XS("var(--lumo-font-size-xs)"),
    S("var(--lumo-font-size-s)"),
    M("var(--lumo-font-size-m)"),
    L("var(--lumo-font-size-l)"),
    XL("var(--lumo-font-size-xl)"),
    XXL("var(--lumo-font-size-xxl)"),
    XXXL("var(--lumo-font-size-xxxl)");

    private final String value;

    FontSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
