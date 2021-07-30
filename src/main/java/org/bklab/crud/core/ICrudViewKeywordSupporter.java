/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-24 11:35:14
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.crud.core.ICrudViewKeywordSupporter
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.crud.core;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.components.textfield.KeywordField;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public interface ICrudViewKeywordSupporter<T, G extends Grid<T>, C extends FluentCrudView<T, G>>
        extends IFluentCrudViewCommonField<T, G, C> {


    /**
     * @param name        inMemoryEntityFilter map key
     * @param placeholder text field placeholder
     * @param predicate   test entity and stripedKeywordString is valid
     *
     * @return keyword field
     */
    default KeywordField createInMemoryFilter(String name, String placeholder, BiPredicate<T, String> predicate) {
        BiConsumer<TextField, ComponentEvent<?>> consumer = (filed, event) -> {
            filed.getOptionalValue().map(String::strip).filter(s -> !s.isBlank()).ifPresentOrElse(
                    keyword -> getCrudView().getInMemoryEntityFilter().put(name, entity -> predicate.test(entity, keyword)),
                    () -> getCrudView().getInMemoryEntityFilter().remove(name));
            getCrudView().reloadGridDataInMemory();
        };

        KeywordField keywordField = new KeywordField(consumer);
        keywordField.setValueChangeMode(ValueChangeMode.EAGER);
        keywordField.addValueChangeListener(event -> consumer.accept(keywordField, event));

        if (placeholder != null) keywordField.setPlaceholder(placeholder);
        return keywordField;
    }

    default KeywordField addKeywordField() {
        KeywordField keywordField = new KeywordField((c, v) -> getCrudView().reloadGridData());
        getCrudView().getParameterMap().put("keyword", () -> Optional.ofNullable(keywordField.getValue()).map(k -> k.trim().isEmpty() ? null : k.trim()).orElse(null));
        return keywordField;
    }

    default FluentCrudView<T, G> addKeywordFieldToLeft() {
        getCrudView().header().left(addKeywordField());
        return getCrudView();
    }

    default FluentCrudView<T, G> addKeywordFieldToMiddle() {
        getCrudView().header().middle(addKeywordField());
        return getCrudView();
    }

    default FluentCrudView<T, G> addKeywordFieldToRight() {
        getCrudView().header().right(addKeywordField());
        return getCrudView();
    }
}
