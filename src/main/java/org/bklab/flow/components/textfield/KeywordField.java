package org.bklab.flow.components.textfield;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.TextFieldFactory;

import java.util.function.BiConsumer;


public class KeywordField extends TextField {

    private final Button keywordButton = new ButtonFactory()
            .icon(VaadinIcon.SEARCH).lumoSmall().lumoTertiaryInline().get();

    {
        setPlaceholder("关键字(按回车搜索)");
        setSuffixComponent(keywordButton);
        addThemeVariants(TextFieldVariant.LUMO_SMALL);
    }

    public KeywordField(BiConsumer<TextField, ComponentEvent<?>> eventConsumer) {
        addKeyPressListener(Key.ENTER, t -> eventConsumer.accept(this, t));
        keywordButton.addClickListener(t -> eventConsumer.accept(this, t));
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
