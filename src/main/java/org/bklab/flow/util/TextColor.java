/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.TextColor
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util;

public enum TextColor {

    HEADER("var(--lumo-header-text-color)"),
    BODY("var(--lumo-body-text-color)"),
    SECONDARY("var(--lumo-secondary-text-color)"),
    TERTIARY("var(--lumo-tertiary-text-color)"),
    DISABLED("var(--lumo-disabled-text-color)"),
    PRIMARY("var(--lumo-primary-text-color)"),
    PRIMARY_CONTRAST("var(--lumo-primary-contrast-color)"),
    ERROR("var(--lumo-error-text-color)"),
    ERROR_CONTRAST("var(--lumo-error-contrast-color)"),
    SUCCESS("var(--lumo-success-text-color)"),
    SUCCESS_CONTRAST("var(--lumo-success-contrast-color)");

    private final String value;

    TextColor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
