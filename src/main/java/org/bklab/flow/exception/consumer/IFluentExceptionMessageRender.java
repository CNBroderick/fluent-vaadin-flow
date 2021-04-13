package org.bklab.flow.exception.consumer;


public interface IFluentExceptionMessageRender {

    String header();

    String[] className();

    String message(Exception exception);

    default String[] classNames(String... classNames) {
        return classNames;
    }

    default String createHeader(int code, String message) {
        return code + " - " + message;
    }
}
