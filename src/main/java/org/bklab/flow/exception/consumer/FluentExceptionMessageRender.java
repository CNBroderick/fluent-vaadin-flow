/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-28 09:52:58
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.exception.consumer.FluentExceptionMessageRender
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.exception.consumer;

import org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class FluentExceptionMessageRender {

    public static final List<IFluentExceptionMessageRender> exceptionMessageRenders = Arrays.asList(
            new SC_REQUEST_TIMEOUT(),
            new SC_REQUEST_TIMEOUT_DB(),
            new SC_REQUEST_TIMEOUT_DB1()
    );

    private static class SC_REQUEST_TIMEOUT implements IFluentExceptionMessageRender {

        @Override
        public String header() {
            return createHeader(HttpStatus.SC_REQUEST_TIMEOUT, " - 连接超时");
        }

        @Override
        public String[] className() {
            return classNames(
                    java.net.http.HttpTimeoutException.class.getName(),
                    java.net.ConnectException.class.getName()
            );
        }

        @Override
        public String message(Exception exception) {
            return "系统无法连接到指定服务器，请联系系统管理员。";
        }
    }

    private static class SC_REQUEST_TIMEOUT_DB implements IFluentExceptionMessageRender {

        @Override
        public String header() {
            return createHeader(HttpStatus.SC_REQUEST_TIMEOUT, " - 连接超时");
        }

        @Override
        public String[] className() {
            return classNames(
                    "com.mysql.cj.jdbc.exceptions.SQLError.createCommunicationsException"
            );
        }

        @Override
        public String message(Exception exception) {
            return "系统无法连接到数据库服务器超时，请联系系统管理员。";
        }
    }

    private static class SC_REQUEST_TIMEOUT_DB1 implements IFluentExceptionMessageRender {

        @Override
        public String header() {
            return createHeader(HttpStatus.SC_REQUEST_TIMEOUT, " - 连接超时");
        }

        @Override
        public String[] className() {
            return classNames(
                    "com.alibaba.druid.pool.GetConnectionTimeoutException"
            );
        }

        @Override
        public String message(Exception exception) {
            return "与数据库链接超时，请稍后重试。";
        }
    }
}
