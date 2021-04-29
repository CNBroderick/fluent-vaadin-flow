/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-22 13:25:28
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.crud.menu.IFluentMenuSeparatorSupporter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import org.bklab.flow.components.menu.FluentMenuItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface IFluentMenuSeparatorSupporter {


    default void addSeparator(ContextMenu contextMenu) {
        if (canAddSeparator(contextMenu)) FluentMenuItem.addSeparator(contextMenu);
    }

    default void removeDuplicateSeparator(ContextMenu contextMenu) {
        Set<Component> separators = new HashSet<>();

        Component last = null;
        for (Component item : contextMenu.getChildren().collect(Collectors.toCollection(ArrayList::new))) {
            if (last != null && isSeparator(item) && isSeparator(last)) {
                separators.add(last);
            }
            last = item;
        }
        if (isSeparator(last)) separators.add(last);

        separators.forEach(contextMenu::remove);
    }

    default boolean canAddSeparator(ContextMenu contextMenu) {
        return !contextMenu.getItems().isEmpty() && contextMenu.getItems().get(contextMenu.getItems().size() - 1).getChildren().noneMatch(this::isSeparator);
    }

    default boolean isSeparator(Component component) {
        return component != null && "hr".equals(component.getElement().getTag());
    }
}
