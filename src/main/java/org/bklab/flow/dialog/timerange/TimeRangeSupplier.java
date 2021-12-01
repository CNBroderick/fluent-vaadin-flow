/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-30 13:51:47
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.TimeRangeSupplier
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class TimeRangeSupplier implements ITimeRangeSupplier {

    private final Supplier<LocalDateTime> min;
    private final Supplier<LocalDateTime> max;
    private final String name;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TimeRangeSupplier(LocalDateTime min, LocalDateTime max) {
        this(() -> min, () -> max);
    }

    public TimeRangeSupplier(Supplier<LocalDateTime> min, Supplier<LocalDateTime> max) {
        this.name = "自定义";
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    public static ITimeRangeSupplier recently(String suffix, long duration, Function<LocalDateTime, LocalDateTime> function) {
        return new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return "最近" + duration + suffix;
            }

            @Override
            public LocalDateTime getMin() {
                return function.apply(LocalDateTime.now());
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.now();
            }
        };

    }

    public static ITimeRangeSupplier recently(String name, Duration duration) {
        return new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public LocalDateTime getMin() {
                return LocalDateTime.now().minus(duration);
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.now();
            }

            @Override
            public String getLabel() {
                return name;
            }
        };
    }

    public static ITimeRangeSupplier recently(String suffix, long duration) {
        return new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return "最近" + duration + suffix;
            }

            @Override
            public LocalDateTime getMin() {
                return switch (suffix) {
                    case "分钟" -> LocalDateTime.now().minusMinutes(duration);
                    case "小时" -> LocalDateTime.now().minusHours(duration);
                    case "天" -> LocalDateTime.now().minusDays(duration);
                    case "月" -> LocalDateTime.now().minusMonths(duration);
                    case "年" -> LocalDateTime.now().minusYears(duration);
                    default -> throw new IllegalStateException("Unexpected value: " + suffix);
                };
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.now();
            }
        };

    }

    public static ITimeRangeSupplier lastSeconds(long duration) {
        return recently("秒", duration, a -> a.minusSeconds(duration));
    }

    public static ITimeRangeSupplier lastMinutes(long duration) {
        return recently("分钟", duration);
    }

    public static ITimeRangeSupplier lastHours(long duration) {
        return recently("小时", duration);
    }

    public static ITimeRangeSupplier lastDays(long duration) {
        return recently("天", duration);
    }

    public static ITimeRangeSupplier lastMonths(long duration) {
        return recently("月", duration);
    }

    public static ITimeRangeSupplier lastYears(long duration) {
        return recently("年", duration, a -> a.minusYears(duration));
    }

    public static ITimeRangeSupplier appoint(LocalDateTime min, LocalDateTime max) {
        return new TimeRangeSupplier(min, max);
    }

    public static ITimeRangeSupplier appoint(LocalDateTime min, LocalDateTime max, String name, String label) {
        return new TimeRangeSupplier(min, max) {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getLabel() {
                return label;
            }
        };
    }

    public static ITimeRangeSupplier appoint(LocalDateTime min, LocalDateTime max, String label) {
        return new TimeRangeSupplier(min, max) {
            @Override
            public String getLabel() {
                return label;
            }
        };
    }

    public static ITimeRangeSupplier today() {
        return new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return "今天";
            }

            @Override
            public LocalDateTime getMin() {
                return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            }

            @Override
            public LocalDateTime getMax() {
                return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            }

        };
    }

    @Override
    public String getName() {
        return "自定义";
    }

    @Override
    public LocalDateTime getMin() {
        return min.get();
    }

    @Override
    public LocalDateTime getMax() {
        return max.get();
    }

    @Override
    public String getLabel() {
        LocalDateTime min = getMin();
        LocalDateTime max = getMax();
        if (min == null && max != null) return dateTimeFormatter.format(max) + " 以前";
        if (min != null && max == null) return dateTimeFormatter.format(min) + " 以后";
        if (min != null) return dateTimeFormatter.format(min) + " - " + dateTimeFormatter.format(max);
        return "全部时间";
    }
}
