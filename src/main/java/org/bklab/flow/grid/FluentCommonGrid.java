/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-04 13:01:15
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.grid.FluentCommonGrid
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import org.bklab.flow.components.badge.BadgeTag;
import org.bklab.flow.components.badge.BadgeTagStyle;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.GridFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.util.number.DigitalFormatter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public interface FluentCommonGrid<T> extends Supplier<Grid<T>> {

    default Grid.Column<T> addIndexColumn() {
        return get().addColumn(r -> {
            int i = IntStream.range(0, get().getDataCommunicator().getItemCount())
                            .filter(a -> r == get().getDataCommunicator().getItem(a))
                            .findFirst().orElse(-1) + 1;
            return i <= 0 ? "-" : new DigitalFormatter(i).toInteger();
        }).setHeader("#").setWidth("6em").setTextAlign(ColumnTextAlign.END).setKey("INDEX_COLUMN");
    }

    default Grid.Column<T> addStringColumn(Function<T, String> function, String header, String key) {
        return get().addColumn(function::apply).setHeader(header).setKey(key).setComparator(function::apply);
    }

    default Grid.Column<T> addStringTooltipColumn(Function<T, String> function, String header, String key) {
        return get().addComponentColumn(t -> {
            String content = function.apply(t);
            return new SpanFactory(content).tooltip(content).textOverflowEllipsis().alignContentBaseline().padding("0").get();
        }).setHeader(header).setKey(key).setComparator(function::apply);
    }

    default Grid.Column<T> addIntColumn(Function<T, Integer> function, String suffix, String header, String key) {
        return get().addColumn(r -> new DigitalFormatter(function.apply(r)).toInteger() + " " + suffix)
                .setComparator(Comparator.comparingInt(function::apply))
                .setKey(key).setHeader(header)
                ;
    }

    default Grid.Column<T> addLongColumn(Function<T, Long> function, String suffix, String header, String key) {
        return get().addColumn(r -> new DigitalFormatter(function.apply(r)).toInteger() + " " + suffix)
                .setComparator(Comparator.comparingLong(function::apply))
                .setKey(key).setHeader(header)
                ;
    }

    default Grid.Column<T> addDataColumn(Function<T, Long> function, String header, String key) {
        return get().addColumn(r -> new DigitalFormatter(function.apply(r)).toDataSize())
                .setComparator(Comparator.comparingLong(function::apply))
                .setKey(key).setHeader(header)
                ;
    }

    default Grid.Column<T> addDoubleColumn(Function<T, Double> function, String suffix, String header, String key) {
        return get().addColumn(r -> new DigitalFormatter(function.apply(r)).toFormatted() + " " + suffix)
                .setComparator(Comparator.comparingDouble(function::apply))
                .setKey(key).setHeader(header);
    }

    default Grid.Column<T> addDateTimeColumn(Function<T, LocalDateTime> function, String header, String key) {
        return get().addColumn(r -> Optional.ofNullable(function.apply(r))
                .map(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")::format).orElse(null))
                .setComparator(Comparator.comparingLong(r -> Optional.ofNullable(function.apply(r))
                        .map(t -> t.toEpochSecond(ZoneOffset.ofHours(8))).orElse(0L)))
                .setKey(key).setHeader(header);
    }

    default Grid.Column<T> addDateColumn(Function<T, LocalDate> function, String header, String key) {
        return get().addColumn(r -> Optional.ofNullable(function.apply(r))
                        .map(DateTimeFormatter.ofPattern("uuuu-MM-dd")::format).orElse(null))
                .setComparator(Comparator.comparingLong(r -> Optional.ofNullable(function.apply(r))
                        .map(LocalDate::toEpochDay).orElse(0L)))
                .setKey(key).setHeader(header);
    }

    default Grid.Column<T> addJavaUtilDateColumn(Function<T, Date> date, String header, String key) {
        return addDateTimeColumn(r -> Optional.ofNullable(date.apply(r)).map(d -> d.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null), header, key);
    }

    default Grid.Column<T> addTimeColumn(Function<T, LocalTime> function, String header, String key) {
        return get().addColumn(r -> Optional.ofNullable(function.apply(r))
                        .map(DateTimeFormatter.ofPattern("HH:mm:ss")::format).orElse(null))
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

    default Grid.Column<T> addBadgeTagBooleanGreenWhiteColumn(
            Function<T, Boolean> valueProvider, String trueValue, String falseValue) {
        return addBadgeTagBooleanColumn(valueProvider, trueValue, falseValue, BadgeTagStyle.GREEN, BadgeTagStyle.GREY);
    }

    default Grid.Column<T> addBadgeTagBooleanBlueWhiteColumn(
            Function<T, Boolean> valueProvider, String trueValue, String falseValue) {
        return addBadgeTagBooleanColumn(valueProvider, trueValue, falseValue, BadgeTagStyle.BLUE, BadgeTagStyle.GREY);
    }

    default Grid.Column<T> addBadgeTagBooleanBlueGreenColumn(
            Function<T, Boolean> valueProvider, String trueValue, String falseValue) {
        return addBadgeTagBooleanColumn(valueProvider, trueValue, falseValue, BadgeTagStyle.GREEN, BadgeTagStyle.BLUE);
    }

    default Grid.Column<T> addBadgeTagBooleanColumn(Function<T, Boolean> valueProvider,
                                                    String trueValue, String falseValue,
                                                    BadgeTagStyle trueStyle, BadgeTagStyle falseStyle) {
        return addBadgeTagColumn(
                t -> valueProvider.apply(t) ? trueValue : falseValue,
                t -> valueProvider.apply(t) ? trueStyle : falseStyle
        ).setComparator(Comparator.comparingInt(t -> valueProvider.apply(t) ? 1 : 0));
    }

    default Grid.Column<T> addBadgeTagColumn(Function<T, String> valueProvider, Function<T, BadgeTagStyle> styleProvider) {
        return get().addComponentColumn(t -> new BadgeTag(valueProvider.apply(t), styleProvider.apply(t)));
    }

    default GridFactory<T> asFactory() {
        return new GridFactory<>(get());
    }

    default GridFactory<T> initCommonGrid() {
        return asFactory().columnAutoWidth().columnAlignCenter().columnReorderingAllowed(true).sizeFull();
    }
}
