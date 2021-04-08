package org.bklab.flow.components.selector.button;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./styles/components/selector/button-selector.css")
public class SelectButton extends Button {

    private final static String CLASS_NAME = "button-selector";
    private final static String ACTIVE_CLASS_NAME = "button-selector__button-active";

    private boolean active = false;

    public SelectButton() {
        addClassName(CLASS_NAME + "__button");
    }

    public SelectButton active() {
        addClassName(ACTIVE_CLASS_NAME);
        this.active = true;
        return this;
    }

    public SelectButton active(boolean active) {
        if (active) return active();
        else return inactive();
    }

    public SelectButton inactive() {
        removeClassName(ACTIVE_CLASS_NAME);
        this.active = false;
        return this;
    }
}
