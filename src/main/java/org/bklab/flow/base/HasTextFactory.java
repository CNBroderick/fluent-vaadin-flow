package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasTextFactory<C extends Component & HasText, E extends HasTextFactory<C, E>> extends IFlowFactory<C> {

    default E text(String text) {
        get().setText(text);
        return (E) this;
    }
}
