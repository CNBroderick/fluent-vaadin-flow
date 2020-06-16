package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasValueChangeModeFactory<T extends Component & HasValueChangeMode, E extends HasValueChangeModeFactory<T, E>> extends IFlowFactory<T> {
    default E valueChangeMode(ValueChangeMode valueChangeMode) {
        get().setValueChangeMode(valueChangeMode);
        return (E) this;
    }

    default E valueChangeTimeout(int valueChangeTimeout) {
        get().setValueChangeTimeout(valueChangeTimeout);
        return (E) this;
    }
}
