package org.bklab.flow.dialog.timerange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class TimeRangeSupplier implements ITimeRangeSupplier {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Supplier<LocalDateTime> min;
    private final Supplier<LocalDateTime> max;
    private final String name;

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

    public static ITimeRangeSupplier recently(String suffix, long duration) {
        return new ITimeRangeSupplier() {
            @Override
            public String getName() {
                return "最近" + duration + suffix;
            }

            @Override
            public LocalDateTime getMin() {
                return switch (suffix) {
                    case "分钟" -> LocalDateTime.now().minusMinutes(duration).withSecond(0).withNano(0);
                    case "小时" -> LocalDateTime.now().minusHours(duration).withMinute(0).withSecond(0).withNano(0);
                    case "天" -> LocalDateTime.of(LocalDate.now().minusDays(duration), LocalTime.MIN);
                    case "月" -> LocalDateTime.of(LocalDate.now().minusMonths(duration).withDayOfMonth(1), LocalTime.MIN);
                    default -> throw new IllegalStateException("Unexpected value: " + suffix);
                };
            }

            @Override
            public LocalDateTime getMax() {
                return switch (suffix) {
                    case "分钟" -> LocalDateTime.now().withSecond(0).withNano(0).minusNanos(1);
                    case "小时" -> LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).minusNanos(1);
                    case "天" -> LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
                    case "月" -> LocalDateTime.of(LocalDate.now().minusMonths(1).withDayOfMonth(1), LocalTime.MIN);
                    default -> throw new IllegalStateException("Unexpected value: " + suffix);
                };
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
        return name;
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
        return dateTimeFormatter.format(getMin()) + " - " + dateTimeFormatter.format(getMax());
    }
}