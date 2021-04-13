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
        String exceptionClassName = exception.getClass().getName();

        IFluentExceptionMessageRender messageRender = renders.stream()
                .filter(render -> Arrays.stream(render.className()).anyMatch(exceptionClassName::endsWith))
                .findFirst().orElse(null);

        if (messageRender != null) {
            String message = messageRender.message(exception);
            new ErrorDialog(message, exception).header(messageRender.header()).build().open();
            LoggerFactory.getLogger(getExceptionClass(exception)).error(message, exception);
            return;
        }

        new ErrorDialog(Objects.toString(exception.getMessage(), "未知错误"), exception).build().open();
        LoggerFactory.getLogger(getExceptionClass(exception)).error("错误", exception);
    }

    private Class<?> getExceptionClass(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        if (stackTrace.length > 2) return stackTrace[2].getClass();
        if (stackTrace.length > 0) return stackTrace[0].getClass();
        return getClass();
    }
}
