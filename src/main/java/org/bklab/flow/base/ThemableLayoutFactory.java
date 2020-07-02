/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-17 20:13:07
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.ThemableLayoutFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ThemableLayoutFactory<T extends Component & ThemableLayout, E extends ThemableLayoutFactory<T, E>> extends IFlowFactory<T> {

    default E boxSizing(BoxSizing boxSizing) {
        get().setBoxSizing(boxSizing);
        return (E) this;
    }

    default E boxSizingUndefined() {
        get().setBoxSizing(BoxSizing.UNDEFINED);
        return (E) this;
    }

    default E boxSizingContentBox() {
        get().setBoxSizing(BoxSizing.CONTENT_BOX);
        return (E) this;
    }

    default E boxSizingBorderBox() {
        get().setBoxSizing(BoxSizing.BORDER_BOX);
        return (E) this;
    }

    default E margin(boolean margin) {
        get().setMargin(margin);
        return (E) this;
    }

    default E noMargin() {
        get().setMargin(false);
        return (E) this;
    }

    default E hasMargin() {
        get().setMargin(true);
        return (E) this;
    }

    default E spacing(boolean spacing) {
        get().setSpacing(spacing);
        return (E) this;
    }

    default E noSpacing() {
        get().setSpacing(false);
        return (E) this;
    }

    default E hasSpacing() {
        get().setSpacing(true);
        return (E) this;
    }

    default E padding(boolean padding) {
        get().setPadding(padding);
        return (E) this;
    }

    default E noPadding() {
        get().setPadding(false);
        return (E) this;
    }

    default E hasPadding() {
        get().setPadding(true);
        return (E) this;
    }
}
