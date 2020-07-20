package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasStyleFactory<C extends Component & HasStyle, E extends HasStyleFactory<C, E>> extends IFlowFactory<C> {
    default E displayFlex() {
        get().getStyle().set("display", "flex");
        return (E) this;
    }

    default E backgroundColor(String color) {
        get().getStyle().set("background-color", color);
        return (E) this;
    }

    default E margin(String vertical, String horizontal) {
        get().getStyle().set("margin", vertical + " " + horizontal);
        return (E) this;
    }

    default E margin(String top, String right, String bottom, String left) {
        get().getStyle().set("margin", top + " " + right + " " + bottom + " " + left);
        return (E) this;
    }

    default E marginAutoLeft() {
        get().getStyle().set("margin-left", "auto");
        return (E) this;
    }

    default E marginAutoRight() {
        get().getStyle().set("margin-right", "auto");
        return (E) this;
    }

    default E marginAutoTop() {
        get().getStyle().set("margin-top", "auto");
        return (E) this;
    }

    default E marginAutoEnd() {
        get().getStyle().set("margin-end", "auto");
        return (E) this;
    }

    default E colors(String color) {
        get().getStyle().set("color", color);
        return (E) this;
    }

    default E fontSize(String fontSize) {
        get().getStyle().set("font-size", fontSize);
        return (E) this;
    }

    default E fontSizeXXS() {
        get().getStyle().set("font-size", "var(--lumo-font-size-xxs)");
        return (E) this;
    }

    default E fontSizeXS() {
        get().getStyle().set("font-size", "var(--lumo-font-size-xs)");
        return (E) this;
    }

    default E fontSizeS() {
        get().getStyle().set("font-size", "var(--lumo-font-size-s)");
        return (E) this;
    }

    default E fontSizeM() {
        get().getStyle().set("font-size", "var(--lumo-font-size-m)");
        return (E) this;
    }

    default E fontSizeL() {
        get().getStyle().set("font-size", "var(--lumo-font-size-l)");
        return (E) this;
    }

    default E fontSizeXL() {
        get().getStyle().set("font-size", "var(--lumo-font-size-xl)");
        return (E) this;
    }

    default E fontSizeXXL() {
        get().getStyle().set("font-size", "var(--lumo-font-size-xxl)");
        return (E) this;
    }

    default E fontSizeXXXL() {
        get().getStyle().set("font-size", "var(--lumo-font-size-xxxl)");
        return (E) this;
    }

    default E style(String name, String content) {
        get().getStyle().set(name, content);
        return (E) this;
    }

    default E className(String... className) {
        get().addClassNames(className);
        return (E) this;
    }

    default E removeClassName(String removeClassName) {
        get().removeClassName(removeClassName);
        return (E) this;
    }

    default E className(String string, boolean set) {
        get().setClassName(string, set);
        return (E) this;
    }

    default E className(String className) {
        get().setClassName(className);
        return (E) this;
    }

    default E classNames(String... classNames) {
        get().addClassNames(classNames);
        return (E) this;
    }

    default E removeClassNames(String... removeClassNames) {
        get().removeClassNames(removeClassNames);
        return (E) this;
    }

    default E hasClassName(String hasClassName) {
        get().hasClassName(hasClassName);
        return (E) this;
    }
}
