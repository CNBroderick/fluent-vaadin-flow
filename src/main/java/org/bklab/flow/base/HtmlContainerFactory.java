package org.bklab.flow.base;

import com.vaadin.flow.component.HtmlContainer;
import org.bklab.flow.IFlowFactory;

public interface HtmlContainerFactory<C extends HtmlContainer, E extends HtmlContainerFactory<C, E>> extends IFlowFactory<C>,
        HtmlComponentFactory<C, E>,
        HasComponentsFactory<C, E>,
        HasTextFactory<C, E> {

}
