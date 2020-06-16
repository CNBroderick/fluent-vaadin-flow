package org.bklab.flow.factory;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.AbstractStreamResource;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;

public class ImageFactory extends FlowFactory<Image, ImageFactory>
        implements ClickNotifierFactory<Image, ImageFactory> {

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
