/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.css.AlignItems
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.css;

public enum AlignItems {

    BASELINE("baseline"),
    CENTER("center"),
    END("end"),
    FLEX_END("flex-end"),
    FLEX_START("flex-start"),
    LEFT("left"),
    NORMAL("normal"),
    RIGHT("right"),
    SELF_END("self-end"),
    SELF_START("self-start"),
    START("start"),
    STRETCH("stretch");

    private final String value;

    AlignItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
