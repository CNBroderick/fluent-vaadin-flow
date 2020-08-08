package org.bklab.crud.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.function.ValueProvider;

import java.util.function.BiConsumer;

public class FluentColumnRender<T, C extends Component> {

    private final String id;
    private final String name;
    private final ValueProvider<T, C> componentProvider;
    private BiConsumer<T, C> writeComponentConsumer;
    private BiConsumer<T, C> writeEntityConsumer;
    private BiConsumer<Binder<T>, C> binderConsumer;

    public FluentColumnRender(String id, String name, ValueProvider<T, C> supplier) {
        this.id = id;
        this.name = name;
        this.componentProvider = supplier;
    }

    public FluentColumnRender<T, C> binderConsumer(BiConsumer<Binder<T>, C> binderConsumer) {
        this.binderConsumer = binderConsumer;
        return this;
    }

    public C createComponent(T entity) {
        return componentProvider.apply(entity);
    }

    public void writeEntity(T entity, Component c) {
        if (writeEntityConsumer != null)
            //noinspection unchecked
            writeEntityConsumer.accept(entity, (C) c);
    }

    public void writeComponent(T entity, Component c) {
        if (writeComponentConsumer != null)
            //noinspection unchecked
            writeComponentConsumer.accept(entity, (C) c);
    }

    public BiConsumer<Binder<T>, C> getBinderConsumer() {
        return binderConsumer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ValueProvider<T, C> getComponentProvider() {
        return componentProvider;
    }

    public BiConsumer<T, C> getWriteComponentConsumer() {
        return writeComponentConsumer;
    }

    public FluentColumnRender<T, C> setWriteComponentConsumer(BiConsumer<T, C> writeComponentConsumer) {
        this.writeComponentConsumer = writeComponentConsumer;
        return this;
    }

    public BiConsumer<T, C> getWriteEntityConsumer() {
        return writeEntityConsumer;
    }

    public FluentColumnRender<T, C> setWriteEntityConsumer(BiConsumer<T, C> writeEntityConsumer) {
        this.writeEntityConsumer = writeEntityConsumer;
        return this;
    }
}
