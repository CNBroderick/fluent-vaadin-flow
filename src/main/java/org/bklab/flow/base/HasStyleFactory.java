package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasStyleFactory<C extends Component & HasStyle, E extends HasStyleFactory<C, E>> extends IFlowFactory<C> {
    default E className(String... className) {
        get().addClassNames(className);
        return (E) this;
    }

    default E removeClassName(String removeClassName) {
        get().removeClassName(removeClassName);
        return (E) this;
    }

    default E className(String string, boolean set) {
        get().setClassName(string, set);
        return (E) this;
    }

    default E className(String className) {
        get().setClassName(className);
        return (E) this;
    }

    default E classNames(String... classNames) {
        get().addClassNames(classNames);
        return (E) this;
    }

    default E removeClassNames(String... removeClassNames) {
        get().removeClassNames(removeClassNames);
        return (E) this;
    }

    default E hasClassName(String hasClassName) {
        get().hasClassName(hasClassName);
        return (E) this;
    }
}
