package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H4;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class H4Factory extends FlowFactory<H4, H4Factory>
        implements HtmlContainerFactory<H4, H4Factory>, ClickNotifierFactory<H4, H4Factory> {

    public H4Factory() {
        this(new H4());
    }

    public H4Factory(Component... component) {
        this(new H4(component));
    }

    public H4Factory(String text) {
        this(new H4(text));
    }

    public H4Factory(H4 component) {
        super(component);
    }
}
