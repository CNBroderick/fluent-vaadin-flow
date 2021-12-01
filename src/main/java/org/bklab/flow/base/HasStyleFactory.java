/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:45
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.base.HasStyleFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.dom.Element;
import dev.mett.vaadin.tooltip.Tooltips;
import dev.mett.vaadin.tooltip.config.TC_FOLLOW_CURSOR;
import dev.mett.vaadin.tooltip.config.TooltipConfiguration;
import org.bklab.flow.IFlowFactory;
import org.bklab.flow.factory.TooltipConfigurationFactory;
import org.bklab.flow.util.text.ObjectToStringFormatter;
import org.slf4j.LoggerFactory;

import java.util.Map;

@SuppressWarnings("unchecked")
public interface HasStyleFactory<C extends Component & HasStyle, E extends HasStyleFactory<C, E>> extends IFlowFactory<C> {

    default E displayFlex() {
        return display("flex");
    }

    default E displayGrid() {
        return display("grid");
    }

    default E display(String display) {
        get().getStyle().set("display", display);
        return (E) this;
    }

    default E alignItems(String align) {
        get().getStyle().set("align-items", align);
        return (E) this;
    }

    default E alignItemsCenter() {
        return alignItems("center");
    }

    default E alignItemsBaseline() {
        return alignItems("baseline");
    }

    default E alignSelf(String align) {
        get().getStyle().set("align-self", align);
        return (E) this;
    }

    default E alignSelfCenter() {
        return alignSelf("center");
    }

    default E alignSelfBaseline() {
        return alignSelf("baseline");
    }

    default E alignContent(String align) {
        get().getStyle().set("align-content", align);
        return (E) this;
    }

    default E alignContentCenter() {
        return alignContent("center");
    }

    default E alignContentBaseline() {
        return alignContent("baseline");
    }

    default E overflowYScroll() {
        return overflowY("auto");
    }

    default E overflowY(String overflow) {
        get().getStyle().set("overflow-y", overflow);
        return (E) this;
    }

    default E flexWrap() {
        get().getStyle().set("flex-wrap", "wrap");
        return (E) this;
    }

    default E flexGrow(int grow) {
        get().getStyle().set("flex-grow", "" + grow);
        return (E) this;
    }

    default E limitMaxWidth(String width) {
        get().getStyle()
                .set("max-width", width)
                .set("text-overflow", "ellipsis")
                .set("display", "block")
                .set("white-space", "nowrap")
                .set("overflow", "hidden");
        return (E) this;
    }

    default E hasSpacingDivContainer() {
        get().addClassName("has-spacing-div-container");
        return (E) this;
    }

    default E hasSpacingDivContainerForRight() {
        get().addClassName("has-spacing-div-container-r");
        return (E) this;
    }

    default E backgroundColor(String color) {
        get().getStyle().set("background-color", color);
        return (E) this;
    }

    default E textAlign(String textAlign) {
        get().getStyle().set("text-align", textAlign);
        return (E) this;
    }

    default E textOverflowEllipsis() {
        get().getStyle().set("text-overflow", "ellipsis");
        return (E) this;
    }

    default E margin(int margin) {
        get().getStyle().set("margin", margin == 0 ? "0" : margin + "px");
        return (E) this;
    }

    default E margin(String margin) {
        get().getStyle().set("margin", margin);
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

    default E border() {
        get().getStyle().set("border", "1px solid #d9d9d9");
        return border("1px", "solid", "#d9d9d9");
    }

    default E border(String width) {
        return border(width, "solid", "#d9d9d9");
    }

    default E border(String width, String color) {
        get().getStyle().set("border", width + " solid " + color);
        return border(width, "solid", color);
    }

    default E border(String width, String style, String color) {
        get().getStyle().set("border", width + " " + style + " " + color);
        return (E) this;
    }

    default E padding(String padding) {
        get().getStyle().set("padding", padding);
        return (E) this;
    }

    default E padding(String vertical, String horizontal) {
        get().getStyle().set("padding", vertical + " " + horizontal);
        return (E) this;
    }

    default E padding(String top, String right, String bottom, String left) {
        get().getStyle().set("padding", top + " " + right + " " + bottom + " " + left);
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

    @Deprecated
    default E marginAutoEnd() {
        return marginAutoBottom();
    }

    default E marginAutoBottom() {
        get().getStyle().set("margin-bottom", "auto");
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

    default E cursorPointer() {
        get().getStyle().set("cursor", "pointer");
        return (E) this;
    }

    default E cursor(String cursorName) {
        get().getStyle().set("cursor", cursorName);
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

    default E tooltip(Map<String, Object> attributes) {
        return tooltip(new ObjectToStringFormatter().serialize(attributes));
    }

    default E tooltip(String text) {
        if (text == null || text.isBlank()) {
            Tooltips.getCurrent().removeTooltip(get());
            return (E) this;
        }
        return tooltip(new TooltipConfigurationFactory().theme("light-border")
                .followCursor(TC_FOLLOW_CURSOR.HORIZONTAL)
                .maxWidthNone().content(text).get());
    }

    default E tooltip(Element element) {
        try {
            return tooltip(new TooltipConfigurationFactory().content(element.getOuterHTML())
                    .followCursor(TC_FOLLOW_CURSOR.HORIZONTAL)
                    .theme("light-border").allowHTML(true).maxWidthNone().get());
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("设置tooltip失败。", e);
        }
        return (E) this;
    }

    default E tooltipHtml(String text) {
        if (text == null || text.isBlank()) {
            Tooltips.getCurrent().removeTooltip(get());
            return (E) this;
        }
        return tooltip(new TooltipConfigurationFactory().content(text)
                .followCursor(TC_FOLLOW_CURSOR.HORIZONTAL).maxWidthNone().allowHTML(true).get());
    }

    default E tooltip(TooltipConfiguration tooltipConfiguration) {
        if (tooltipConfiguration.getContent() == null || tooltipConfiguration.getContent().isBlank()) {
            Tooltips.getCurrent().removeTooltip(get());
            return (E) this;
        }

        if (tooltipConfiguration.getTheme() == null || tooltipConfiguration.getTheme().isBlank()) {
            tooltipConfiguration.setTheme("light");
        }

        Tooltips.getCurrent().setTooltip(get(), tooltipConfiguration);
        return (E) this;
    }

    @Deprecated
    default E tooltip(String text, TooltipConfiguration tooltipConfiguration) {
        tooltipConfiguration.setContent(text);
        return tooltip(tooltipConfiguration);
    }
}
