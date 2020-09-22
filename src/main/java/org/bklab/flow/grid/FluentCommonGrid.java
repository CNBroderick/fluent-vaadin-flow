package org.bklab.flow.grid;

import com.vaadin.flow.component.grid.Grid;
import org.bklab.flow.util.number.DigitalFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public interface FluentCommonGrid<T> extends Supplier<Grid<T>> {

    default void addIntColumn(Function<T, Integer> function, String suffix, String header, String key) {
        get().addColumn(r -> new DigitalFormatter(function.apply(r)).toInteger() + " " + suffix)
                .setComparator(Comparator.comparingInt(function::apply))
                .setKey(key).setHeader(header)
        ;
    }

    default void addLongColumn(Function<T, Long> function, String suffix, String header, String key) {
        get().addColumn(r -> new DigitalFormatter(function.apply(r)).toInteger() + " " + suffix)
                .setComparator(Comparator.comparingLong(function::apply))
                .setKey(key).setHeader(header)
        ;
    }

    default void addDataColumn(Function<T, Long> function, String header, String key) {
        get().addColumn(r -> new DigitalFormatter(function.apply(r)).toDataSize())
                .setComparator(Comparator.comparingLong(function::apply))
                .setKey(key).setHeader(header)
        ;
    }

    default void addDoubleColumn(Function<T, Double> function, String suffix, String header, String key) {
        get().addColumn(r -> new DigitalFormatter(function.apply(r)).toFormatted() + " " + suffix)
                .setComparator(Comparator.comparingDouble(function::apply))
                .setKey(key).setHeader(header);
    }

    default void addDateTimeColumn(Function<T, LocalDateTime> function, String header, String key) {
        get().addColumn(r -> DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(function.apply(r)))
                .setComparator(Comparator.comparingLong(r -> Optional.ofNullable(function.apply(r))
                        .map(t -> t.toEpochSecond(ZoneOffset.ofHours(8))).orElse(0L)))
                .setKey(key).setHeader(header);
    }

    default void addDateColumn(Function<T, LocalDate> function, String header, String key) {
        get().addColumn(r -> DateTimeFormatter.ofPattern("uuuu-MM-dd").format(function.apply(r)))
                .setComparator(Comparator.comparingLong(r -> Optional.ofNullable(function.apply(r))
                        .map(LocalDate::toEpochDay).orElse(0L)))
                .setKey(key).setHeader(header);
    }

    default void addTimeColumn(Function<T, LocalTime> function, String header, String key) {
        get().addColumn(r -> DateTimeFormatter.ofPattern("HH:mm:ss").format(function.apply(r)))
                .setComparator(Comparator.comparingLong(r -> Optional.ofNullable(function.apply(r))
                        .map(LocalTime::toNanoOfDay).orElse(0L)))
                .setKey(key).setHeader(header);
    }
}
