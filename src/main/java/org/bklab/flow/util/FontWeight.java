/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.FontWeight
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util;

public enum FontWeight {

    LIGHTER("lighter"),
    NORMAL("normal"),
    BOLD("bold"),
    BOLDER("bolder"),
    _100("100"),
    _200("200"),
    _300("300"),
    _400("400"),
    _500("500"),
    _600("600"),
    _700("700"),
    _800("800"),
    _900("900");

    private final String value;

    FontWeight(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

