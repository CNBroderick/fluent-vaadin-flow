/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 09:10:37
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.dialog.crud.Buildable
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.crud;

import org.bklab.flow.base.HasReturnThis;

public interface Buildable<B extends Buildable<B>> extends HasReturnThis<B> {

    B build();
}
