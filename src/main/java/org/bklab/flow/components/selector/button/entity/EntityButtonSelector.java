/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-21 11:26:34
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.selector.button.entity.EntityButtonSelector
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.selector.button.entity;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.HasListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.shared.Registration;
import dev.mett.vaadin.tooltip.Tooltips;
import org.bklab.flow.components.selector.button.ButtonSelector;
import org.bklab.flow.components.selector.button.SelectButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@CssImport("./styles/components/selector/button-selector.css")
public class EntityButtonSelector<T> extends Span implements
        HasValue<EntityButtonSelectorValueChangeEvent<EntityButtonSelector<T>, T>, T>,
        HasListDataView<T, EntityButtonSelectorListDataView<T>> {

    private final static String CLASS_NAME = "button-selector";
    private static final Logger logger = LoggerFactory.getLogger(ButtonSelector.class);
    private final List<ValueChangeListener<? super EntityButtonSelectorValueChangeEvent
            <EntityButtonSelector<T>, T>>> valueChangeListeners = new ArrayList<>();
    private Function<T, String> labelGenerator = String::valueOf;
    private Function<T, String> tooltipGenerator;
    private T value;
    private T oldValue;
    private final EntityButtonSelectorListDataView<T> listDataView = new EntityButtonSelectorListDataView<>(this, this::createSelectButton);
    private boolean readOnly;

    public EntityButtonSelector() {
        addClassName(CLASS_NAME + "__container");
    }

    public EntityButtonSelector<T> labelGenerator(@Nonnull Function<T, String> labelGenerator) {
        Objects.requireNonNull(labelGenerator);
        this.labelGenerator = labelGenerator;
        this.listDataView.refreshButtonText(labelGenerator);
        return this;
    }

    public EntityButtonSelector<T> labelGenerator(String nullLabel, @Nonnull Function<T, String> labelGenerator) {
        return labelGenerator(value -> value == null ? nullLabel : labelGenerator.apply(value));
    }

    public EntityButtonSelector<T> tooltipGenerator(Function<T, String> tooltipGenerator) {
        this.tooltipGenerator = tooltipGenerator;
        if (tooltipGenerator != null) {
            this.listDataView.getButtonMap().forEach((entity, button) -> Tooltips.getCurrent().setTooltip(button, tooltipGenerator.apply(entity)));
        } else {
            this.listDataView.getButtonMap().forEach((entity, button) -> Tooltips.getCurrent().removeTooltip(button));
        }
        return this;
    }

    public EntityButtonSelector<T> tooltipGenerator(String nullTooltip, @Nonnull Function<T, String> tooltipGenerator) {
        return tooltipGenerator(value -> value == null ? nullTooltip : tooltipGenerator.apply(value));
    }

    public Optional<SelectButton> get(T name) {
        return Optional.ofNullable(listDataView.getButtonMap().get(name));
    }

    @Override
    public void clear() {
        removeAll();
        listDataView.clear();
    }

    public EntityButtonSelector<T> clearEntity() {
        clear();
        return this;
    }

    @Deprecated
    public EntityButtonSelector<T> addSelectListeners(BiConsumer<T, ClickEvent<Button>> listener) {
        addValueChangeListener(event -> {
            T value = event.getValue();
            listener.accept(value, new ClickEvent<>(listDataView.getButtonMap().get(value)));
        });
        return this;
    }

    public EntityButtonSelector<T> addNull() {
        listDataView.addItem(null);
        return this;
    }

    @SafeVarargs
    public final EntityButtonSelector<T> add(T... entities) {
        for (T entity : entities) {
            listDataView.addItem(entity);
        }
        return this;
    }

    public EntityButtonSelector<T> add(Collection<T> entities) {
        listDataView.addItems(entities);
        return this;
    }

    private SelectButton createSelectButton(T entity) {
        SelectButton selectButton = new SelectButton();
        selectButton.setText(labelGenerator.apply(entity));
        selectButton.addClickListener(e -> {
            listDataView.getButtonMap().values().forEach(SelectButton::inactive);
            selectButton.active();
        });
        selectButton.addClickListener(event -> {
            this.oldValue = value;
            this.value = entity;
            EntityButtonSelectorValueChangeEvent<EntityButtonSelector<T>, T> changeEvent
                    = new EntityButtonSelectorValueChangeEvent<>(this, value, event.isFromClient(), oldValue);
            valueChangeListeners.forEach(valueChangeListener -> valueChangeListener.valueChanged(changeEvent));
        });
        if (tooltipGenerator != null) {
            Tooltips.getCurrent().setTooltip(selectButton, tooltipGenerator.apply(entity));
        }
        return selectButton;
    }

    public EntityButtonSelector<T> activeFirst() {
        this.listDataView.getEntityList().stream().findFirst().ifPresent(this::active);
        return this;
    }

    public EntityButtonSelector<T> active(T entity) {
        get(entity).ifPresent(selectButton -> {
            this.listDataView.getButtonMap().values().forEach(SelectButton::inactive);
            selectButton.active();
            selectButton.click();
        });
        return this;
    }

    /* HAS VALUE PART */

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        active(value);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super EntityButtonSelectorValueChangeEvent<EntityButtonSelector<T>, T>> listener) {
        valueChangeListeners.add(listener);
        return () -> valueChangeListeners.remove(listener);
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        this.listDataView.getButtonMap().values().forEach(b -> b.setDisableOnClick(readOnly));
        this.listDataView.getButtonMap().values().forEach(b -> b.setEnabled(!readOnly));
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        String message = getClass().getName() + " not support isRequiredIndicatorVisible()";
        LoggerFactory.getLogger(getClass()).error(message, new UnsupportedOperationException(message));
        return false;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        String message = getClass().getName() + " not support setRequiredIndicatorVisible(boolean requiredIndicatorVisible)";
        LoggerFactory.getLogger(getClass()).error(message, new UnsupportedOperationException(message));
    }

    /* HAS LIST DATA VIEW PART */

    @Override
    public EntityButtonSelectorListDataView<T> setItems(ListDataProvider<T> dataProvider) {
        clear();
        listDataView.addItems(dataProvider.getItems());
        return listDataView;
    }

    @Override
    public EntityButtonSelectorListDataView<T> setItems(Collection<T> items) {
        return setItems(DataProvider.ofCollection(items));
    }

    @SafeVarargs
    @Override
    public final EntityButtonSelectorListDataView<T> setItems(T... items) {
        listDataView.clear();
        listDataView.addItems(List.of(items));
        return listDataView;
    }

    @Override
    public EntityButtonSelectorListDataView<T> getListDataView() {
        return listDataView;
    }

    public EntityButtonSelectorFactory<T> asFactory() {
        return new EntityButtonSelectorFactory<>(this);
    }

}
