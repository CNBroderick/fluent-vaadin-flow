package org.bklab.flow.components.selector.button.entity;

import com.vaadin.flow.component.HasValue;

public class EntityButtonSelectorValueChangeEvent<COMPONENT extends HasValue<?, ENTITY>, ENTITY>
        implements HasValue.ValueChangeEvent<ENTITY> {

    private final COMPONENT hasValue;
    private final ENTITY value;
    private final boolean fromClient;
    private final ENTITY oldValue;

    public EntityButtonSelectorValueChangeEvent(COMPONENT hasValue, ENTITY value) {
        this(hasValue, value, true, value);
    }

    public EntityButtonSelectorValueChangeEvent(COMPONENT hasValue, ENTITY value, boolean fromClient) {
        this(hasValue, value, true, value);
    }

    public EntityButtonSelectorValueChangeEvent(COMPONENT hasValue, ENTITY value, boolean fromClient, ENTITY oldValue) {
        this.hasValue = hasValue;
        this.value = value;
        this.fromClient = fromClient;
        this.oldValue = oldValue;
    }

    @Override
    public COMPONENT getHasValue() {
        return hasValue;
    }

    @Override
    public boolean isFromClient() {
        return fromClient;
    }

    @Override
    public ENTITY getOldValue() {
        return oldValue;
    }

    @Override
    public ENTITY getValue() {
        return value;
    }
}
