package org.bklab.flow.factory;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HtmlContainer;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HtmlContainerFactory;

public class GenerateHtmlContainerFactory<C extends HtmlContainer & ClickNotifier<C>>
        extends FlowFactory<C, GenerateHtmlContainerFactory<C>> implements
        HtmlContainerFactory<C, GenerateHtmlContainerFactory<C>>,
        ClickNotifierFactory<C, GenerateHtmlContainerFactory<C>> {

    public GenerateHtmlContainerFactory(C component) {
        super(component);
    }
}
