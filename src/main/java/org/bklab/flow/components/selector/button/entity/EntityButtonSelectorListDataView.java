package org.bklab.flow.components.selector.button.entity;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.data.provider.*;
import com.vaadin.flow.function.SerializableComparator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.shared.Registration;
import org.bklab.flow.components.selector.button.SelectButton;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityButtonSelectorListDataView<T> implements ListDataView<T, EntityButtonSelectorListDataView<T>> {

    private final Map<T, SelectButton> buttonMap;
    private final List<T> entityList;
    private final EntityButtonSelector<T> entityButtonSelector;
    private final List<SerializablePredicate<T>> filters = new ArrayList<>();
    private final List<SerializableComparator<T>> sortComparators = new ArrayList<>();
    private final List<ComponentEventListener<ItemCountChangeEvent<?>>> itemCountChangeListeners = new ArrayList<>();
    private final Function<T, SelectButton> selectButtonCreator;
    private ListDataProvider<T> listDataProvider;
    private IdentifierProvider<T> identifierProvider;

    public EntityButtonSelectorListDataView(EntityButtonSelector<T> entityButtonSelector, Function<T, SelectButton> selectButtonCreator) {
        this.entityButtonSelector = entityButtonSelector;
        this.buttonMap = new LinkedHashMap<>();
        this.entityList = new ArrayList<>();
        this.listDataProvider = DataProvider.ofCollection(entityList);
        this.selectButtonCreator = selectButtonCreator;
    }

    public EntityButtonSelectorListDataView<T> setListDataProvider(ListDataProvider<T> dataProvider) {
        this.listDataProvider = dataProvider == null ? DataProvider.ofItems() : dataProvider;
        this.buttonMap.clear();
        this.entityList.clear();
        this.addItems(this.listDataProvider.getItems());
        return refreshButtonSelector();
    }

    public void refreshButtonText(@Nonnull Function<T, String> labelGenerator) {
        buttonMap.forEach((entity, button) -> button.setText(labelGenerator.apply(entity)));
    }

    public void clear() {
        setListDataProvider(DataProvider.ofCollection(new ArrayList<>()));
    }

    @Override
    public boolean contains(T item) {
        return buttonMap.containsKey(item);
    }

    @Override
    public int getItemCount() {
        return buttonMap.size();
    }

    @Override
    public Optional<T> getNextItem(T item) {
        if (entityList.isEmpty()) return Optional.empty();
        int index = entityList.indexOf(item);
        if (index < 0 || index >= entityList.size() - 2) return Optional.empty();
        return Optional.ofNullable(entityList.get(index + 1));
    }

    @Override
    public Optional<T> getPreviousItem(T item) {
        if (entityList.isEmpty()) return Optional.empty();
        int index = entityList.indexOf(item);
        if (index < 1) return Optional.empty();
        return Optional.ofNullable(entityList.get(index - 1));
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItem(T item) {
        SelectButton selectButton = selectButtonCreator.apply(item);
        this.entityList.add(item);
        this.listDataProvider.getItems().add(item);
        this.buttonMap.put(item, selectButton);
        this.entityButtonSelector.add(selectButton);
        return callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItemAfter(T item, T after) {
        int i = entityList.indexOf(after);
        if (i < 0 || i > entityList.size() - 1) return addItem(item);
        entityList.add(i + 1, item);
        buttonMap.put(item, selectButtonCreator.apply(item));
        return refreshButtonSelector().callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItemBefore(T item, T before) {
        int i = entityList.indexOf(before);
        if (i < 0 || i > entityList.size() - 1) return addItem(item);
        entityList.add(i, item);
        buttonMap.put(item, selectButtonCreator.apply(item));
        return refreshButtonSelector().callItemCountChangeListeners();
    }

    public EntityButtonSelectorListDataView<T> refreshButtonSelector() {
        Stream<T> stream = entityList.stream();

        if (!filters.isEmpty()) {
            for (SerializablePredicate<T> filter : filters) {
                stream = stream.filter(filter);
            }
        }

        if (!sortComparators.isEmpty()) {
            for (SerializableComparator<T> sortComparator : sortComparators) {
                stream = stream.sorted(sortComparator);
            }
        }

        List<T> entities = stream.collect(Collectors.toList());
        entityButtonSelector.removeAll();
        entities.stream().map(buttonMap::get).filter(Objects::nonNull).forEach(entityButtonSelector::add);
        listDataProvider = DataProvider.ofCollection(entities);
        return this;
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItems(Collection<T> items) {
        items.forEach(this::addItem);
        listDataProvider.getItems().addAll(items);
        callItemCountChangeListeners();
        return this;
    }

    private EntityButtonSelectorListDataView<T> callItemCountChangeListeners() {
        ItemCountChangeEvent<EntityButtonSelector<T>> event = new ItemCountChangeEvent<>(entityButtonSelector, entityList.size(), true);
        itemCountChangeListeners.forEach(componentEventListener -> componentEventListener.onComponentEvent(event));
        return this;
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItemsAfter(Collection<T> items, T after) {
        int i = entityList.indexOf(after);
        if (i < 0 || i > entityList.size() - 1) return addItems(items);
        entityList.addAll(i + 1, items);
        items.forEach(item -> buttonMap.put(item, selectButtonCreator.apply(item)));
        return refreshButtonSelector().callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> addItemsBefore(Collection<T> items, T before) {
        int i = entityList.indexOf(before);
        if (i < 0 || i > entityList.size() - 1) return addItems(items);
        entityList.addAll(i, items);
        items.forEach(item -> buttonMap.put(item, selectButtonCreator.apply(item)));
        return refreshButtonSelector().callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> removeItem(T item) {
        SelectButton selectButton = buttonMap.get(item);
        if (selectButton != null) entityButtonSelector.remove(selectButton);
        buttonMap.remove(item);
        entityList.remove(item);
        listDataProvider.getItems().remove(item);
        return callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> removeItems(Collection<T> items) {
        items.forEach(this::removeItem);
        return callItemCountChangeListeners();
    }

    @Override
    public EntityButtonSelectorListDataView<T> setFilter(SerializablePredicate<T> filter) {
        return removeFilters().addFilter(filter);
    }

    @Override
    public EntityButtonSelectorListDataView<T> addFilter(SerializablePredicate<T> filter) {
        if (filter != null) filters.add(filter);
        return this;
    }

    @Override
    public EntityButtonSelectorListDataView<T> removeFilters() {
        filters.clear();
        return this;
    }

    @Override
    public EntityButtonSelectorListDataView<T> setSortComparator(SerializableComparator<T> sortComparator) {
        return removeSorting().addSortComparator(sortComparator);
    }

    @Override
    public EntityButtonSelectorListDataView<T> addSortComparator(SerializableComparator<T> sortComparator) {
        if (sortComparator != null) this.sortComparators.add(sortComparator);
        return this;
    }

    @Override
    public EntityButtonSelectorListDataView<T> removeSorting() {
        this.sortComparators.clear();
        return this;
    }

    @Override
    public <V1 extends Comparable<? super V1>> EntityButtonSelectorListDataView<T> setSortOrder(ValueProvider<T, V1> valueProvider, SortDirection sortDirection) {
        return setSortComparator(InMemoryDataProviderHelpers.propertyComparator(valueProvider, sortDirection));
    }

    @Override
    public <V1 extends Comparable<? super V1>> EntityButtonSelectorListDataView<T> addSortOrder(ValueProvider<T, V1> valueProvider, SortDirection sortDirection) {
        return addSortComparator(InMemoryDataProviderHelpers.propertyComparator(valueProvider, sortDirection));
    }

    @Override
    public T getItem(int index) {
        return entityList.get(index);
    }

    @Override
    public Stream<T> getItems() {
        return entityList.stream();
    }

    @Override
    public void refreshItem(T item) {
        buttonMap.put(item, selectButtonCreator.apply(item));
        refreshButtonSelector();
    }

    @Override
    public void refreshAll() {
        refreshButtonSelector();
    }

    @Override
    public Registration addItemCountChangeListener(ComponentEventListener<ItemCountChangeEvent<?>> listener) {
        itemCountChangeListeners.add(listener);
        return () -> itemCountChangeListeners.remove(listener);
    }

    @Override
    public void setIdentifierProvider(IdentifierProvider<T> identifierProvider) {
        this.identifierProvider = identifierProvider;
    }

    public Map<T, SelectButton> getButtonMap() {
        return buttonMap;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public EntityButtonSelector<T> getEntityButtonSelector() {
        return entityButtonSelector;
    }
}
