package org.bklab.crud.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FluentGridItem<T, C extends Component> {
    private static final Logger logger = LoggerFactory.getLogger(FluentGridItem.class);
    private final T entity;
    private final C component;
    private final FluentColumnRender<T, C> render;

    public FluentGridItem(T entity, C component, FluentColumnRender<T, C> render) {
        this.entity = entity;
        this.component = component;
        this.render = render;
    }

    public static <T, C extends Component> C registered(
            T entity,
            FluentColumnRender<T, C> render,
            List<FluentGridItem<T, ?>> container,
            Map<T, Map<FluentColumnRender<T, ?>, FluentGridItem<T, ?>>> map,
            Map<T, Binder<T>> binderMap
    ) {
        C c = render.createComponent(entity);
        FluentGridItem<T, C> fluentGridItem = new FluentGridItem<>(entity, c, render);
        container.add(fluentGridItem);
        map.computeIfAbsent(entity, k -> new LinkedHashMap<>()).put(render, fluentGridItem);
        Optional.ofNullable(render.getBinderConsumer())
                .flatMap(binderConsumer -> Optional.ofNullable(render.getBinderConsumer()))
                .ifPresent(b -> b.accept(binderMap.computeIfAbsent(entity, k -> new Binder<>()), c));
        return c;
    }

    public void writeComponent() {
        render.writeComponent(entity, component);
    }

    public void writeEntity() {
        render.writeEntity(entity, component);
    }

    public boolean match(T entity) {
        return this.entity == entity;
    }

    public boolean match(T entity, FluentColumnRender<T, ?> render) {
        return this.entity == entity && this.render == render;
    }

    public C getComponent() {
        return component;
    }
}
