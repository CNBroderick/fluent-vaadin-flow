package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H6;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H6Factory extends FlowFactory<H6, H6Factory>
        implements HtmlContainerFactory<H6, H6Factory>, ClickNotifierFactory<H6, H6Factory> {

    public H6Factory() {
        this(new H6());
    }

    public H6Factory(Component... component) {
        this(new H6(component));
    }

    public H6Factory(String text) {
        this(new H6(text));
    }

    public H6Factory(H6 component) {
        super(component);
    }
}
