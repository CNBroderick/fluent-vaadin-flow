package org.bklab.flow.base;

import com.vaadin.flow.component.HtmlComponent;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HtmlComponentFactory<C extends HtmlComponent, E extends HtmlComponentFactory<C, E>> extends IFlowFactory<C> {

    default E title(String title) {
        get().setTitle(title);
        return (E) this;
    }

}
