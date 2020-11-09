package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.data.SelectDataView;
import com.vaadin.flow.component.select.data.SelectListDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializablePredicate;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.*;

public class SelectFactory<T> extends FlowFactory<Select<T>, SelectFactory<T>> implements
        GeneratedVaadinSelectFactory<T, Select<T>, SelectFactory<T>>,
        HasSizeFactory<Select<T>, SelectFactory<T>>,
        HasListDataViewFactory<T, SelectListDataView<T>, Select<T>, SelectFactory<T>>,
        HasDataViewFactory<T, Void, SelectDataView<T>, Select<T>, SelectFactory<T>>,
        HasValidationFactory<Select<T>, SelectFactory<T>>,
        SingleSelectFactory<T, Select<T>, SelectFactory<T>> {

    public SelectFactory() {
        this(new Select<>());
    }

    @SafeVarargs
    public SelectFactory(T... items) {
        this(new Select<>(items));
    }

    public SelectFactory(Select<T> component) {
        super(component);
    }

    @Override
    public SelectFactory<T> value(T value) {
        get().setValue(value);
        return this;
    }

    @Override
    public SelectFactory<T> valueChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<Select<T>, T>> valueChangeListener) {
        get().addValueChangeListener(valueChangeListener);
        return this;
    }

    public SelectFactory<T> add(Component... add) {
        get().add(add);
        return this;
    }

    public SelectFactory<T> remove(Component... remove) {
        get().remove(remove);
        return this;
    }

    public SelectFactory<T> removeAll() {
        get().removeAll();
        return this;
    }

    public SelectFactory<T> emptySelectionCaption(String emptySelectionCaption) {
        get().setEmptySelectionCaption(emptySelectionCaption);
        return this;
    }

    public SelectFactory<T> itemEnabledProvider(SerializablePredicate<T> itemEnabledProvider) {
        get().setItemEnabledProvider(itemEnabledProvider);
        return this;
    }

    public SelectFactory<T> emptySelectionAllowed(boolean emptySelectionAllowed) {
        get().setEmptySelectionAllowed(emptySelectionAllowed);
        return this;
    }

    public SelectFactory<T> itemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        get().setItemLabelGenerator(i -> i == null ? get().getEmptySelectionCaption() : itemLabelGenerator.apply(i));
        return this;
    }

    public SelectFactory<T> itemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator, String DefaultValue) {
        get().setItemLabelGenerator(i -> i == null ? DefaultValue : itemLabelGenerator.apply(i));
        return this;
    }

    public SelectFactory<T> componentAtIndex(int index, Component component) {
        get().addComponentAtIndex(index, component);
        return this;
    }

    public SelectFactory<T> componentAsFirst(Component componentAsFirst) {
        get().addComponentAsFirst(componentAsFirst);
        return this;
    }

    public SelectFactory<T> prependComponents(T beforeItem, Component... components) {
        get().prependComponents(beforeItem, components);
        return this;
    }

    @Deprecated
    public SelectFactory<T> dataProvider(DataProvider<T, ?> dataProvider) {
        get().setDataProvider(dataProvider);
        return this;
    }

    public SelectFactory<T> components(T afterItem, Component... components) {
        get().addComponents(afterItem, components);
        return this;
    }

    public SelectFactory<T> errorMessage(String errorMessage) {
        get().setErrorMessage(errorMessage);
        return this;
    }

    public SelectFactory<T> invalid(boolean invalid) {
        get().setInvalid(invalid);
        return this;
    }

    public SelectFactory<T> requiredIndicatorVisible(boolean requiredIndicatorVisible) {
        get().setRequiredIndicatorVisible(requiredIndicatorVisible);
        return this;
    }

    public SelectFactory<T> placeholder(String placeholder) {
        get().setPlaceholder(placeholder);
        return this;
    }

    public SelectFactory<T> renderer(ComponentRenderer<? extends Component, T> renderer) {
        get().setRenderer(renderer);
        return this;
    }

    public SelectFactory<T> autofocus(boolean autofocus) {
        get().setAutofocus(autofocus);
        return this;
    }

    public SelectFactory<T> label(String label) {
        get().setLabel(label);
        return this;
    }

    public SelectFactory<T> textRenderer(ItemLabelGenerator<T> textRenderer) {
        get().setTextRenderer(textRenderer);
        return this;
    }

    public SelectFactory<T> toPrefix(Component... toPrefix) {
        get().addToPrefix(toPrefix);
        return this;
    }

    public SelectFactory<T> lumoSmall() {
        get().getElement().setAttribute("theme", "small");
        get().getStyle().set("margin-top", "auto 0").set("margin-bottom", "auto 0");
        return this;
    }

    public SelectFactory<T> lumoSmall30pxHeight() {
        get().getElement().setAttribute("theme", "small");
        get().getStyle().set("height", "30px")
                .set("margin-top", "auto 0").set("margin-bottom", "auto 0");
        return this;
    }
}
