package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.router.RouterLayout;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface RouterLayoutFactory<C extends Component & RouterLayout, E extends RouterLayoutFactory<C, E>> extends IFlowFactory<C> {
    default E showRouterLayoutContent(HasElement content) {
        get().showRouterLayoutContent(content);
        return (E) this;
    }

    default E removeRouterLayoutContent(HasElement oldContent) {
        get().removeRouterLayoutContent(oldContent);
        return (E) this;
    }

}
