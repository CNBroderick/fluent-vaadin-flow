/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-23 15:50:47
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.HasSizeFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasSizeFactory<T extends Component & HasSize, E extends HasSizeFactory<T, E>> extends IFlowFactory<T> {
    default E minWidth(String minWidth) {
        get().setMinWidth(minWidth);
        return (E) this;
    }

    default E sizeFull() {
        get().setSizeFull();
        return (E) this;
    }

    default E sizeUndefined() {
        get().setSizeUndefined();
        return (E) this;
    }

    default E minHeight(String minHeight) {
        get().setMinHeight(minHeight);
        return (E) this;
    }

    default E height(String height) {
        get().setHeight(height);
        return (E) this;
    }

    default E height(String minHeight, String maxHeight) {
        get().setMinHeight(minHeight);
        get().setMaxHeight(maxHeight);
        return (E) this;
    }

    default E height(String minHeight, String height, String maxHeight) {
        get().setMinHeight(minHeight);
        get().setHeight(height);
        get().setMaxHeight(maxHeight);
        return (E) this;
    }

    default E width(String minWidth, String maxWidth) {
        get().setMinWidth(minWidth);
        get().setMaxWidth(maxWidth);
        return (E) this;
    }

    default E width(String minWidth, String width, String maxWidth) {
        get().setMinWidth(minWidth);
        get().setWidth(width);
        get().setMaxWidth(maxWidth);
        return (E) this;
    }

    default E widthFull() {
        get().setWidthFull();
        return (E) this;
    }

    default E maxHeight(String maxHeight) {
        get().setMaxHeight(maxHeight);
        return (E) this;
    }

    default E width(String width) {
        get().setWidth(width);
        return (E) this;
    }

    default E maxWidth(String maxWidth) {
        get().setMaxWidth(maxWidth);
        return (E) this;
    }

    default E heightFull() {
        get().setHeightFull();
        return (E) this;
    }
}
