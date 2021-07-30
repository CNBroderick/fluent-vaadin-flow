/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-06-04 13:32:40
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.exception.consumer.IFluentExceptionMessageRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.exception.consumer;


public interface IFluentExceptionMessageRender {

    String header();

    String[] className();

    String message(Exception exception);

    default String detail(Exception exception) {
        return message(exception);
    }

    default String[] classNames(String... classNames) {
        return classNames;
    }

    default String createHeader(int code, String message) {
        return code + " - " + message;
    }
}
