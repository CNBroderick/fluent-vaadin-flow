package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H1Factory extends FlowFactory<H1, H1Factory>
        implements HtmlContainerFactory<H1, H1Factory>, ClickNotifierFactory<H1, H1Factory> {

    public H1Factory() {
        this(new H1());
    }

    public H1Factory(Component... component) {
        this(new H1(component));
    }

    public H1Factory(String text) {
        this(new H1(text));
    }

    public H1Factory(H1 component) {
        super(component);
    }
}
