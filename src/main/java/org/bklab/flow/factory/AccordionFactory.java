package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasSizeFactory;

public class AccordionFactory extends FlowFactory<Accordion, AccordionFactory> implements HasSizeFactory<Accordion, AccordionFactory> {
    public AccordionFactory() {
        this(new Accordion());
    }

    public AccordionFactory(Accordion component) {
        super(component);
    }

    public AccordionFactory add(AccordionPanel add) {
        get().add(add);
        return this;
    }

    public AccordionFactory add(String string, Component component) {
        get().add(string, component);
        return this;
    }

    public AccordionFactory remove(Component remove) {
        get().remove(remove);
        return this;
    }

    public AccordionFactory remove(AccordionPanel remove) {
        get().remove(remove);
        return this;
    }

    public AccordionFactory close() {
        get().close();
        return this;
    }

    public AccordionFactory open(AccordionPanel open) {
        get().open(open);
        return this;
    }

    public AccordionFactory open(int open) {
        get().open(open);
        return this;
    }

    public AccordionFactory openedChangeListener(ComponentEventListener<Accordion.OpenedChangeEvent> openedChangeListener) {
        get().addOpenedChangeListener(openedChangeListener);
        return this;
    }

}
