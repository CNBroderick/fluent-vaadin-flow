package org.bklab.flow.convertor;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Result<Double> convertToModel(String string, ValueContext valueContext) {
        try {
            return Result.ok(Double.parseDouble(string));
        } catch (Exception e) {
            return Result.error("请输入数字");
        }
    }

    @Override
    public String convertToPresentation(Double aDouble, ValueContext valueContext) {
        return String.valueOf(aDouble);
    }

}

