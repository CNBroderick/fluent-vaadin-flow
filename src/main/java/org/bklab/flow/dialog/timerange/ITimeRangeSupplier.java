package org.bklab.flow.dialog.timerange;

import java.time.LocalDateTime;

public interface ITimeRangeSupplier {

    String getName();

    LocalDateTime getMin();

    LocalDateTime getMax();

    default String getLabel() {
        return getName();
    }
}
