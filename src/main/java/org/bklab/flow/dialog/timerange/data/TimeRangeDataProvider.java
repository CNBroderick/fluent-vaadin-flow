/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:45
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.data.TimeRangeDataProvider
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange.data;

import org.bklab.flow.dialog.timerange.ITimeRangeSupplier;
import org.bklab.flow.dialog.timerange.TimeRangeSupplier;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public record TimeRangeDataProvider(List<ITimeRangeSupplier> relativeTimeRanges,
                                    List<ITimeRangeSupplier> absoluteTimeRanges,
                                    ITimeRangeSelectDataStore dataStore) {

    private static TimeRangeDataProvider DEFAULT_PROVIDER;

    public static TimeRangeDataProvider getDefaultProvider() {
        if (DEFAULT_PROVIDER == null) {
            DEFAULT_PROVIDER = new TimeRangeDataProvider(new ArrayList<>(), new ArrayList<>(), new VaadinSessionTimeRangeSelectDataStore());
            addDefaultAbsoluteTimes(DEFAULT_PROVIDER.absoluteTimeRanges);
            addDefaultRelativeTimes(DEFAULT_PROVIDER.relativeTimeRanges);
        }
        return DEFAULT_PROVIDER;
    }

    public static void addDefaultAbsoluteTimes(List<ITimeRangeSupplier> absoluteTimeRanges) {
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX), "今天", "今天"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN), LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX), "昨天", "昨天"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MIN), LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MAX), "前天", "前天"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)), LocalTime.MAX),
                "本周", "本周"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX),
                "本月", "本月"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().withMonth(LocalDate.now().getMonth().firstMonthOfQuarter().getValue()).withDayOfMonth(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().withMonth(LocalDate.now().getMonth().firstMonthOfQuarter().getValue()).plusMonths(3).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX),
                "本季度", "本季度"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().withDayOfYear(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX),
                "本年度", "本年度"));

        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusWeeks(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).minusWeeks(1), LocalTime.MAX),
                "上周", "上周"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().minusMonths(1).withDayOfMonth(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(1), LocalTime.MAX),
                "上月", "上月"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().withMonth(LocalDate.now().getMonth().firstMonthOfQuarter().getValue()).minusMonths(3).withDayOfMonth(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().withMonth(LocalDate.now().getMonth().firstMonthOfQuarter().getValue()).minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX),
                "上季度", "上季度"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().minusYears(1).withDayOfYear(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().minusYears(1).with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX),
                "去年", "去年"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().minusYears(2).withDayOfYear(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().minusYears(2).with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX),
                "前年", "前年"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().minusYears(2).withDayOfYear(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX),
                "本3年", "本3年"));
        absoluteTimeRanges.add(TimeRangeSupplier.appoint(
                LocalDateTime.of(LocalDate.now().minusYears(4).withDayOfYear(1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX),
                "本5年", "本5年"));
    }

    public static void addDefaultRelativeTimes(List<ITimeRangeSupplier> relativeTimeRanges) {
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近5分钟", Duration.ofMinutes(5)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近10分钟", Duration.ofMinutes(10)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近15分钟", Duration.ofMinutes(15)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近20分钟", Duration.ofMinutes(20)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近30分钟", Duration.ofMinutes(30)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近45分钟", Duration.ofMinutes(45)));

        relativeTimeRanges.add(TimeRangeSupplier.recently("最近1小时", Duration.ofHours(1)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近2小时", Duration.ofHours(2)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近3小时", Duration.ofHours(3)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近6小时", Duration.ofHours(6)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近12小时", Duration.ofHours(12)));

        relativeTimeRanges.add(TimeRangeSupplier.recently("最近1天", Duration.ofDays(1)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近2天", Duration.ofDays(2)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近3天", Duration.ofDays(3)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近7天", Duration.ofDays(7)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近15天", Duration.ofDays(15)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近30天", Duration.ofDays(30)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近90天", Duration.ofDays(90)));
        relativeTimeRanges.add(TimeRangeSupplier.recently("最近180天", Duration.ofDays(180)));

        relativeTimeRanges.add(TimeRangeSupplier.recently("月", 1));
        relativeTimeRanges.add(TimeRangeSupplier.recently("月", 3));
        relativeTimeRanges.add(TimeRangeSupplier.recently("月", 6));
        relativeTimeRanges.add(TimeRangeSupplier.recently("月", 12));

        relativeTimeRanges.add(TimeRangeSupplier.recently("年", 1));
        relativeTimeRanges.add(TimeRangeSupplier.recently("年", 2));
        relativeTimeRanges.add(TimeRangeSupplier.recently("年", 3));
        relativeTimeRanges.add(TimeRangeSupplier.recently("年", 5));

    }

    public TimeRangeDataProvider addToAbsolute(ITimeRangeSupplier timeRange) {
        absoluteTimeRanges.add(timeRange);
        return this;
    }

    public TimeRangeDataProvider addToRelative(ITimeRangeSupplier timeRange) {
        relativeTimeRanges.add(timeRange);
        return this;
    }

    public TimeRangeDataProvider addToStore(ITimeRangeSupplier timeRange) {
        dataStore.add(timeRange);
        return this;
    }

    public List<ITimeRangeSupplier> getStoreCache() {
        return dataStore.get();
    }
}
