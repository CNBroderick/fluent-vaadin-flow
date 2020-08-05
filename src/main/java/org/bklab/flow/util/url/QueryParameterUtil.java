package org.bklab.flow.util.url;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.QueryParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QueryParameterUtil {

    private final Map<String, List<String>> parameters;
    private final String queryString;

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
