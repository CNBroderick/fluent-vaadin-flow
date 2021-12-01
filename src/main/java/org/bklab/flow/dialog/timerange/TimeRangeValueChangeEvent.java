/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-30 14:38:46
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.TimeRangeValueChangeEvent
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.HasValue;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public class TimeRangeValueChangeEvent implements HasValue.ValueChangeEvent<ITimeRangeSupplier> {

    private final HasValue<?, ITimeRangeSupplier> hasValue;
    private final boolean fromClient;
    private final ITimeRangeSupplier oldValue;
    private final ITimeRangeSupplier value;

    public TimeRangeValueChangeEvent(HasValue<?, ITimeRangeSupplier> hasValue, ITimeRangeSupplier value) {
        this(hasValue, false, value, value);
    }

    public TimeRangeValueChangeEvent(HasValue<?, ITimeRangeSupplier> hasValue, ITimeRangeSupplier oldValue, ITimeRangeSupplier value) {
        this(hasValue, false, oldValue, value);
    }

    public TimeRangeValueChangeEvent(HasValue<?, ITimeRangeSupplier> hasValue, boolean fromClient, ITimeRangeSupplier oldValue, ITimeRangeSupplier value) {
        this.hasValue = hasValue;
        this.fromClient = fromClient;
        this.oldValue = oldValue;
        this.value = value;
    }
}
