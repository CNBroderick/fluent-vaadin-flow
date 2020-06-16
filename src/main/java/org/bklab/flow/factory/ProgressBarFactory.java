package org.bklab.flow.factory;

import com.vaadin.flow.component.progressbar.ProgressBar;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinProgressBarFactory;
import org.bklab.flow.base.HasSizeFactory;

public class ProgressBarFactory extends FlowFactory<ProgressBar, ProgressBarFactory> implements
        GeneratedVaadinProgressBarFactory<ProgressBar, ProgressBarFactory>,
        HasSizeFactory<ProgressBar, ProgressBarFactory> {
    public ProgressBarFactory() {
        this(new ProgressBar());
    }

    public ProgressBarFactory(double min, double max) {
        this(new ProgressBar(min, max));
    }

    public ProgressBarFactory(double min, double max, double value) {
        this(new ProgressBar(min, max, value));
    }

    public ProgressBarFactory(ProgressBar component) {
        super(component);
    }

    public ProgressBarFactory value(double value) {
        get().setValue(value);
        return this;
    }

    public ProgressBarFactory min(double min) {
        get().setMin(min);
        return this;
    }

    public ProgressBarFactory max(double max) {
        get().setMax(max);
        return this;
    }

    public ProgressBarFactory indeterminate(boolean indeterminate) {
        get().setIndeterminate(indeterminate);
        return this;
    }
}
