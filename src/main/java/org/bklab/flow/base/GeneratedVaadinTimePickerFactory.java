package org.bklab.flow.base;

import com.vaadin.flow.component.timepicker.GeneratedVaadinTimePicker;
import org.bklab.flow.IFlowFactory;

import java.time.LocalTime;

public interface GeneratedVaadinTimePickerFactory<C extends GeneratedVaadinTimePicker<C, LocalTime>, E extends GeneratedVaadinTimePickerFactory<C, E>> extends IFlowFactory<C>,
        AbstractSinglePropertyFieldFactory<LocalTime, C, E>,
        HasStyleFactory<C, E>,
        FocusableFactory<C, E> {


}
