package org.bklab.flow.components.button;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.server.AbstractStreamResource;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.util.lumo.LumoStyles;

@Tag("image-card-button")
@CssImport("./styles/org/bklab/component/button/image-card-button.css")
public class ImageCardButton extends Button {

    private final Image image = new Image();
    private final Span label = new Span();

    {
        String BASE_CLASS_NAME = "image-card-button";

        addClassNames(BASE_CLASS_NAME);
        label.addClassNames(BASE_CLASS_NAME + "__label", LumoStyles.Size.M);
        image.addClassNames(BASE_CLASS_NAME + "__image");
    }

    public ImageCardButton(AbstractStreamResource imageResource, String text) {
        image.setSrc(imageResource);
        image.setAlt(text);
        label.setText(text);
        addToPrefix(new DivFactory(image, label).sizeFull().get());
    }

    public ImageCardButton clickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        addClickListener(listener);
        return this;
    }

    public ButtonFactory asFactory() {
        return new ButtonFactory(this);
    }
}
