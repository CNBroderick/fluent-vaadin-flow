/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 16:33:55
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.HtmlComponentFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.HtmlComponent;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HtmlComponentFactory<C extends HtmlComponent, E extends HtmlComponentFactory<C, E>>
        extends IFlowFactory<C>, HasSizeFactory<C, E>, HasStyleFactory<C, E> {

    default E title(String title) {
        get().setTitle(title);
        return (E) this;
    }

}
