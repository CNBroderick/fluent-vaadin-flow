package org.bklab.flow.components.slider;

import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Tag("paper-slider")
@JsModule("@polymer/paper-slider/paper-slider.js")
@JsModule("./src/org/bklab/slider/fluent-paper-slider.js")
@NpmPackage(value = "@polymer/paper-slider", version = "^3.0.1")
/*
 If you wish to include your own JS modules in the add-on jar, add the module
 files to './src/main/resources/META-INF/resources/frontend' and insert an
 annotation @JsModule("./my-module.js") here.
*/
public class PaperSlider extends PolymerTemplate<PaperSlider.PaperSliderModel> implements HasValue<HasValue.ValueChangeEvent<Integer>, Integer> {

    private final List<ValueChangeListener<? super HasValue.ValueChangeEvent<Integer>>> listeners = new ArrayList<>();
    private final AtomicInteger lastValue = new AtomicInteger();

    public PaperSlider(int min, int max, int value) {
        getModel().setMin(min);
        getModel().setMax(max);
        getModel().setValue(value);
        lastValue.set(value);
    }

    public Integer getValue() {
        return getModel().getValue();
    }

    @Override
    public void setValue(Integer value) {
        getModel().setValue(value);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<? super HasValue.ValueChangeEvent<Integer>> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }

    public PaperSlider valueChangeListener(ValueChangeListener<? super HasValue.ValueChangeEvent<Integer>> listener) {
        addValueChangeListener(listener);
        return this;
    }

    @EventHandler
    public void onValueChanged(@EventData("value") int value) {
        System.err.println("changed value to: " + value);
        ValueChangeEvent valueChangeEvent = new ValueChangeEvent(this, value, lastValue.get(), true);
        listeners.forEach(a -> a.valueChanged(valueChangeEvent));
        lastValue.set(value);
    }

    @Override
    public boolean isReadOnly() {
        return getElement().hasAttribute("readonly");
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        getElement().setAttribute("readonly", readOnly);
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return false;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
    }

    public interface PaperSliderModel extends TemplateModel {

        void setMax(int max);

        void setMin(int min);

        int getValue();

        void setValue(int value);
    }

    public static class ValueChangeEvent implements HasValue.ValueChangeEvent<Integer> {

        private final PaperSlider paperSlider;
        private final int value;
        private final int lastValue;
        private final boolean fromClient;

        public ValueChangeEvent(PaperSlider paperSlider, int value, int lastValue, boolean fromClient) {
            this.paperSlider = paperSlider;
            this.value = value;
            this.lastValue = lastValue;
            this.fromClient = fromClient;
        }

        @Override
        public HasValue<?, Integer> getHasValue() {
            return paperSlider;
        }

        @Override
        public boolean isFromClient() {
            return fromClient;
        }

        @Override
        public Integer getOldValue() {
            return lastValue;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
