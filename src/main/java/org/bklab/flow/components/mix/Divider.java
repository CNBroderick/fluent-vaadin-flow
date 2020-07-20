/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.mix.Divider
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.mix;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.util.lumo.LumoStyles;
import org.bklab.flow.util.lumo.UIUtils;

public class Divider extends FlexBoxLayout implements HasSize, HasStyle {

    private final String CLASS_NAME = "divider";

    private final Div divider;

    public Divider(String height) {
        this(FlexComponent.Alignment.CENTER, height);
    }

    public Divider(FlexComponent.Alignment alignItems, String height) {
        setAlignItems(alignItems);
        setClassName(CLASS_NAME);
        setHeight(height);

        divider = new Div();
        UIUtils.setBackgroundColor(LumoStyles.Color.Contrast._10, divider);
        divider.setHeight("1px");
        divider.setWidthFull();
        add(divider);
    }

}
