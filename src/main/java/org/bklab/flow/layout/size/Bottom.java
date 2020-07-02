/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.size.Bottom
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.layout.size;

public enum Bottom implements Size {

    AUTO("auto", null),

    XS("var(--lumo-space-xs)", "spacing-b-xs"), S("var(--lumo-space-s)",
            "spacing-b-s"), M("var(--lumo-space-m)", "spacing-b-m"), L(
            "var(--lumo-space-l)",
            "spacing-b-l"), XL("var(--lumo-space-xl)", "spacing-b-xl"),

    RESPONSIVE_M("var(--lumo-space-r-m)", null), RESPONSIVE_L(
            "var(--lumo-space-r-l)",
            null), RESPONSIVE_X("var(--lumo-space-r-x)", null);

    private final String variable;
    private final String spacingClassName;

    Bottom(String variable, String spacingClassName) {
        this.variable = variable;
        this.spacingClassName = spacingClassName;
    }

    @Override
    public String[] getMarginAttributes() {
        return new String[]{"margin-bottom"};
    }

    @Override
    public String[] getPaddingAttributes() {
        return new String[]{"padding-bottom"};
    }

    @Override
    public String getSpacingClassName() {
        return this.spacingClassName;
    }

    @Override
    public String getVariable() {
        return this.variable;
    }
}
