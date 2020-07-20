/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.mix.ListItem
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.mix;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.layout.size.Right;
import org.bklab.flow.layout.size.Wide;
import org.bklab.flow.util.lumo.FontSize;
import org.bklab.flow.util.lumo.TextColor;
import org.bklab.flow.util.lumo.UIUtils;
import org.bklab.flow.util.css.WhiteSpace;

@CssImport("./styles/components/list-item.css")
public class ListItem extends FlexBoxLayout {

    private final String CLASS_NAME = "list-item";
    private final FlexBoxLayout content;
    private final Label primary;
    private final Label secondary;
    private Div prefix;
    private Div suffix;

    public ListItem(String primary, String secondary) {
        addClassName(CLASS_NAME);

        setAlignItems(FlexComponent.Alignment.CENTER);
        setPadding(Wide.RESPONSIVE_L);
        setSpacing(Right.L);

        this.primary = new Label(primary);
        this.secondary = UIUtils.createLabel(FontSize.S, TextColor.SECONDARY,
                secondary);
        this.primary.setClassName(CLASS_NAME + "__primary");
        this.secondary.setClassName(CLASS_NAME + "__secondary");

        content = new FlexBoxLayout(this.primary, this.secondary);
        content.setClassName(CLASS_NAME + "__content");
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        add(content);
    }

    public ListItem(String primary) {
        this(primary, "");
    }

    /* === PREFIX === */

    public ListItem(Component prefix, String primary, String secondary) {
        this(primary, secondary);
        setPrefix(prefix);
    }

    public ListItem(Component prefix, String primary) {
        this(prefix, primary, "");
    }

    /* === SUFFIX === */

    public ListItem(String primary, String secondary, Component suffix) {
        this(primary, secondary);
        setSuffix(suffix);
    }

    public ListItem(String primary, Component suffix) {
        this(primary, null, suffix);
    }

    /* === PREFIX & SUFFIX === */

    public ListItem(Component prefix, String primary, String secondary,
                    Component suffix) {
        this(primary, secondary);
        setPrefix(prefix);
        setSuffix(suffix);
    }

    public ListItem(Component prefix, String primary, Component suffix) {
        this(prefix, primary, "", suffix);
    }

    /* === MISC === */

    public FlexBoxLayout getContent() {
        return content;
    }

    public void setWhiteSpace(WhiteSpace whiteSpace) {
        UIUtils.setWhiteSpace(whiteSpace, this);
    }

    public void setReverse(boolean reverse) {
        if (reverse) {
            content.setFlexDirection(FlexLayout.FlexDirection.COLUMN_REVERSE);
        } else {
            content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        }
    }

    public void setHorizontalPadding(boolean horizontalPadding) {
        if (horizontalPadding) {
            getStyle().remove("padding-left");
            getStyle().remove("padding-right");
        } else {
            getStyle().set("padding-left", "0");
            getStyle().set("padding-right", "0");
        }
    }

    public void setPrimaryText(String text) {
        primary.setText(text);
    }

    public Label getPrimary() {
        return primary;
    }

    public void setSecondaryText(String text) {
        secondary.setText(text);
    }

    public void setPrefix(Component... components) {
        if (prefix == null) {
            prefix = new Div();
            prefix.setClassName(CLASS_NAME + "__prefix");
            getElement().insertChild(0, prefix.getElement());
            getElement().setAttribute("with-prefix", true);
        }
        prefix.removeAll();
        prefix.add(components);
    }

    public void setSuffix(Component... components) {
        if (suffix == null) {
            suffix = new Div();
            suffix.setClassName(CLASS_NAME + "__suffix");
            getElement().insertChild(getElement().getChildCount(),
                    suffix.getElement());
            getElement().setAttribute("with-suffix", true);
        }
        suffix.removeAll();
        suffix.add(components);
    }

    public void setDividerVisible(boolean visible) {
        if (visible) {
            getElement().setAttribute("with-divider", true);
        } else {
            getElement().removeAttribute("with-divider");
        }
    }

}
