/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.dialog.crud.AbstractReadModalDialog
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.crud;

import org.bklab.flow.dialog.ModalDialog;

public abstract class AbstractReadModalDialog<T, B extends AbstractReadModalDialog<T, B>> extends ModalDialog implements Buildable<B> {

    protected final T entity;

    protected AbstractReadModalDialog(T entity) {
        this.entity = entity;
    }

}
