/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 13:56:19
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.util.time.range.IDateTimeRangePickerLimiter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.time.range;

import com.vaadin.flow.shared.Registration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IDateTimeRangePickerLimiter {

    Registration limit(Duration globalMaxDuration, LocalDateTime globalMinTime, LocalDateTime globalMaxTime);

    default Registration limit(Duration globalMaxDuration, LocalDate globalMinDate, LocalDate globalMaxDate) {
        return limit(globalMaxDuration, LocalDateTime.of(globalMinDate, LocalTime.MIN), LocalDateTime.of(globalMaxDate, LocalTime.MAX));
    }
}
