/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-03 14:00:46
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.GeneratedVaadinDatePickerFactory
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.datepicker.GeneratedVaadinDatePicker;
import org.bklab.flow.IFlowFactory;

import java.time.LocalDate;

public interface GeneratedVaadinDatePickerFactory<T extends LocalDate, C extends GeneratedVaadinDatePicker<C, T>, E extends GeneratedVaadinDatePickerFactory<T, C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<T, C, E>, HasStyleFactory<C, E>, FocusableFactory<C, E> {

}
