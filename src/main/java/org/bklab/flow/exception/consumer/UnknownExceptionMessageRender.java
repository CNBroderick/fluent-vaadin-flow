/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-06-05 14:27:48
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.exception.consumer.UnknownExceptionMessageRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.exception.consumer;

import java.util.Optional;

public class UnknownExceptionMessageRender implements IFluentExceptionMessageRender {
    @Override
    public String header() {
        return "未知错误";
    }

    @Override
    public String[] className() {
        return new String[0];
    }

    @Override
    public String message(Exception exception) {
        return Optional.ofNullable(exception.getMessage()).orElse("未知错误。");
    }
}
