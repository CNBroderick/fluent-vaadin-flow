/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.lumo.BadgeColor
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css.lumo;

public enum BadgeColor {

    NORMAL("badge"), NORMAL_PRIMARY("badge primary"), SUCCESS(
            "badge success"), SUCCESS_PRIMARY("badge success primary"), ERROR(
            "badge error"), ERROR_PRIMARY(
            "badge error primary"), CONTRAST(
            "badge contrast"), CONTRAST_PRIMARY(
            "badge contrast primary");

    private final String style;

    BadgeColor(String style) {
        this.style = style;
    }

    public String getThemeName() {
        return style;
    }

}
