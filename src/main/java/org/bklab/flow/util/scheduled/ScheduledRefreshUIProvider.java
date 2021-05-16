/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-14 14:50:22
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.scheduled.ScheduledRefreshUIProvider
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.scheduled;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledRefreshUIProvider {

    private final Component component;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final List<ScheduledRefreshUIProviderRefreshUIListener> refreshUIListeners = new ArrayList<>();
    /**
     * 自动刷新时间(单位：毫秒)
     */
    private int interval;
    private int defaultPollInterval;
    private ScheduledFuture<?> scheduledFuture;
    private boolean refreshOnStart = false;

    public ScheduledRefreshUIProvider(Component component) {
        this(component, 1000);
    }

    public ScheduledRefreshUIProvider(Component component, int interval) {
        this.component = component;
        this.interval = interval;
    }

    public ScheduledRefreshUIProvider setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    public ScheduledRefreshUIProvider refreshOnStart(boolean refreshOnStart) {
        this.refreshOnStart = refreshOnStart;
        return this;
    }

    public void startFeederThread(UI ui) {
        ui.setPollInterval(interval / 2);
        if (scheduledFuture != null) stopFeederThread(ui);
        scheduledFuture = executor.scheduleAtFixedRate(() -> ui.access(() -> refresh(ui)), (refreshOnStart ? 0 : interval), interval, TimeUnit.MILLISECONDS);
    }

    public void stopFeederThread(UI ui) {
        ui.setPollInterval(defaultPollInterval);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }

    public ScheduledRefreshUIProvider build() {
        component.addDetachListener(detach -> {
            if (scheduledFuture != null) scheduledFuture.cancel(true);
            detach.getUI().setPollInterval(defaultPollInterval);
        });
        return this;
    }

    private synchronized void refresh(UI ui) {
        refreshUIListeners.forEach(ScheduledRefreshUIProviderRefreshUIListener::refresh);
    }

    public ScheduledRefreshUIProvider addRefreshUIListener(ScheduledRefreshUIProviderRefreshUIListener listener) {
        this.refreshUIListeners.add(listener);
        return this;
    }

    public interface ScheduledRefreshUIProviderRefreshUIListener {
        void refresh();
    }
}
