package org.bklab.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

class FluentCrudMenuButton<T> {

    private final Button button;
    private final ContextMenu contextMenu;
    private final BiConsumer<ContextMenu, T> menuEntityBiConsumer;
    private T entity;

    public FluentCrudMenuButton(T entity, Button button, ContextMenu contextMenu,
                                BiConsumer<ContextMenu, T> menuEntityBiConsumer) {
        this.entity = entity;
        this.button = button;
        this.contextMenu = contextMenu;
        this.menuEntityBiConsumer = menuEntityBiConsumer;
    }

    public void reload(T entity) {
        this.reload(entity, t -> Objects.equals(t, entity));
    }

    public void reload(T entity, Predicate<T> sameFunction) {
        if (!sameFunction.test(entity)) return;
        this.entity = entity;
        contextMenu.removeAll();
        menuEntityBiConsumer.accept(contextMenu, entity);
    }

    public boolean isThisEntity(T entity, BiPredicate<T, T> sameFunction) {
        return sameFunction == null
                ? Objects.equals(entity, this.entity)
                : sameFunction.test(entity, this.entity);
    }

}
