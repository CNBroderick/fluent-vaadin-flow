/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 13:22:07
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.layout.LmrHorizontalLayout
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bklab.flow.factory.HorizontalLayoutFactory;

public class LmrHorizontalLayout extends HorizontalLayout {

    private final HorizontalLayout left = new HorizontalLayoutFactory().noMargin().noPadding().alignItemsCenter().justifyContentModeCenter().defaultVerticalComponentAlignmentStart().get();
    private final HorizontalLayout middle = new HorizontalLayoutFactory().noMargin().noPadding().alignItemsCenter().justifyContentModeCenter().defaultVerticalComponentAlignmentCenter().get();
    private final HorizontalLayout right = new HorizontalLayoutFactory().noMargin().noPadding().alignItemsCenter().justifyContentModeCenter().defaultVerticalComponentAlignmentEnd().get();

    public LmrHorizontalLayout() {
        new HorizontalLayoutFactory(this)
                .alignSelf(Alignment.START, left)
                .alignSelf(Alignment.CENTER, middle)
                .alignSelf(Alignment.END, right)
                .justifyContentModeCenter()
                .defaultVerticalComponentAlignmentCenter()
                .alignItemsCenter()
                .noMargin()
                .noPadding()
                .noSpacing()
                .widthFull()
                .add(left, middle, right)
                .expand(middle);
    }

    public HorizontalLayoutFactory getLeft() {
        return new HorizontalLayoutFactory(left);
    }

    public HorizontalLayoutFactory getMiddle() {
        return new HorizontalLayoutFactory(middle);
    }

    public HorizontalLayoutFactory getRight() {
        return new HorizontalLayoutFactory(right);
    }

    public LmrHorizontalLayout left(Component... components) {
        left.add(components);
        for (Component component : components) {
            middle.setAlignSelf(Alignment.CENTER, component);
        }
        return this;
    }

    public LmrHorizontalLayout middle(Component... components) {
        middle.add(components);
        for (Component component : components) {
            middle.setAlignSelf(Alignment.CENTER, component);
        }
        return this;
    }

    public LmrHorizontalLayout right(Component... components) {
        right.add(components);
        for (Component component : components) {
            right.setAlignSelf(Alignment.CENTER, component);
        }
        return this;
    }

    public LmrHorizontalLayout clearLeft() {
        left.removeAll();
        return this;
    }

    public LmrHorizontalLayout clearMiddle() {
        middle.removeAll();
        return this;
    }

    public LmrHorizontalLayout clearRight() {
        right.removeAll();
        return this;
    }

    public HorizontalLayoutFactory asFactory() {
        return new HorizontalLayoutFactory(this);
    }
}
