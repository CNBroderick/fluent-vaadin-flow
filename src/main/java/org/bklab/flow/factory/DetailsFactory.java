package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.HasEnabledFactory;
import org.bklab.flow.base.HasThemeFactory;

public class DetailsFactory extends FlowFactory<Details, DetailsFactory>
        implements HasEnabledFactory<Details, DetailsFactory>, HasThemeFactory<Details, DetailsFactory> {
    public DetailsFactory() {
        this(new Details());
    }

    public DetailsFactory(String summary, Component content) {
        this(new Details(summary, content));
    }

    public DetailsFactory(Component summary, Component content) {
        this(new Details(summary, content));
    }

    public DetailsFactory(Details component) {
        super(component);
    }

    public DetailsFactory summary(Component summary) {
        get().setSummary(summary);
        return this;
    }

    public DetailsFactory opened(boolean opened) {
        get().setOpened(opened);
        return this;
    }

    public DetailsFactory content(Component... content) {
        get().addContent(content);
        return this;
    }

    public DetailsFactory summaryText(String summaryText) {
        get().setSummaryText(summaryText);
        return this;
    }

    public DetailsFactory themeVariants(DetailsVariant... themeVariants) {
        get().addThemeVariants(themeVariants);
        return this;
    }

    public DetailsFactory content(Component content) {
        get().setContent(content);
        return this;
    }

    public DetailsFactory openedChangeListener(ComponentEventListener<Details.OpenedChangeEvent> openedChangeListener) {
        get().addOpenedChangeListener(openedChangeListener);
        return this;
    }

    public DetailsFactory removeThemeVariants(DetailsVariant... removeThemeVariants) {
        get().removeThemeVariants(removeThemeVariants);
        return this;
    }

    public DetailsFactory filled() {
        get().addThemeVariants(DetailsVariant.FILLED);
        return this;
    }

    public DetailsFactory reverse() {
        get().addThemeVariants(DetailsVariant.REVERSE);
        return this;
    }

    public DetailsFactory small() {
        get().addThemeVariants(DetailsVariant.SMALL);
        return this;
    }

}
