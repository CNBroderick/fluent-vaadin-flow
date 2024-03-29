/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-09-30 16:18:41
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.util.url.QueryParameterBuilder
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.url;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.router.QueryParameters;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryParameterBuilder implements SerializableSupplier<QueryParameters> {

    private final Map<String, List<String>> map;

    public QueryParameterBuilder() {
        this.map = new LinkedHashMap<>();
    }

    public QueryParameterBuilder(QueryParameters queryParameters) {
        this.map = queryParameters.getParameters();
    }

    @SafeVarargs
    public final <T> QueryParameterBuilder add(Function<T, String> function, String name, T... params) {
        this.map.put(name, params == null || params.length < 1
                ? Collections.emptyList()
                : Stream.of(params).map(function).collect(Collectors.toList()));
        return this;
    }

    public QueryParameterBuilder add(String name, Object... params) {
        this.map.put(name, params == null || params.length < 1
                           ? Collections.emptyList()
                           : Stream.of(params).map(String::valueOf).collect(Collectors.toList()));
        return this;
    }

    public QueryParameterBuilder encode(String name, Object... params) {
        return add(name, Arrays.stream(params).map(String::valueOf)
                .map(s -> URLEncoder.encode(s, StandardCharsets.UTF_8))
                .collect(Collectors.toList()).toArray(new Object[]{}));
    }

    public QueryParameterBuilder encode64(String name, Object... params) {
        return add(name, Arrays.stream(params).map(String::valueOf)
                .map(s -> Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8)))
                .collect(Collectors.toList()).toArray(new Object[]{}));
    }

    public QueryParameterBuilder add(boolean add, String name, Object... params) {
        return add ? add(name, params) : this;
    }

    @SafeVarargs
    public final <T> QueryParameterBuilder add(Predicate<T> add, String name, T... params) {
        return add(name, params == null ? Collections.emptyList()
                                        : Stream.of(params).filter(add).collect(Collectors.toList()));
    }

    public QueryParameterBuilder peek(Consumer<QueryParameterBuilder> consumer) {
        consumer.accept(this);
        return this;
    }

    @Override
    public QueryParameters get() {
        return new QueryParameters(map);
    }


    public <C extends Class<? extends Component>> Consumer<UI> getNavigateConsumer(C target) {
        return ui -> navigate(ui, target);
    }

    public <C extends Class<? extends Component>> QueryParameterBuilder navigate(C target) {
        return navigate(UI.getCurrent(), target);
    }

    public <C extends Class<? extends Component>> QueryParameterBuilder navigate(UI ui, C target) {
        ui.getInternals().getRouter().getRegistry().getTargetUrl(target).ifPresent(uri -> ui.navigate(uri, get()));
        return this;
    }

    public <C extends Class<? extends Component>> Optional<String> createTargetFullUrl(C target) {
        return UI.getCurrent().getInternals().getRouter().getRegistry().getTargetUrl(target).map(url -> url + "?" + get().getQueryString());
    }

    public <C extends Class<? extends Component>> boolean open(UI ui, C target) {
        AtomicBoolean success = new AtomicBoolean(true);
        ui.getInternals().getRouter().getRegistry().getTargetUrl(target).ifPresentOrElse(
                uri -> ui.getPage().open(uri + "?" + get().getQueryString(), "_blank"),
                () -> success.set(false));
        return success.get();
    }
}
