/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.JustifyContent
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum JustifyContent {

    BASELINE("baseline"),
    CENTER("center"),
    END("end"),
    FLEX_END("flex-end"),
    FLEX_START("flex-start"),
    LEFT("left"),
    RIGHT("right"),
    SPACE_AROUND("space-around"),
    SPACE_BETWEEN("space-between"),
    SPACE_EVENLY("space-evenly"),
    START("start"),
    STRETCH("stretch");

    private final String value;

    JustifyContent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
