/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.drawer.BrandExpression
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.drawer;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import org.bklab.flow.util.UIUtils;

@CssImport("./styles/components/brand-expression.css")
public class BrandExpression extends Div {

    private final String CLASS_NAME = "brand-expression";

    private final Image logo;
    private final Label title;

    public BrandExpression(String text) {
        setClassName(CLASS_NAME);

        logo = new Image(UIUtils.IMG_PATH + "logos/18.png", "");
        logo.setAlt(text + " logo");
        logo.setClassName(CLASS_NAME + "__logo");

        title = UIUtils.createH3Label(text);
        title.addClassName(CLASS_NAME + "__title");

        add(logo, title);
    }

}
