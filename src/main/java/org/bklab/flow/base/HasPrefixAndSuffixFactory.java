package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.HasPrefixAndSuffix;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasPrefixAndSuffixFactory<T extends Component & HasPrefixAndSuffix, E extends HasPrefixAndSuffixFactory<T, E>> extends IFlowFactory<T> {
    default E suffixComponent(Component suffixComponent) {
        get().setSuffixComponent(suffixComponent);
        return (E) this;
    }

    default E prefixComponent(Component prefixComponent) {
        get().setPrefixComponent(prefixComponent);
        return (E) this;
    }
}
