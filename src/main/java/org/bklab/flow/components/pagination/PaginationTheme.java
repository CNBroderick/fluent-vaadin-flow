package org.bklab.flow.components.pagination;

import java.util.stream.Stream;

public enum PaginationTheme {
    TINY,
    NORMAL,
    ;

    public static PaginationTheme parse(String name) {
        return Stream.of(values()).filter(e -> e.name().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return name();
    }
}
