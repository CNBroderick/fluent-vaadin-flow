package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataView;
import com.vaadin.flow.data.provider.IdentifierProvider;
import org.bklab.flow.IFlowFactory;

public interface ComboBoxDataViewFactory<T, C extends Component & DataView<T>, E extends ComboBoxDataViewFactory<T, C, E>> extends IFlowFactory<C>, HasReturnThis<E> {

    default E refreshItem(T object) {
        get().refreshItem(object);
        return thisObject();
    }

    default E setIdentifierProvider(IdentifierProvider<T> identifierProvider) {
        get().setIdentifierProvider(identifierProvider);
        return thisObject();
    }
}
