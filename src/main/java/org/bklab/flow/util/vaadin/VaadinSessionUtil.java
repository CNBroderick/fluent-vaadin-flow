/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-13 16:12:09
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.vaadin.VaadinSessionUtil
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.vaadin;

import com.vaadin.flow.server.VaadinSession;

import java.util.function.Function;

public class VaadinSessionUtil {

    private final VaadinSession session;

    public VaadinSessionUtil(VaadinSession session) {
        this.session = session;
    }

    public static <T> T access(VaadinSession vaadinSession, Function<VaadinSession, T> function) {
        if (vaadinSession.hasLock()) return function.apply(vaadinSession);
        vaadinSession.lock();
        try {
            return function.apply(vaadinSession);
        } finally {
            vaadinSession.unlock();
        }
    }

    public Object getAttribute(String name) {
        return access(this.session, vaadinSession -> vaadinSession.getAttribute(name));
    }

    public <T> T getAttribute(Class<T> target) {
        return access(this.session, vaadinSession -> vaadinSession.getAttribute(target));
    }
}
