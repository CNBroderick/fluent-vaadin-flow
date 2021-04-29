/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-27 13:42:30
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.button.CountdownButton
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.button;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.factory.NotificationFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class CountdownButton extends FluentButton implements HasSizeFactory<CountdownButton, CountdownButton> {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;
    private Function<Integer, String> labelRender = "%d秒"::formatted;
    private int interval = 60;
    private int remaining = -1;
    private int defaultPollInterval = -1;
    private String defaultText;
    private Component defaultIcon;
    private Consumer<Integer> notFinishConsumer = r -> new NotificationFactory("请%d秒后再试。".formatted(r)).positionTopEnd().lumoError().duration(1000).open();

    public CountdownButton(VaadinIcon icon, String text) {
        super(icon, text);
        this.defaultIcon = getIcon();
        this.defaultText = text;
    }

    public CountdownButton(VaadinIcon icon) {
        super(icon);
        this.defaultIcon = getIcon();
    }

    public CountdownButton(String text) {
        super(text);
        this.defaultText = text;
    }

    public CountdownButton() {
    }

    public boolean startCountdown() {
        if (remaining >= 0) {
            sendNotification(remaining);
            return false;
        }
        startFeederThread(UI.getCurrent());
        return true;
    }

    public boolean isFinished() {
        return remaining <= 0;
    }

    public CountdownButton setDefaultText(String defaultText) {
        this.defaultText = defaultText;
        return this;
    }

    public CountdownButton setDefaultIcon(VaadinIcon defaultIcon) {
        return setDefaultIcon(defaultIcon.create());
    }

    public CountdownButton setDefaultIcon(Component defaultIcon) {
        this.defaultIcon = getIcon();
        return this;
    }

    private void sendNotification(int remaining) {
        if (notFinishConsumer == null) return;
        notFinishConsumer.accept(remaining);
    }

    /**
     * 设置倒计时 小于等于0 为不启动。
     *
     * @param interval 倒计时 单位：秒。
     *
     * @return this
     */
    public CountdownButton setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    /**
     * @param labelRender 单位：秒。
     *
     * @return this
     */
    public CountdownButton setLabelRender(Function<Integer, String> labelRender) {
        this.labelRender = labelRender;
        return this;
    }

    /**
     * @param labelRenderPattern "pattern like %d sec".formatted(Integer remaining)
     *
     * @return this
     */
    public CountdownButton setLabelRender(String labelRenderPattern) {
        this.labelRender = labelRenderPattern::formatted;
        return this;
    }

    private void startFeederThread(UI ui) {
        this.remaining = this.interval;
        ui.setPollInterval(500);
        if (scheduledFuture != null) stopFeederThread(ui);
        setEnabled(false);
        setDisableOnClick(true);
        scheduledFuture = executor.scheduleAtFixedRate(() -> ui.access(() -> refresh(ui)), 0, 1, TimeUnit.SECONDS);
    }

    private void stopFeederThread(UI ui) {
        ui.setPollInterval(defaultPollInterval);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }

    private void refresh(UI ui) {
        if (remaining < 0) {
            stopFeederThread(ui);
            return;
        }

        if (remaining > 0) {
            remaining -= 1;
            setText(labelRender.apply(remaining));
            return;
        }

        setEnabled(true);
        setDisableOnClick(false);
        if (defaultText != null) setText(defaultText);
        if (defaultIcon != null) setIcon(defaultIcon);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        UI ui = attachEvent.getUI();
        this.defaultPollInterval = ui.getPollInterval();

        ui.addDetachListener(detach -> {
            if (scheduledFuture != null) scheduledFuture.cancel(true);
            detach.getUI().setPollInterval(defaultPollInterval);
        });

        ui.addBeforeLeaveListener(leaveEvent -> {
            if (scheduledFuture != null) scheduledFuture.cancel(true);
            leaveEvent.getUI().setPollInterval(defaultPollInterval);
        });
    }

    public CountdownButton setNotFinishConsumer(Consumer<Integer> notFinishConsumer) {
        this.notFinishConsumer = notFinishConsumer;
        return this;
    }

    @Override
    public CountdownButton get() {
        return this;
    }
}
