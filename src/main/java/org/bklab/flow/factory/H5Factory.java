package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H5;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H5Factory extends FlowFactory<H5, H5Factory>
        implements HtmlContainerFactory<H5, H5Factory>, ClickNotifierFactory<H5, H5Factory> {

    public H5Factory() {
        this(new H5());
    }

    public H5Factory(Component... component) {
        this(new H5(component));
    }

    public H5Factory(String text) {
        this(new H5(text));
    }

    public H5Factory(H5 component) {
        super(component);
    }
}
