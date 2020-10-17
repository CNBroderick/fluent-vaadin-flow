package org.bklab.flow.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.util.number.DigitalFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public interface FluentCommonGrid<T> extends Supplier<Grid<T>> {

    default void addIndexColumn() {
        get().addColumn(r -> {
            int i = IntStream.range(0, get().getDataCommunicator().getItemCount())
                    .filter(a -> r == get().getDataCommunicator().getItem(a))
                    .findFirst().orElse(-1) + 1;
            return i <= 0 ? "-" : new DigitalFormatter(i).toInteger();
        }).setHeader("#").setWidth("6em").setTextAlign(ColumnTextAlign.END).setKey("INDEX_COLUMN");
    }

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

    default Button createDetailRenderVisibilityButton(T entity, Map<T, Button> buttonsMap) {
        Function<T, String> caption = e -> get().isDetailsVisible(e) ? "收起" : "详情";
        return new ButtonFactory(caption.apply(entity), e -> {
            get().setDetailsVisible(entity, !get().isDetailsVisible(entity));
            buttonsMap.forEach((r, b) -> b.setText(caption.apply(r)));
        }).peek(b -> buttonsMap.put(entity, b)).lumoSmall().lumoTertiaryInline().get();
    }
}
