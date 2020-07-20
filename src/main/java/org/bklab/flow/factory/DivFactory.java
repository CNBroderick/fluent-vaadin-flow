package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class DivFactory extends FlowFactory<Div, DivFactory> implements
        HtmlContainerFactory<Div, DivFactory>, ClickNotifierFactory<Div, DivFactory> {

    public DivFactory() {
        this(new Div());
    }

    public DivFactory(Component... components) {
        this(new Div(components));
    }

    public DivFactory(Div component) {
        super(component);
    }
}
