package org.bklab.flow.factory;

import dev.mett.vaadin.tooltip.config.*;

import java.util.function.Supplier;

public class TooltipConfigurationFactory implements Supplier<TooltipConfiguration> {

    private final TooltipConfiguration tooltipConfiguration;

    public TooltipConfigurationFactory(TooltipConfiguration tooltipConfiguration) {
        this.tooltipConfiguration = tooltipConfiguration;
    }

    public TooltipConfigurationFactory(String text) {
        this.tooltipConfiguration = new TooltipConfiguration(text);
    }

    public TooltipConfigurationFactory() {
        this.tooltipConfiguration = new TooltipConfiguration();
    }

    public TooltipConfigurationFactory offset(int skidding, int distance) {
        get().setOffset(skidding, distance);
        return this;
    }

    public TooltipConfigurationFactory ignoreAttributes(Boolean ignoreAttributes) {
        get().setIgnoreAttributes(ignoreAttributes);
        return this;
    }

    public TooltipConfigurationFactory interactiveDebounce(Integer interactiveDebounce) {
        get().setInteractiveDebounce(interactiveDebounce);
        return this;
    }

    public TooltipConfigurationFactory interactiveBorder(Integer interactiveBorder) {
        get().setInteractiveBorder(interactiveBorder);
        return this;
    }

    public TooltipConfigurationFactory touch(String touch) {
        get().setTouch(touch);
        return this;
    }

    public TooltipConfigurationFactory touch(Boolean touch) {
        get().setTouch(touch);
        return this;
    }

    public TooltipConfigurationFactory touch(String touchTrigger, int duration) {
        get().setTouch(touchTrigger, duration);
        return this;
    }

    public TooltipConfigurationFactory content(String content) {
        get().setContent(content);
        return this;
    }

    public TooltipConfigurationFactory delay(Integer showDelay, Integer hideDelay) {
        get().setDelay(showDelay, hideDelay);
        return this;
    }

    public TooltipConfigurationFactory delay(Integer delay) {
        get().setDelay(delay);
        return this;
    }

    public TooltipConfigurationFactory duration(Integer showDuration, Integer hideDuration) {
        get().setDuration(showDuration, hideDuration);
        return this;
    }

    public TooltipConfigurationFactory duration(Integer duration) {
        get().setDuration(duration);
        return this;
    }

    public TooltipConfigurationFactory maxWidth(Integer maxWidth) {
        get().setMaxWidth(maxWidth);
        return this;
    }

    public TooltipConfigurationFactory maxWidthNone() {
        get().setMaxWidthNone();
        return this;
    }

    public TooltipConfigurationFactory followCursor(TC_FOLLOW_CURSOR followCursor) {
        get().setFollowCursor(followCursor);
        return this;
    }

    public TooltipConfigurationFactory allowHTML(Boolean allowHTML) {
        get().setAllowHTML(allowHTML);
        return this;
    }

    public TooltipConfigurationFactory arrow(Boolean arrow) {
        get().setArrow(arrow);
        return this;
    }

    public TooltipConfigurationFactory inertia(Boolean inertia) {
        get().setInertia(inertia);
        return this;
    }

    public TooltipConfigurationFactory moveTransition(String moveTransition) {
        get().setMoveTransition(moveTransition);
        return this;
    }

    public TooltipConfigurationFactory interactive(Boolean interactive) {
        get().setInteractive(interactive);
        return this;
    }

    public TooltipConfigurationFactory hideOnClick(TC_HIDE_ON_CLICK hideOnClick) {
        get().setHideOnClick(hideOnClick);
        return this;
    }

    public TooltipConfigurationFactory zIndex(Integer zIndex) {
        get().setZIndex(zIndex);
        return this;
    }

    public TooltipConfigurationFactory placement(TC_PLACEMENT placement) {
        get().setPlacement(placement);
        return this;
    }

    public TooltipConfigurationFactory sticky(TC_STICKY sticky) {
        get().setSticky(sticky);
        return this;
    }

    public TooltipConfigurationFactory role(String role) {
        get().setRole(role);
        return this;
    }

    public TooltipConfigurationFactory showOnCreate(Boolean showOnCreate) {
        get().setShowOnCreate(showOnCreate);
        return this;
    }

    public TooltipConfigurationFactory trigger(String trigger) {
        get().setTrigger(trigger);
        return this;
    }

    public TooltipConfigurationFactory theme(String theme) {
        get().setTheme(theme);
        return this;
    }

    @Override
    public TooltipConfiguration get() {
        return tooltipConfiguration;
    }
}
