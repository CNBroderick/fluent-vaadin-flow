/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.IconSize
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util;

public enum IconSize {

    S("size-s"),
    M("size-m"),
    L("size-l");

    private final String style;

    IconSize(String style) {
        this.style = style;
    }

    public String getClassName() {
        return style;
    }

}
