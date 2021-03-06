package org.bklab.flow.util.url;

import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.router.QueryParameters;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public QueryParameterBuilder add(boolean add, String name, Object... params) {
        return add ? add(name, params) : this;
    }

    @SafeVarargs
    public final <T> QueryParameterBuilder add(Predicate<T> add, String name, T... params) {
        return add(name, params == null ? Collections.emptyList()
                : Stream.of(params).filter(add).collect(Collectors.toList()));
    }

    @Override
    public QueryParameters get() {
        return new QueryParameters(map);
    }
}
