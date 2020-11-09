package org.bklab.flow.components.time;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class TimeRangeSupplier implements ITimeRangeSupplier {

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

    public static ITimeRangeSupplier lastSeconds(long duration) {
        return recently("秒", duration, a -> a.minusSeconds(duration));
    }

    public static ITimeRangeSupplier lastMinutes(long duration) {
        return recently("分钟", duration, a -> a.minusMinutes(duration));
    }

    public static ITimeRangeSupplier lastHours(long duration) {
        return recently("小时", duration, a -> a.minusHours(duration));
    }

    public static ITimeRangeSupplier lastDays(long duration) {
        return recently("天", duration, a -> a.minusDays(duration));
    }

    public static ITimeRangeSupplier lastMonths(long duration) {
        return recently("月", duration, a -> a.minusMonths(duration));
    }

    public static ITimeRangeSupplier lastYears(long duration) {
        return recently("年", duration, a -> a.minusYears(duration));
    }

    public static ITimeRangeSupplier appoint(LocalDateTime min, LocalDateTime max) {
        return new TimeRangeSupplier(min, max);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        return formatter.format(getMin()) + " - " + formatter.format(getMax());
    }
}
