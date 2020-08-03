package org.bklab.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

class FluentCrudMenuButton<T, G extends Grid<T>> {

    private final Button button;
    private final ContextMenu contextMenu;
    private final IFluentMenuBuilder<T, G> menuEntityBiConsumer;
    private final FluentCrudView<T, G> fluentCrudView;

    private T entity;

    public FluentCrudMenuButton(FluentCrudView<T, G> fluentCrudView,
                                T entity, Button button, ContextMenu contextMenu,
                                IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        this.fluentCrudView = fluentCrudView;
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
        if (menuEntityBiConsumer != null) menuEntityBiConsumer.safeBuild(fluentCrudView, contextMenu, entity);
    }

    public boolean isThisEntity(T entity, BiPredicate<T, T> sameFunction) {
        return sameFunction == null
                ? Objects.equals(entity, this.entity)
                : sameFunction.test(entity, this.entity);
    }

}
