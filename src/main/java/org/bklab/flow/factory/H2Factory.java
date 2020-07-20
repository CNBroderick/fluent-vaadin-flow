package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H2Factory extends FlowFactory<H2, H2Factory>
        implements HtmlContainerFactory<H2, H2Factory>, ClickNotifierFactory<H2, H2Factory> {

    public H2Factory() {
        this(new H2());
    }

    public H2Factory(Component... component) {
        this(new H2(component));
    }

    public H2Factory(String text) {
        this(new H2(text));
    }

    public H2Factory(H2 component) {
        super(component);
    }
}
