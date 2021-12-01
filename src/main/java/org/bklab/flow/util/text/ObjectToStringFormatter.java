/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:45
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.util.text.ObjectToStringFormatter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.text;

import com.vaadin.flow.function.SerializableFunction;
import org.bklab.flow.util.number.DigitalFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectToStringFormatter implements SerializableFunction<Object, String> {

    @Override
    public String apply(Object o) {
        return serialize(o);
    }

    public String serialize(Object o) {
        if (o == null) return "";
        if (o instanceof String) return (String) o;
        if (o instanceof Number) return serialize((Number) o);
        if (o instanceof LocalDate) return serialize((LocalDate) o);
        if (o instanceof LocalTime) return serialize((LocalTime) o);
        if (o instanceof LocalDateTime) return serialize((LocalDateTime) o);
        if (o instanceof TemporalAccessor) return serialize((TemporalAccessor) o);
        if (o instanceof Collection<?>) return serialize((Collection<?>) o);
        if (o instanceof Map<?, ?>) return serialize((Map<?, ?>) o);
        return String.valueOf(o);
    }

    public String serialize(Number o) {
        return o instanceof Integer || o instanceof Long
               ? new DigitalFormatter(o).toInteger() : new DigitalFormatter(o).toFormatted();
    }

    public String serialize(Map<?, ?> o) {
        return o.entrySet().stream().map(e -> serialize(e.getKey()) + ": " + serialize(e.getValue())).collect(Collectors.joining("\n"));
    }

    public String serialize(Collection<?> o) {
        return o.stream().map(this::serialize).collect(Collectors.joining(", "));
    }

    public String serialize(LocalDate o) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(o);
    }

    public String serialize(LocalTime o) {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(o);
    }

    public String serialize(LocalDateTime o) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(o);
    }

    public String serialize(TemporalAccessor o) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(o);
    }
}
