/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-23 15:18:33
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.HasValueFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.IFlowFactory;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E addToParameter(Map<String, Supplier<Object>> parameterMap, String name) {
        parameterMap.put(name, () -> Optional.ofNullable(get().getValue()).orElse(null));
        return addToParameter(parameterMap, name, s -> s instanceof String ? ((String) s).trim().isEmpty() ? null : ((String) s).trim() : s);
    }

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E addToParameter(Map<String, Supplier<Object>> parameterMap, String name, Function<T, ?> mapValue) {
        parameterMap.put(name, () -> Optional.ofNullable(get().getValue()).map(mapValue).orElse(null));
        return (E) this;
    }

    default <N, G extends FluentCrudView<N, ? extends Grid<N>>> E watch(G view) {
        view.watch(get());
        return (E) this;
    }

}
