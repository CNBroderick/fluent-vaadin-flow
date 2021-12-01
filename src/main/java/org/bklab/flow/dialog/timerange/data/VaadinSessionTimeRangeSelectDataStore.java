/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:45
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.data.VaadinSessionTimeRangeSelectDataStore
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange.data;

import com.vaadin.flow.server.VaadinSession;
import org.bklab.flow.dialog.timerange.ITimeRangeSupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VaadinSessionTimeRangeSelectDataStore implements ITimeRangeSelectDataStore {

    private final static String SESSION_KEY = "VAADIN_SESSION_TIME_RANGE_SELECT_DATA_STORE";
    private static final int MAX_KEEP_SIZE = 3;

    @Override
    public void add(ITimeRangeSupplier timeRangeSupplier) {
        List<ITimeRangeSupplier> suppliers = get();
        suppliers.add(0, timeRangeSupplier);
        if (suppliers.size() > MAX_KEEP_SIZE) {
            suppliers = suppliers.subList(0, 3);
        }
        VaadinSession.getCurrent().setAttribute(SESSION_KEY, suppliers);
    }

    @Override
    public void remove(ITimeRangeSupplier timeRangeSupplier) {
        List<ITimeRangeSupplier> suppliers = get();
        suppliers.remove(timeRangeSupplier);
        VaadinSession.getCurrent().setAttribute(SESSION_KEY, suppliers);
    }

    @Override
    public void remove(String timeRangeSupplierName) {
        List<ITimeRangeSupplier> suppliers = new ArrayList<>();
        for (ITimeRangeSupplier supplier : get()) {
            if (Objects.equals(timeRangeSupplierName, supplier.getName())) {
                suppliers.add(supplier);
            }
        }
        suppliers.forEach(this::remove);
    }

    @Override
    public List<ITimeRangeSupplier> get() {
        Object object = VaadinSession.getCurrent().getAttribute(SESSION_KEY);
        if (object instanceof Collection<?> collection) {
            return collection.stream()
                    .filter(ITimeRangeSupplier.class::isInstance)
                    .map(ITimeRangeSupplier.class::cast)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
