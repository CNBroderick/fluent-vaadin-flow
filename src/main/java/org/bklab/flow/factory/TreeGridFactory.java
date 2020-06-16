package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.treegrid.CollapseEvent;
import com.vaadin.flow.component.treegrid.ExpandEvent;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalDataProvider;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasHierarchicalDataProviderFactory;

import java.util.Collection;
import java.util.stream.Stream;

public class TreeGridFactory<T> extends FlowFactory<TreeGrid<T>, TreeGridFactory<T>> implements
        HasHierarchicalDataProviderFactory<T, TreeGrid<T>, TreeGridFactory<T>> {

    public TreeGridFactory() {
        this(new TreeGrid<>());
    }

    public TreeGridFactory(Class<T> beanType) {
        this(new TreeGrid<>(beanType));
    }

    public TreeGridFactory(HierarchicalDataProvider<T, ?> dataProvider) {
        this(new TreeGrid<>(dataProvider));
    }

    public TreeGridFactory(TreeGrid<T> tTreeGrid) {
        super(tTreeGrid);
    }

    @SafeVarargs
    public final TreeGridFactory<T> expand(T... expand) {
        get().expand(expand);
        return this;
    }

    public TreeGridFactory<T> expand(Collection<T> expand) {
        get().expand(expand);
        return this;
    }

    public TreeGridFactory<T> columns(String hierarchyPropertyName, ValueProvider<T, ?> valueProvider, Collection<String> propertyNames) {
        get().setColumns(hierarchyPropertyName, valueProvider, propertyNames);
        return this;
    }

    public TreeGridFactory<T> hierarchyColumn(String propertyName, ValueProvider<T, ?> valueProvider) {
        get().setHierarchyColumn(propertyName, valueProvider);
        return this;
    }

    public TreeGridFactory<T> hierarchyColumn(String hierarchyColumn) {
        get().setHierarchyColumn(hierarchyColumn);
        return this;
    }

    public TreeGridFactory<T> expandRecursively(Stream<T> items, int depth) {
        get().expandRecursively(items, depth);
        return this;
    }

    public TreeGridFactory<T> expandRecursively(Collection<T> items, int depth) {
        get().expandRecursively(items, depth);
        return this;
    }

    public TreeGridFactory<T> collapse(Collection<T> collapse) {
        get().collapse(collapse);
        return this;
    }

    @SafeVarargs
    public final TreeGridFactory<T> collapse(T... collapse) {
        get().collapse(collapse);
        return this;
    }

    public TreeGridFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public TreeGridFactory<T> expandListener(ComponentEventListener<ExpandEvent<T, TreeGrid<T>>> expandListener) {
        get().addExpandListener(expandListener);
        return this;
    }

    public TreeGridFactory<T> hierarchyColumn(ValueProvider<T, ?> hierarchyColumn) {
        get().addHierarchyColumn(hierarchyColumn);
        return this;
    }

    public TreeGridFactory<T> uniqueKeyDataGenerator(String propertyName, ValueProvider<T, String> uniqueKeyProvider) {
        get().setUniqueKeyDataGenerator(propertyName, uniqueKeyProvider);
        return this;
    }

    public TreeGridFactory<T> collapseListener(ComponentEventListener<CollapseEvent<T, TreeGrid<T>>> collapseListener) {
        get().addCollapseListener(collapseListener);
        return this;
    }

    public <V extends Component> TreeGridFactory<T> componentHierarchyColumn(ValueProvider<T, V> componentHierarchyColumn) {
        get().addComponentHierarchyColumn(componentHierarchyColumn);
        return this;
    }

    public TreeGridFactory<T> collapseRecursively(Stream<T> items, int depth) {
        get().collapseRecursively(items, depth);
        return this;
    }

    public TreeGridFactory<T> collapseRecursively(Collection<T> items, int depth) {
        get().collapseRecursively(items, depth);
        return this;
    }

}
