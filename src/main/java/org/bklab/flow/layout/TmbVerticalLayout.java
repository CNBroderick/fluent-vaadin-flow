/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-18 18:20:03
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.TmbVerticalLayout
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.factory.VerticalLayoutFactory;

public class TmbVerticalLayout extends VerticalLayout {

    private final VerticalLayout top = new VerticalLayoutFactory().noMargin().noPadding().justifyContentModeCenter().defaultHorizontalComponentAlignmentStart().get();
    private final VerticalLayout middle = new VerticalLayoutFactory().noMargin().noPadding().justifyContentModeCenter().defaultHorizontalComponentAlignmentCenter().get();
    private final VerticalLayout bottom = new VerticalLayoutFactory().noMargin().noPadding().justifyContentModeCenter().defaultHorizontalComponentAlignmentEnd().get();

    public TmbVerticalLayout() {
        new VerticalLayoutFactory(this)
                .alignSelf(Alignment.START, top)
                .alignSelf(Alignment.CENTER, middle)
                .alignSelf(Alignment.END, bottom)
                .justifyContentModeCenter()
                .defaultHorizontalComponentAlignmentCenter()
                .noSpacing()
                .noMargin()
                .noPadding()
                .heightFull()
                .widthFull()
                .add(top, middle, bottom)
                .expand(middle);

        top.getStyle()
                .set("border-bottom", "1px solid #f0f0f0")
                .set("border-radius", "2px 2px 0 0")
                .set("padding", "16px 24px")
                .set("background", "white")
                .set("color", "rgba(0,0,0,.65)")
        ;

        middle.getStyle()
                .set("font-size", "14px")
                .set("padding-left", "24px")
                .set("padding-right", "24px")
                .set("padding-bottom", "5px")
                .set("line-height", "1.5715")
        ;

        bottom.getStyle()
                .set("padding", "10px 16px")
                .set("text-align", "right")
                .set("background", "0 0")
                .set("border-top", "1px solid #f0f0f0")
                .set("border-radius", "0 0 2px 2px")
        ;
    }

    public TmbVerticalLayout noBorder() {
        top.getStyle().remove("border-bottom").remove("border-radius");
        bottom.getStyle().remove("border-top").remove("border-radius");
        return this;
    }

    public VerticalLayoutFactory getLeft() {
        return new VerticalLayoutFactory(top);
    }

    public VerticalLayoutFactory getMiddle() {
        return new VerticalLayoutFactory(middle);
    }

    public VerticalLayoutFactory getBottom() {
        return new VerticalLayoutFactory(bottom);
    }

    public TmbVerticalLayout top(Component... components) {
        top.add(components);
        return this;
    }

    public TmbVerticalLayout middle(Component... components) {
        middle.add(components);
        return this;
    }

    public TmbVerticalLayout bottom(Component... components) {
        bottom.add(components);
        return this;
    }

    public TmbVerticalLayout clearTop() {
        top.removeAll();
        return this;
    }

    public TmbVerticalLayout clearMiddle() {
        middle.removeAll();
        return this;
    }

    public TmbVerticalLayout clearBottom() {
        bottom.removeAll();
        return this;
    }

    public VerticalLayoutFactory asFactory() {
        return new VerticalLayoutFactory(this);
    }
}
