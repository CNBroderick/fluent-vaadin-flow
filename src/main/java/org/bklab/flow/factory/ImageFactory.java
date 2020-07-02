/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 16:33:55
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.factory.ImageFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.factory;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.AbstractStreamResource;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class ImageFactory extends FlowFactory<Image, ImageFactory>
        implements ClickNotifierFactory<Image, ImageFactory>, HtmlContainerFactory<Image, ImageFactory> {

    public ImageFactory() {
        this(new Image());
    }

    public ImageFactory(Image component) {
        super(component);
    }

    public ImageFactory(String src, String alt) {
        this(new Image(src, alt));
    }

    public ImageFactory(AbstractStreamResource src, String alt) {
        this(new Image(src, alt));
    }

    public ImageFactory src(String src) {
        get().setSrc(src);
        return this;
    }

    public ImageFactory src(AbstractStreamResource src) {
        get().setSrc(src);
        return this;
    }

    public ImageFactory alt(String alt) {
        get().setAlt(alt);
        return this;
    }

}
