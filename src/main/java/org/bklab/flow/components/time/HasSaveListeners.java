package org.bklab.flow.components.time;

import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public interface HasSaveListeners<T, E extends HasSaveListeners<T, E>> {

//  private final List<Consumer<>> saveListeners = new ArrayList<>();

    default void callSaveListeners(T object) {
        getSaveListeners().forEach(s -> s.accept(object));
    }

    default E addSaveListeners(Consumer<T> saveListener) {
        getSaveListeners().add(saveListener);
        return (E) this;
    }

    default boolean removeSaveListeners(Consumer<T> saveListener) {
        return getSaveListeners().remove(saveListener);
    }

    List<Consumer<T>> getSaveListeners();

}
