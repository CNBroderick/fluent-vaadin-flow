/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 16:19:00
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.image.ImageBase
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.image;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

public class ImageBase {
    private final Class<?> baseClass;

    public ImageBase() {
        this(ImageBase.class);
    }

    public ImageBase(Class<?> baseClass) {
        this.baseClass = baseClass;
    }

    public static Image getImage(String name) {
        return new ImageBase(ImageBase.class).image(name);
    }

    public static Image getImage(String name, String alt) {
        return new ImageBase(ImageBase.class).image(name, alt);
    }

    public static AbstractStreamResource getResource(String name) {
        return new ImageBase(ImageBase.class).resource(name);
    }

    public Image image(String name) {
        return new Image(resource(name), name);
    }

    public Image image(String name, String alt) {
        return new Image(resource(name), alt);
    }

    public AbstractStreamResource resource(String name) {
        return new StreamResource(name, () -> baseClass.getResourceAsStream(name));
    }

}
