/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-23 14:10:00
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.dialog.search.AdvanceSearchField
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.search;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.factory.IconFactory;
import org.bklab.flow.factory.TextFieldFactory;

import java.util.Objects;

public class AdvanceSearchField<E extends Dialog> extends TextField {

    private final E dialog;
    private final Button clearButton;
    private final Button openButton;
    private String placeholder = "高级搜索";

    public AdvanceSearchField(E dialog) {
        this.dialog = dialog;
        asFactory().lumoSmall().minWidth("200px").width("25vw").maxWidth("50vw").readOnly().value(placeholder);

        clearButton = new ButtonFactory().icon(new IconFactory(VaadinIcon.CLOSE).color("var(--lumo-secondary-text-color)").get())
                .lumoIcon().visible(false).lumoSmall().lumoTertiaryInline().get();

        openButton = new FluentButton(new IconFactory(VaadinIcon.FILTER).color("var(--lumo-secondary-text-color)").get())
                .asFactory().border("0").enabled(true).padding("0").clickListener(e -> dialog.open()).get();
        addValueChangeListener(e -> clearButton.setVisible(e.getValue() != null && !placeholder.equals(e.getValue())));
        dialog.addOpenedChangeListener(e -> openButton.setEnabled(!e.isOpened()));
        setSuffixComponent(new DivFactory(clearButton, openButton).displayFlex().get());
    }

    public AdvanceSearchField<E> placeholder(String placeholder) {
        if (Objects.equals(getPlaceholder(), this.placeholder)) setValue(placeholder);
        this.placeholder = Objects.toString(placeholder, this.placeholder);
        return this;
    }

    public E getDialog() {
        return dialog;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public AdvanceSearchField<E> checkClearButtonVisibility() {
        clearButton.setVisible(getValue() != null && !getValue().isBlank());
        return this;
    }

    public Button getOpenButton() {
        return openButton;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value == null || value.trim().isEmpty() ? placeholder : value);
    }

    public TextFieldFactory asFactory() {
        return new TextFieldFactory(this);
    }
}
