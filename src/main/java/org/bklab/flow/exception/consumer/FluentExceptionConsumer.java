/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-28 09:52:58
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.exception.consumer.FluentExceptionConsumer
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.exception.consumer;

import org.bklab.flow.dialog.ErrorDialog;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

public class FluentExceptionConsumer implements Consumer<Exception> {

    protected static final List<IFluentExceptionMessageRender> renders = new ArrayList<>(FluentExceptionMessageRender.exceptionMessageRenders);

    public static void addMessageRender(IFluentExceptionMessageRender... exceptionMessageRenders) {
        Collections.addAll(renders, exceptionMessageRenders);
    }

    @Override
    public void accept(Exception exception) {
        IFluentExceptionMessageRender messageRender = findMessageRender(exception);

        if (messageRender != null) {
            String message = messageRender.message(exception);
            new ErrorDialog(message, exception).header(messageRender.header()).build().open();
            LoggerFactory.getLogger(getExceptionClass(exception)).error(message, exception);
            return;
        }

        new ErrorDialog(Objects.toString(exception.getMessage(), "未知错误"), exception).build().open();
        LoggerFactory.getLogger(getExceptionClass(exception)).error("错误", exception);
    }

    public IFluentExceptionMessageRender findMessageRender(Exception exception) {
        String exceptionClassName = exception.getClass().getName();
        return renders.stream()
                .filter(render -> Arrays.stream(render.className()).anyMatch(exceptionClassName::endsWith))
                .findFirst().orElse(null);
    }

    private Class<?> getExceptionClass(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        if (stackTrace.length > 2) return stackTrace[2].getClass();
        if (stackTrace.length > 0) return stackTrace[0].getClass();
        return getClass();
    }


}
