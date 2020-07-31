package org.bklab.flow.layout.select;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dnd.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.selection.MultiSelect;
import com.vaadin.flow.data.selection.MultiSelectionEvent;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import com.vaadin.flow.shared.Registration;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.TitleLayout;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.util.lumo.LumoStyles;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Tag("drag-select-layout")
public class DragSelectLayout<T> extends Div implements MultiSelect<DragSelectLayout<T>, T> {

    private final Div candidateContainer = new DivFactory().displayFlex().heightFull().get();
    private final Div selectedContainer = new DivFactory().displayFlex().heightFull().minWidth("150px").get();

    private final ToolBar candidateToolBar = new ToolBar();
    private final ToolBar selectedToolBar = new ToolBar();

    private final TitleLayout candidateLayout = new TitleLayout("请拖动选择").content(candidateContainer, candidateToolBar).heightFull();
    private final TitleLayout selectedLayout = new TitleLayout("已选择").content(selectedContainer, selectedToolBar).heightFull();

    private final Set<T> candidateItems = new LinkedHashSet<>();
    private final Set<T> selectedItems = new LinkedHashSet<>();

    private final Map<Component, T> candidateComponentMap = new LinkedHashMap<>();
    private final Map<Component, T> selectedComponentMap = new LinkedHashMap<>();
    private final List<MultiSelectionListener<DragSelectLayout<T>, T>> multiSelectionListeners = new ArrayList<>();
    private final List<ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<DragSelectLayout<T>, Set<T>>>> valueChangeListeners = new ArrayList<>();
    private final AtomicReference<Set<T>> oldValues = new AtomicReference<>(null);
    private Function<T, ? extends Component> componentRender = t -> new Button(String.valueOf(t));
    private EmptyLayout emptyLayout;

    {
        add(candidateLayout, selectedLayout);

        candidateLayout.getStyle().set("flex-grow", "8").set("min-width", "150px").set("width", "80%");
        selectedLayout.getStyle().set("flex-grow", "2").set("min-width", "150px").set("width", "20%").set("margin-left", "var(--lumo-space-m)");

        candidateLayout.getContent().getStyle().set("flex-wrap", "wrap");
        selectedLayout.getContent().getStyle().set("flex-wrap", "wrap");

        candidateContainer.getStyle().set("border", "1px solid #d9d9d9").set("flex-wrap", "wrap").set("min-height", "100px").set("padding", "var(--lumo-space-m)").set("overflow-y", "auto");
        selectedContainer.getStyle().set("border", "1px solid #d9d9d9").set("flex-wrap", "wrap").set("min-height", "100px").set("padding", "var(--lumo-space-m)").set("overflow-y", "auto");

        candidateContainer.addClassName(LumoStyles.Spacing.Right.M);
        selectedContainer.addClassName(LumoStyles.Spacing.Right.M);

        addClassName(LumoStyles.Spacing.Right.M);
        getElement().getStyle().set("display", "flex");
    }

    public DragSelectLayout() {

    }

    public DragSelectLayout<T> whenEmpty(String message) {
        emptyLayout = new EmptyLayout(message);
        selectedContainer.add(emptyLayout);
        emptyLayout.setVisible(selectedItems.isEmpty());

        addSelectionListener(event -> emptyLayout.setVisible(event.getValue().isEmpty()));

        return this;
    }

    private void createDragSupport(Component component, T instance) {
        DragSource<Component> dragSource = DragSource.create(component);
        dragSource.setEffectAllowed(EffectAllowed.MOVE);
        dragSource.setDragData(instance);
        dragSource.addDragStartListener(e -> e.setDragData(instance));
        dragSource.addDragEndListener(DragEndEvent::clearDragData);

        effectDropTarget(selectedContainer, candidateComponentMap, selectedComponentMap, candidateItems, selectedItems);
        effectDropTarget(candidateContainer, selectedComponentMap, candidateComponentMap, selectedItems, candidateItems);
    }

    private void effectDropTarget(Div container, Map<Component, T> sourceComponentMap,
                                  Map<Component, T> targetComponentMap, Set<T> sourceSet, Set<T> targetSet) {
        DropTarget<Div> candidateTarget = DropTarget.create(container);
        candidateTarget.setDropEffect(DropEffect.MOVE);
        candidateTarget.addDropListener(event -> {
            Component source = event.getDragSourceComponent().orElse(null);
            if (sourceComponentMap.containsKey(source)) {
                @SuppressWarnings("unchecked")
                T data = (T) event.getDragData().orElse(null);
                if (data == null) return;

                targetComponentMap.put(source, data);
                sourceComponentMap.remove(source);

                container.add(source);

                sourceSet.remove(data);
                targetSet.add(data);

                call(true);
            }
        });
    }

    private void call(boolean userOriginated) {
        MultiSelectionEvent<DragSelectLayout<T>, T> selectionEvent
                = new MultiSelectionEvent<>(this, this, getOldValues(), userOriginated);
        multiSelectionListeners.forEach(listener -> listener.selectionChange(selectionEvent));

        AbstractField.ComponentValueChangeEvent<DragSelectLayout<T>, Set<T>> changeEvent
                = new AbstractField.ComponentValueChangeEvent<>(this, this, getOldValues(), userOriginated);
        valueChangeListeners.forEach(listener -> listener.valueChanged(changeEvent));

        oldValues.set(Collections.unmodifiableSet(selectedItems));
    }

    public Set<T> getOldValues() {
        if (oldValues.get() == null) oldValues.set(Collections.unmodifiableSet(selectedItems));
        return oldValues.get();
    }

    public DragSelectLayout<T> componentRender(Function<T, ? extends Component> componentRender) {
        this.componentRender = componentRender;
        return this;
    }

    @SafeVarargs
    public final DragSelectLayout<T> items(T... candidateItems) {
        return items(Arrays.asList(candidateItems));
    }

    public DragSelectLayout<T> items(Collection<T> candidateItems) {
        clearCandidate();
        this.candidateItems.addAll(candidateItems);
        for (T instance : this.candidateItems) {
            Component c = componentRender.apply(instance);
            candidateComponentMap.put(c, instance);
            createDragSupport(c, instance);
            candidateContainer.add(c);
        }
        return this;
    }

    public void clearInstance() {
        candidateItems.clear();
        selectedItems.clear();
        candidateContainer.removeAll();
        selectedContainer.removeAll();
        candidateComponentMap.clear();
        selectedComponentMap.clear();

        call(false);
    }

    public void clearCandidate() {
        candidateItems.clear();
        candidateContainer.removeAll();
        candidateComponentMap.clear();
    }

    public void clearSelect() {
        selectedContainer.removeAll();

        candidateItems.addAll(selectedItems);
        candidateContainer.add(selectedComponentMap.keySet().toArray(new Component[]{}));
        candidateComponentMap.putAll(selectedComponentMap);

        selectedItems.clear();
        selectedComponentMap.clear();

        call(false);
    }

    @Override
    public void updateSelection(Set<T> addedItems, Set<T> removedItems) {
        if (addedItems != null) addedItems.forEach(this::select);
        if (removedItems != null) removedItems.forEach(this::deselect);
    }

    public void select(T instance) {
        doSelect(instance, candidateComponentMap, candidateContainer, candidateItems,
                selectedContainer, selectedItems, selectedComponentMap);
    }

    public void deselect(T instance) {
        doSelect(instance, selectedComponentMap, selectedContainer, selectedItems,
                candidateContainer, candidateItems, candidateComponentMap);
    }

    private void doSelect(T instance, Map<Component, T> sourceComponentMap, Div sourceContainer, Set<T> sourceItems,
                          Div targetContainer, Set<T> targetItems, Map<Component, T> targetComponentMap) {
        Component c = sourceComponentMap.keySet().stream().filter(component -> sourceComponentMap.get(component) == instance).findFirst().orElse(null);
        if (c == null) return;

        sourceContainer.remove(c);
        sourceItems.remove(instance);
        sourceComponentMap.remove(c);

        targetContainer.add(c);
        targetItems.add(instance);
        targetComponentMap.put(c, instance);


        call(false);
    }

    @Override
    public Set<T> getSelectedItems() {
        return Collections.unmodifiableSet(selectedItems);
    }

    @Override
    public Registration addSelectionListener(MultiSelectionListener<DragSelectLayout<T>, T> multiSelectionListener) {
        multiSelectionListeners.add(multiSelectionListener);
        return () -> multiSelectionListeners.remove(multiSelectionListener);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<DragSelectLayout<T>, Set<T>>> valueChangeListener) {
        valueChangeListeners.add(valueChangeListener);
        return () -> valueChangeListeners.remove(valueChangeListener);
    }

    public Div getCandidateContainer() {
        return candidateContainer;
    }

    public Div getSelectedContainer() {
        return selectedContainer;
    }

    public ToolBar getCandidateToolBar() {
        return candidateToolBar;
    }

    public ToolBar getSelectedToolBar() {
        return selectedToolBar;
    }

    public TitleLayout getCandidateLayout() {
        return candidateLayout;
    }

    public TitleLayout getSelectedLayout() {
        return selectedLayout;
    }

    public Set<T> getCandidateItems() {
        return candidateItems;
    }

    public DragSelectLayout<T> containerHeight(String height) {
        this.selectedContainer.setHeight(height);
        this.candidateContainer.setHeight(height);
        return this;
    }
}
