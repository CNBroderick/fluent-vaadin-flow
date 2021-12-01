/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-27 09:25:21
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.util.url.QueryParameterUtil
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.url;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.QueryParameters;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class QueryParameterUtil {

    private static QueryParameterUtil EMPTY_QUERY_PARAMETER;

    private final Map<String, List<String>> parameters;
    private final String queryString;

    private QueryParameterUtil() {
        this.parameters = Collections.emptyMap();
        this.queryString = "";
    }

    public static QueryParameterUtil emptyParameter() {
        if (EMPTY_QUERY_PARAMETER == null) EMPTY_QUERY_PARAMETER = new QueryParameterUtil();
        return EMPTY_QUERY_PARAMETER;
    }

    public QueryParameterUtil(Location location) {
        this(location.getQueryParameters());
    }

    public QueryParameterUtil(BeforeEvent beforeEnterEvent) {
        this(beforeEnterEvent.getLocation().getQueryParameters());
    }

    public QueryParameterUtil(QueryParameters queryParameters) {
        this.parameters = queryParameters.getParameters();
        this.queryString = queryParameters.getQueryString();
    }

    public int getInt(String name) {
        try {
            return Integer.parseInt(get(name));
        } catch (Exception e) {
            return 0;
        }
    }

    public long getLong(String name) {
        try {
            return Long.parseLong(get(name));
        } catch (Exception e) {
            return 0;
        }
    }

    public String get(String name) {
        return Optional.ofNullable(parameters.get(name)).flatMap(s -> s.stream().findFirst()).orElse(null);
    }

    public String decode(String name) {
        return Optional.ofNullable(get(name)).map(value -> URLDecoder.decode(value, StandardCharsets.UTF_8)).orElse(null);
    }

    public String decode64(String name) {
        return Optional.ofNullable(get(name)).map(value -> new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8)).orElse(null);
    }

    public Optional<String> getOptional(String name) {
        return Optional.ofNullable(parameters.get(name)).flatMap(s -> s.stream().findFirst());
    }

    public List<String> getList(String name) {
        return Optional.ofNullable(parameters.get(name)).orElse(new ArrayList<>());
    }

    public <T> List<T> getList(String name, SerializableFunction<String, T> stringTSerializableFunction) {
        List<T> list = new ArrayList<>();
        for (String s : getList(name)) {
            try {
                list.add(stringTSerializableFunction.apply(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }

    public String getQueryString() {
        return queryString;
    }
}
