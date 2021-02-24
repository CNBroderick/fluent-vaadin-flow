package org.bklab.flow.factory;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.IntegerField;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.AbstractNumberFieldFactory;

public class IntegerFieldFactory extends FlowFactory<IntegerField, IntegerFieldFactory> implements
        AbstractNumberFieldFactory<Integer, IntegerField, IntegerFieldFactory> {

    public IntegerFieldFactory() {
        this(new IntegerField());
    }

    public IntegerFieldFactory(String label) {
        this(new IntegerField(label));
    }

    public IntegerFieldFactory(String label, String placeholder) {
        this(new IntegerField(label, placeholder));
    }

    public IntegerFieldFactory(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this(new IntegerField(listener));
    }

    public IntegerFieldFactory(String label, HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this(new IntegerField(listener));
    }

    public IntegerFieldFactory(String label, Integer initialValue, HasValue.ValueChangeListener
            <? super AbstractField.ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this(new IntegerField());
    }

    public IntegerFieldFactory(IntegerField component) {
        super(component);
    }

    public IntegerFieldFactory max(int max) {
        get().setMax(max);
        return this;
    }

    public IntegerFieldFactory min(int min) {
        get().setMin(min);
        return this;
    }

    public IntegerFieldFactory step(int step) {
        get().setStep(step);
        return this;
    }

}
