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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;
import org.bklab.flow.image.ImageBase;
import org.bklab.flow.util.lumo.UIUtils;

import java.util.function.Consumer;

@CssImport("./styles/components/brand-expression.css")
public class BrandExpression extends Div {

    private final static String CLASS_NAME = "brand-expression";

    private final Image logo;
    private final Label title;
    private Consumer<Image> expandIconConsumer;
    private Consumer<Image> collapseIconConsumer;

    public BrandExpression(String text) {
        setClassName(CLASS_NAME);

        this.expandIconConsumer = logo -> logo.setSrc(ImageBase.getResource("broderick.cn.png"));
        this.collapseIconConsumer = logo -> logo.setSrc(ImageBase.getResource("bklab.png"));

        logo = new Image();
        toggle(true);
        logo.setAlt(text + " logo");
        logo.setClassName(CLASS_NAME + "__logo");

        title = UIUtils.createH3Label(text);
        title.addClassName(CLASS_NAME + "__title");

        add(logo, title);
    }

    public BrandExpression logo(Class<?> baseClass, String name) {
        return logo(new StreamResource(name, () -> baseClass.getResourceAsStream(name)));
    }

    public BrandExpression logo(AbstractStreamResource src) {
        logo.setSrc(src);
        return this;
    }

    public BrandExpression logo(String src) {
        logo.setSrc(src);
        return this;
    }

    public BrandExpression title(String text) {
        title.removeAll();
        title.setText(text);
        return this;
    }

    public BrandExpression title(Component... component) {
        title.removeAll();
        title.setText("");
        title.add(component);
        return this;
    }

    public BrandExpression toggle(boolean expand) {
        if (expand) {
            if (expandIconConsumer != null) expandIconConsumer.accept(logo);
        } else {
            if (collapseIconConsumer != null) collapseIconConsumer.accept(logo);
        }
        return this;
    }

    public BrandExpression expandIconConsumer(Consumer<Image> expandIconConsumer) {
        this.expandIconConsumer = expandIconConsumer;
        return this;
    }

    public BrandExpression collapseIconConsumer(Consumer<Image> collapseIconConsumer) {
        this.collapseIconConsumer = collapseIconConsumer;
        return this;
    }
}
