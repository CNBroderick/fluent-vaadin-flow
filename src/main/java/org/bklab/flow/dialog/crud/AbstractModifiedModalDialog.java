/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.dialog.crud.AbstractModifiedModalDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.crud;

import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.util.element.HasSaveListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractModifiedModalDialog<T, B extends AbstractModifiedModalDialog<T, B>>
        extends ModalDialog implements Buildable<B>, HasSaveListeners<T, B> {

    protected final T entity;
    protected final boolean updateMode;
    private final List<Consumer<T>> saveListeners = new ArrayList<>();

    protected AbstractModifiedModalDialog(T entity, boolean updateMode) {
        this.entity = entity;
        this.updateMode = updateMode;
    }

    @Override
    public List<Consumer<T>> getSaveListeners() {
        return saveListeners;
    }
}
