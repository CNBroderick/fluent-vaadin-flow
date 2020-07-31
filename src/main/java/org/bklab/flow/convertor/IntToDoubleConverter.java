package org.bklab.flow.convertor;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class IntToDoubleConverter implements Converter<Integer, Double> {

    @Override
    public Result<Double> convertToModel(Integer integer, ValueContext valueContext) {
        return Result.ok(integer == null ? 0 : integer.doubleValue());
    }

    @Override
    public Integer convertToPresentation(Double aDouble, ValueContext valueContext) {
        return aDouble == null ? 0 : aDouble.intValue();
    }
}
