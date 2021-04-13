package org.bklab.flow.factory;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.GeneratedVaadinDialog;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasComponentsFactory;
import org.bklab.flow.base.HasSizeFactory;

public class DialogFactory extends FlowFactory<Dialog, DialogFactory>
        implements HasComponentsFactory<Dialog, DialogFactory>, HasSizeFactory<Dialog, DialogFactory> {

    public DialogFactory() {
        this(new Dialog());
    }

    public DialogFactory(Component... components) {
        this(new Dialog(components));
    }

    public DialogFactory(Dialog component) {
        super(component);
    }

    public DialogFactory add(Component... add) {
        get().add(add);
        return this;
    }

    public DialogFactory remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public DialogFactory close() {
        get().close();
        return this;
    }

    public DialogFactory open() {
        get().open();
        return this;
    }

    public DialogFactory removeAll() {
        get().removeAll();
        return this;
    }

    public DialogFactory attachListener(ComponentEventListener<AttachEvent> attachListener) {
        get().addAttachListener(attachListener);
        return this;
    }

    public DialogFactory detachListener(ComponentEventListener<DetachEvent> detachListener) {
        get().addDetachListener(detachListener);
        return this;
    }

    public DialogFactory height(String height) {
        get().setHeight(height);
        return this;
    }

    public DialogFactory width(String width) {
        get().setWidth(width);
        return this;
    }

    public DialogFactory opened(boolean opened) {
        get().setOpened(opened);
        return this;
    }

    public DialogFactory resizeListener(ComponentEventListener<Dialog.DialogResizeEvent> listener) {
        get().addResizeListener(listener);
        return this;
    }

    public DialogFactory modal(boolean modal) {
        get().setModal(modal);
        return this;
    }

    public DialogFactory resizable(boolean resizable) {
        get().setResizable(resizable);
        return this;
    }

    public DialogFactory closeOnEsc(boolean closeOnEsc) {
        get().setCloseOnEsc(closeOnEsc);
        return this;
    }

    public DialogFactory draggable(boolean draggable) {
        get().setDraggable(draggable);
        return this;
    }

    public DialogFactory closeOnOutsideClick(boolean closeOnOutsideClick) {
        get().setCloseOnOutsideClick(closeOnOutsideClick);
        return this;
    }

    public DialogFactory dialogCloseActionListener(ComponentEventListener<Dialog.DialogCloseActionEvent> listener) {
        get().addDialogCloseActionListener(listener);
        return this;
    }

    public DialogFactory componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return this;
    }

    public DialogFactory openedChangeListener(ComponentEventListener<GeneratedVaadinDialog.OpenedChangeEvent<Dialog>> listener) {
        get().addOpenedChangeListener(listener);
        return this;
    }
}
