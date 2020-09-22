package org.bklab.flow.util.function;

import java.util.function.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyFunctions {
    private static final Consumer EMPTY_CONSUMER = a -> {
    };
    private static final Function EMPTY_FUNCTION = a -> a;
    private static final Supplier EMPTY_SUPPLIER = () -> null;
    private static final BiConsumer EMPTY_BI_CONSUMER = (a, b) -> {
    };
    private static final BiFunction FIRST_BI_FUNCTION = (a, b) -> a;
    private static final BiFunction LAST_BI_FUNCTION = (a, b) -> b;

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) EMPTY_CONSUMER;
    }

    public static <T, R> Function<T, R> emptyFunction() {
        return (Function<T, R>) EMPTY_FUNCTION;
    }

    public static <T> Supplier<T> emptySupplier() {
        return (Supplier<T>) EMPTY_SUPPLIER;
    }

    public static <T, R> BiConsumer<T, R> emptyBiConsumer() {
        return (BiConsumer<T, R>) EMPTY_BI_CONSUMER;
    }

    public static <T, U, R> BiFunction<T, U, R> firstBiFunction() {
        return (BiFunction<T, U, R>) FIRST_BI_FUNCTION;
    }

    public static <T, U, R> BiFunction<T, U, R> lastBiFunction() {
        return (BiFunction<T, U, R>) LAST_BI_FUNCTION;
    }
}
