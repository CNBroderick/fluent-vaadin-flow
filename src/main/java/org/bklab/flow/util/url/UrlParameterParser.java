/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-01 13:55:46
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.UrlParameterParser
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.util.url;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


public class UrlParameterParser {

    private final Map<String, List<String>> parameterMap;
    private final Charset charset;

    public UrlParameterParser(URL url) {
        this(url, StandardCharsets.UTF_8);
    }

    public UrlParameterParser(String url) {
        this(url, StandardCharsets.UTF_8);
    }

    public UrlParameterParser(URL url, Charset charset) {
        this.charset = StandardCharsets.UTF_8;
        this.parameterMap = url == null ? Collections.emptyMap() : splitQuery(url.getQuery());
    }

    public UrlParameterParser(String url, Charset charset) {
        this.charset = StandardCharsets.UTF_8;
        this.parameterMap = url == null ? Collections.emptyMap() : splitQuery(url);
    }

    public Set<String> getKeys() {
        return parameterMap.keySet();
    }

    public String getValue(String parameterName) {
        List<String> values = getValues(parameterName);
        if (values != null) {
            for (String value : values) {
                return value;
            }
        }
        return null;
    }

    public UrlParameterParser forEach(BiConsumer<String, List<String>> consumer) {
        parameterMap.forEach(consumer);
        return this;
    }

    public UrlParameterParser forEachSingle(BiConsumer<String, String> consumer) {
        for (String key : parameterMap.keySet()) {
            consumer.accept(key, getValue(key));
        }
        return this;
    }

    public List<String> getValues(String parameterName) {
        return parameterMap.getOrDefault(parameterName, new ArrayList<>());
    }

    public Map<String, List<String>> getParameterMap() {
        return parameterMap;
    }

    private Map<String, List<String>> splitQuery(String parameter) {
        return parameter == null || parameter.isBlank() ? Collections.emptyMap() : Arrays.stream(parameter.split("&"))
                .map(this::splitQueryParameter)
                .collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey, LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private AbstractMap.SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
        final int idx = it.indexOf("=");
        final String key = URLDecoder.decode(idx > 0 ? it.substring(0, idx) : it, charset);
        final String value = idx > 0 && it.length() > idx + 1 ? URLDecoder.decode(it.substring(idx + 1), charset) : null;
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

}
