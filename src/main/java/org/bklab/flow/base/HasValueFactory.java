package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.IFlowFactory;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public interface HasValueFactory<T, A extends HasValue.ValueChangeEvent<T>, C extends Component & HasValue<A, T>, E extends HasValueFactory<T, A, C, E>> extends IFlowFactory<C> {
    default E clear() {
        get().clear();
        return (E) this;
    }

    default E readOnly(boolean readOnly) {
        get().setReadOnly(readOnly);
        return (E) this;
    }

    default E value(T value) {
        get().setValue(value);
        return (E) this;
    }

    default E valueChangeListener(HasValue.ValueChangeListener<? super A> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return (E) this;
    }

    default E requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return (E) this;
    }

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E addFluentCrudViewParameter(G view, String name) {
        view.addParameter(name, get());
        return (E) this;
    }

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E addFluentCrudViewParameter(G view, String name, Function<T, ?> mapValue) {
        view.addParameter(name, get(), mapValue);
        return (E) this;
    }

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E watch(G view) {
        view.watch(get());
        return (E) this;
    }

}
