package org.bklab.flow.components.html;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

@CssImport("./styles/org/bklab/component/html/single-label.css")
public class SingleLabel extends Div {

    private final Span name = new Span();
    private final Span value = new Span();

    {
        addClassName("single-label");
        name.addClassName("single-label-name");
        value.addClassName("single-label-value");
        add(value, name);
    }

    public SingleLabel() {
    }

    public SingleLabel(String name, String value) {
        name(name);
        value(value);
    }

    public SingleLabel name(String name) {
        this.name.setText(name);
        return this;
    }

    public SingleLabel value(String value) {
        this.value.setText(value);
        return this;
    }

    public Span getName() {
        return name;
    }

    public Span getValue() {
        return value;
    }
}
