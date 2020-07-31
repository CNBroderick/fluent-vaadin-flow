package org.bklab.flow.convertor;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class StringToIntConverter implements Converter<String, Integer> {

    @Override
    public Result<Integer> convertToModel(String string, ValueContext valueContext) {
        try {
            return Result.ok(Integer.parseInt(string));
        } catch (Exception e) {
            return Result.error("请输入整数");
        }
    }

    @Override
    public String convertToPresentation(Integer integer, ValueContext valueContext) {
        return String.valueOf(integer);
    }
}

