package org.bklab.flow.factory;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.notification.GeneratedVaadinNotification;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinNotificationFactory;
import org.bklab.flow.base.HasComponentsFactory;
import org.bklab.flow.base.HasThemeFactory;

public class NotificationFactory extends FlowFactory<Notification, NotificationFactory> implements
        GeneratedVaadinNotificationFactory<Notification, NotificationFactory>,
        HasComponentsFactory<Notification, NotificationFactory>,
        HasThemeFactory<Notification, NotificationFactory> {
    public NotificationFactory() {
        this(new Notification());
    }

    public NotificationFactory(Notification component) {
        super(component);
    }

    public NotificationFactory(String text) {
        this(new Notification(text));
    }

    public NotificationFactory(String text, int duration) {
        this(new Notification(text, duration));
    }

    public NotificationFactory(String text, int duration, Notification.Position position) {
        this(new Notification(text, duration, position));
    }

    public NotificationFactory(Component... components) {
        this(new Notification(components));
    }

    public NotificationFactory add(Component... add) {
        get().add(add);
        return this;
    }

    public NotificationFactory remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public NotificationFactory close() {
        get().close();
        return this;
    }

    public NotificationFactory open() {
        get().open();
        return this;
    }

    public NotificationFactory removeAll() {
        get().removeAll();
        return this;
    }

    public NotificationFactory text(String text) {
        get().setText(text);
        return this;
    }

    public NotificationFactory componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return this;
    }

    public NotificationFactory openedChangeListener(ComponentEventListener<GeneratedVaadinNotification.OpenedChangeEvent<Notification>> openedChangeListener) {
        get().addOpenedChangeListener(openedChangeListener);
        return this;
    }

    public NotificationFactory removeThemeVariants(NotificationVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return this;
    }

    public NotificationFactory themeVariants(NotificationVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return this;
    }

    public NotificationFactory duration(int duration) {
        get().setDuration(duration);
        return this;
    }

    public NotificationFactory attachListener(ComponentEventListener<AttachEvent> attachListener) {
        get().addAttachListener(attachListener);
        return this;
    }

    @SuppressWarnings("AccessStaticViaInstance")
    public NotificationFactory show(String text, int duration, Notification.Position position) {
        get().show(text, duration, position);
        return this;
    }

    @SuppressWarnings("AccessStaticViaInstance")
    public NotificationFactory show(String show) {
        get().show(show);
        return this;
    }

    public NotificationFactory position(Notification.Position position) {
        get().setPosition(position);
        return this;
    }

    public NotificationFactory opened(boolean opened) {
        get().setOpened(opened);
        return this;
    }

    public NotificationFactory detachListener(ComponentEventListener<DetachEvent> detachListener) {
        get().addDetachListener(detachListener);
        return this;
    }

    public NotificationFactory lumoPrimary() {
        get().addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        return this;
    }

    public NotificationFactory lumoContrast() {
        get().addThemeVariants(NotificationVariant.LUMO_CONTRAST);
        return this;
    }

    public NotificationFactory lumoSuccess() {
        get().addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        return this;
    }

    public NotificationFactory lumoError() {
        get().addThemeVariants(NotificationVariant.LUMO_ERROR);
        return this;
    }
}
