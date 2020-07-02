/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.mix.Token
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.mix;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.layout.size.Left;
import org.bklab.flow.layout.size.Right;
import org.bklab.flow.util.FontSize;
import org.bklab.flow.util.LumoStyles;
import org.bklab.flow.util.TextColor;
import org.bklab.flow.util.UIUtils;
import org.bklab.flow.util.css.BorderRadius;
import org.bklab.flow.util.css.Display;

public class Token extends FlexBoxLayout {

    private final String CLASS_NAME = "token";

    public Token(String text) {
        setAlignItems(FlexComponent.Alignment.CENTER);
        setBackgroundColor(LumoStyles.Color.Primary._10);
        setBorderRadius(BorderRadius.M);
        setClassName(CLASS_NAME);
        setDisplay(Display.INLINE_FLEX);
        setPadding(Left.S, Right.XS);
        setSpacing(Right.XS);

        Label label = UIUtils.createLabel(FontSize.S, TextColor.BODY, text);
        Button button = UIUtils.createButton(VaadinIcon.CLOSE_SMALL, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY_INLINE);
        add(label, button);
    }

}
