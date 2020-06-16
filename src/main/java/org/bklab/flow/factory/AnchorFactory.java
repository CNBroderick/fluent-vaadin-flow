package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.AbstractStreamResource;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.FocusableFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class AnchorFactory extends FlowFactory<Anchor, AnchorFactory> implements
        HtmlContainerFactory<Anchor, AnchorFactory>, FocusableFactory<Anchor, AnchorFactory> {

    public AnchorFactory() {
        this(new Anchor());
    }

    public AnchorFactory(String href, String text) {
        this(new Anchor(href, text));
    }

    public AnchorFactory(AbstractStreamResource href, String text) {
        this(new Anchor(href, text));
    }

    public AnchorFactory(String href, Component... components) {
        this(new Anchor(href, components));
    }

    public AnchorFactory(Anchor component) {
        super(component);
    }

    public AnchorFactory target(String target) {
        get().setTarget(target);
        return this;
    }

    public AnchorFactory removeHref() {
        get().removeHref();
        return this;
    }

    public AnchorFactory href(AbstractStreamResource href) {
        get().setHref(href);
        return this;
    }

    public AnchorFactory href(String href) {
        get().setHref(href);
        return this;
    }

}
