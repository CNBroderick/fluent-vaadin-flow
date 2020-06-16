package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.HasAutocorrect;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings({"unchecked"})
public interface HasAutocorrectFactory<T extends Component & HasAutocorrect, E extends HasAutocorrectFactory<T, E>> extends IFlowFactory<T> {
    default E autocorrect(boolean autocorrect) {
        get().setAutocorrect(autocorrect);
        return (E) this;
    }
}