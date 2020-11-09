package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface FlexComponentFactory<T extends Component & FlexComponent, E extends FlexComponentFactory<T, E>>
        extends IFlowFactory<T>,
        HasOrderedComponentsFactory<T, E>,
        HasStyleFactory<T, E>,
        HasSizeFactory<T, E> {

    @Override
    default E replace(Component oldComponent, Component newComponent) {
        get().replace(oldComponent, newComponent);
        return (E) this;
    }

    default E expand(Component... expand) {
        get().expand(expand);
        return (E) this;
    }

    default E flexGrow(double flexGrow, HasElement... elementContainers) {
        get().setFlexGrow(flexGrow, elementContainers);
        return (E) this;
    }

    default E alignItems(FlexComponent.Alignment alignItems) {
        get().setAlignItems(alignItems);
        return (E) this;
    }

    default E alignItemsStart(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.START);
        return (E) this;
    }

    default E alignItemsEnd(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.END);
        return (E) this;
    }

    default E alignItemsCenter(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.CENTER);
        return (E) this;
    }

    default E alignItemsStretch(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.STRETCH);
        return (E) this;
    }

    default E alignItemsBaseline(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.BASELINE);
        return (E) this;
    }

    default E alignItemsAuto(HasElement... hasElements) {
        get().setAlignItems(FlexComponent.Alignment.AUTO);
        return (E) this;
    }

    default E alignSelf(FlexComponent.Alignment flexComponentAlignment, HasElement... hasElements) {
        get().setAlignSelf(flexComponentAlignment, hasElements);
        return (E) this;
    }

    default E alignSelfStart(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.START);
        return (E) this;
    }

    default E alignSelfEnd(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.END);
        return (E) this;
    }

    default E alignSelfCenter(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.CENTER);
        return (E) this;
    }

    default E alignSelfStretch(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.STRETCH);
        return (E) this;
    }

    default E alignSelfBaseline(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.BASELINE);
        return (E) this;
    }

    default E alignSelfAuto(HasElement... hasElements) {
        get().setAlignSelf(FlexComponent.Alignment.AUTO);
        return (E) this;
    }

    default E justifyContentMode(FlexComponent.JustifyContentMode justifyContentMode) {
        get().setJustifyContentMode(justifyContentMode);
        return (E) this;
    }

    default E justifyContentModeStart() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        return (E) this;
    }

    default E justifyContentModeEnd() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        return (E) this;
    }

    default E justifyContentModeCenter() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return (E) this;
    }

    default E justifyContentModeBetween() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return (E) this;
    }

    default E justifyContentModeAround() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        return (E) this;
    }

    default E justifyContentModeEvenly() {
        get().setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
        return (E) this;
    }
}
