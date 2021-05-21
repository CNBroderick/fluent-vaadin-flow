/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-19 11:25:12
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.textfield.KeywordField
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.textfield;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.TextFieldFactory;

import java.util.function.BiConsumer;


public class KeywordField extends TextField {

    private final Button keywordButton = new ButtonFactory().cursorPointer()
            .icon(VaadinIcon.SEARCH).lumoSmall().lumoTertiaryInline().get();

    public KeywordField(BiConsumer<TextField, ComponentEvent<?>> eventConsumer) {
        this("关键字(按回车搜索)", eventConsumer);
    }

    public KeywordField(String placeholder, BiConsumer<TextField, ComponentEvent<?>> eventConsumer) {
        this(placeholder);
        searchListener(eventConsumer);
    }

    public KeywordField(String placeholder) {
        setPlaceholder(placeholder);
        setSuffixComponent(keywordButton);
        addThemeVariants(TextFieldVariant.LUMO_SMALL);
        setValueChangeMode(ValueChangeMode.ON_CHANGE);
    }

    public KeywordField searchListener(BiConsumer<TextField, ComponentEvent<?>> eventConsumer) {
        addKeyPressListener(Key.ENTER, t -> eventConsumer.accept(this, t));
        keywordButton.addClickListener(t -> eventConsumer.accept(this, t));
        return this;
    }

    public KeywordField effectWhenValueChange() {
        addValueChangeListener(e -> keywordButton.clickInClient());
        return this;
    }

    public TextFieldFactory asFactory() {
        return new TextFieldFactory(this);
    }

    public ButtonFactory asButtonFactory() {
        return new ButtonFactory(keywordButton);
    }

    public Button getKeywordButton() {
        return keywordButton;
    }
}
