package org.bklab.flow.convertor;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class DoubleToIntConverter implements Converter<Double, Integer> {

    @Override
    public Result<Integer> convertToModel(Double aDouble, ValueContext valueContext) {
        return Result.ok(aDouble == null ? 0 : aDouble.intValue());
    }

    @Override
    public Double convertToPresentation(Integer integer, ValueContext valueContext) {
        return integer == null ? 0d : integer.doubleValue();
    }
}

