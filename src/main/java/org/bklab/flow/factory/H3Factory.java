package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H3;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H3Factory extends FlowFactory<H3, H3Factory>
        implements HtmlContainerFactory<H3, H3Factory>, ClickNotifierFactory<H3, H3Factory> {

    public H3Factory() {
        this(new H3());
    }

    public H3Factory(Component... component) {
        this(new H3(component));
    }

    public H3Factory(String text) {
        this(new H3(text));
    }

    public H3Factory(H3 component) {
        super(component);
    }
}
