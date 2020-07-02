/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.detailsdrawer.DetailsDrawer
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.detailsdrawer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.layout.FlexBoxLayout;

@CssImport("./styles/components/details-drawer.css")
public class DetailsDrawer extends FlexBoxLayout {

    private final String CLASS_NAME = "details-drawer";

    private final FlexBoxLayout header;
    private final FlexBoxLayout content;
    private final FlexBoxLayout footer;

    public DetailsDrawer(Position position, Component... components) {
        setClassName(CLASS_NAME);
        setPosition(position);

        header = new FlexBoxLayout();
        header.setClassName(CLASS_NAME + "__header");

        content = new FlexBoxLayout(components);
        content.setClassName(CLASS_NAME + "__content");
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);

        footer = new FlexBoxLayout();
        footer.setClassName(CLASS_NAME + "__footer");

        add(header, content, footer);
    }

    public FlexBoxLayout getHeader() {
        return this.header;
    }

    public void setHeader(Component... components) {
        this.header.removeAll();
        this.header.add(components);
    }

    public void setContent(Component... components) {
        this.content.removeAll();
        this.content.add(components);
    }

    public void setFooter(Component... components) {
        this.footer.removeAll();
        this.footer.add(components);
    }

    public void setPosition(Position position) {
        getElement().setAttribute("position", position.name().toLowerCase());
    }

    public void hide() {
        getElement().setAttribute("open", false);
    }

    public void show() {
        getElement().setAttribute("open", true);
    }

    public enum Position {
        BOTTOM, RIGHT
    }
}
