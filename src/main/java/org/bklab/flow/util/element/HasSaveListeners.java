package org.bklab.flow.util.element;

import org.bklab.flow.base.HasReturnThis;

import java.util.List;
import java.util.function.Consumer;

public interface HasSaveListeners<T, E extends HasSaveListeners<T, E>> extends HasReturnThis<E> {

//  private final List<Consumer<>> saveListeners = new ArrayList<>();

    default void callSaveListeners(T object) {
        getSaveListeners().forEach(s -> s.accept(object));
    }

    default E addSaveListeners(Consumer<T> saveListener) {
        getSaveListeners().add(saveListener);
        return thisObject();
    }

    default boolean removeSaveListeners(Consumer<T> saveListener) {
        return getSaveListeners().remove(saveListener);
    }

    List<Consumer<T>> getSaveListeners();

}
