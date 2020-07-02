/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.size.Size
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.layout.size;

public interface Size {

    // Margins and paddings can have multiple attributes (e.g. horizontal and
    // vertical)
    String[] getMarginAttributes();

    String[] getPaddingAttributes();

    // Spacing is applied via the class attribute
    String getSpacingClassName();

    // Returns the size variable (Lumo custom property)
    String getVariable();

}
