/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-06-18 11:44:54
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.util.url.RequestUrlParser
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.url;

import javax.servlet.http.HttpServletRequest;

public class RequestUrlParser {

    private final HttpServletRequest request;

    public RequestUrlParser(HttpServletRequest request) {
        this.request = request;
    }

    public String getURL() {

        String scheme = request.getScheme();             // http
        String serverName = request.getServerName();     // hostname.com
        int serverPort = request.getServerPort();        // 80
        String contextPath = request.getContextPath();   // /mywebapp
        String servletPath = request.getServletPath();   // /servlet/MyServlet
        String pathInfo = request.getPathInfo();         // /a/b;c=123
        String queryString = request.getQueryString();   // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }

}
