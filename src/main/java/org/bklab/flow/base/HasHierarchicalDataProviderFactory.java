package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.hierarchy.HasHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.flow.IFlowFactory;

import java.util.Collection;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public interface HasHierarchicalDataProviderFactory<
        T,
        C extends Component & HasHierarchicalDataProvider<T>,
        E extends HasHierarchicalDataProviderFactory<T, C, E>
        > extends IFlowFactory<C> {

    default E items(Collection<T> rootItems, ValueProvider<T, Collection<T>> childItemProvider) {
        get().setItems(rootItems, childItemProvider);
        return (E) this;
    }

    default E items(Stream<T> rootItems, ValueProvider<T, Stream<T>> childItemProvider) {
        get().setItems(rootItems, childItemProvider);
        return (E) this;
    }

    default E treeData(TreeData<T> treeData) {
        get().setTreeData(treeData);
        return (E) this;
    }
}
