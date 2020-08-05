/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-06 09:27:45
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：building.ui.view.sms.ChineseDatePickerI18n
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.time;

import com.vaadin.flow.component.datepicker.DatePicker;

import java.util.List;

public class ChineseDatePickerI18n extends DatePicker.DatePickerI18n {

    private final static ChineseDatePickerI18n instance = new ChineseDatePickerI18n();

    public ChineseDatePickerI18n() {
        setMonthNames(List.of("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"));
        setWeekdays(List.of("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"));
        setWeekdaysShort(List.of("一", "二", "三", "四", "五", "六", "日"));
        setFirstDayOfWeek(0);
        setWeek("星期");
        setCalendar("日历");
        setClear("清除");
        setToday("今天");
        setCancel("取消");
    }

    public static ChineseDatePickerI18n getInstance() {
        return instance;
    }
}
