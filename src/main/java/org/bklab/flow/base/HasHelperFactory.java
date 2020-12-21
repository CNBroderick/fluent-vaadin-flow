package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasHelper;
import org.bklab.flow.IFlowFactory;

public interface HasHelperFactory<C extends Component & HasHelper, E extends HasHelperFactory<C, E>> extends IFlowFactory<C>, HasReturnThis<E> {

    default E helperText(String helperText) {
        get().setHelperText(helperText);
        return thisObject();
    }

    default E helperComponent(Component component) {
        get().setHelperComponent(component);
        return thisObject();
    }
}
