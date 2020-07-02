/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.lumo.BadgeShape
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css.lumo;

public enum BadgeShape {

    NORMAL("normal"), PILL("pill");

    private final String style;

    BadgeShape(String style) {
        this.style = style;
    }

    public String getThemeName() {
        return style;
    }

}
