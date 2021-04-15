/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-14 15:05:00
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.ClickNotifierFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.*;
import com.vaadin.flow.function.SerializableConsumer;
import org.bklab.flow.IFlowFactory;
import org.bklab.flow.text.ClipboardHelper;

@SuppressWarnings("unchecked")
public interface ClickNotifierFactory<T extends Component & ClickNotifier<T>, E extends ClickNotifierFactory<T, E>> extends IFlowFactory<T> {

    default E clickListener(ComponentEventListener<ClickEvent<T>> listener) {
        get().addClickListener(listener);
        return (E) this;
    }

    default E clickShortcut(Key key, KeyModifier... keyModifiers) {
        get().addClickShortcut(key, keyModifiers);
        return (E) this;
    }

    default E clipboard(String content) {
        ClipboardHelper.getInstance().extend(get(), content);
        return (E) this;
    }

    default E clipboard(String content, SerializableConsumer<ClipboardHelper.OnClipboardResult> clipboardResultConsumer) {
        ClipboardHelper.getInstance().extend(get(), content, clipboardResultConsumer);
        return (E) this;
    }

    default E clipboard(String content, String title) {
        ClipboardHelper.getInstance().extend(get(), content, title);
        return (E) this;
    }

    default E clipboard(String content, String title, SerializableConsumer<ClipboardHelper.OnClipboardResult> clipboardResultConsumer) {
        ClipboardHelper.getInstance().extend(get(), content, title, clipboardResultConsumer);
        return (E) this;
    }
}
