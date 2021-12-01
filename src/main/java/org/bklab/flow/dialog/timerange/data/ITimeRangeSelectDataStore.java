/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-11-30 15:21:21
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.data.ITimeRangeSelectDataStore
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange.data;

import org.bklab.flow.dialog.timerange.ITimeRangeSupplier;

import java.util.List;

public interface ITimeRangeSelectDataStore {

    void add(ITimeRangeSupplier timeRangeSupplier);

    void remove(ITimeRangeSupplier timeRangeSupplier);

    void remove(String timeRangeSupplierName);

    List<ITimeRangeSupplier> get();

}
