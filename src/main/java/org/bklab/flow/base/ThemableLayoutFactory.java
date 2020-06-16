package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface ThemableLayoutFactory<T extends Component & ThemableLayout, E extends ThemableLayoutFactory<T, E>> extends IFlowFactory<T> {

    default E boxSizing(BoxSizing boxSizing) {
        get().setBoxSizing(boxSizing);
        return (E) this;
    }

    default E margin(boolean margin) {
        get().setMargin(margin);
        return (E) this;
    }

    default E spacing(boolean spacing) {
        get().setSpacing(spacing);
        return (E) this;
    }

    default E padding(boolean padding) {
        get().setPadding(padding);
        return (E) this;
    }
}
