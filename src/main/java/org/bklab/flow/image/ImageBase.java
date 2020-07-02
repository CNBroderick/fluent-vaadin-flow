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
    public static Image getImage(String name) {
        return new Image(getResource(name), name);
    }

    public static Image getImage(String name, String alt) {
        return new Image(getResource(name), alt);
    }

    public static AbstractStreamResource getResource(String name) {
        return new StreamResource(name, () -> ImageBase.class.getResourceAsStream(name));
    }
}
