package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasThemeFactory<C extends Component & HasTheme, E extends HasThemeFactory<C, E>> extends IFlowFactory<C> {

    default E removeThemeName(String removeThemeName) {
        get().removeThemeName(removeThemeName);
        return (E) this;
    }

    default E themeName(String themeName) {
        get().addThemeName(themeName);
        return (E) this;
    }

    default E themeName(String themeName, boolean set) {
        get().setThemeName(themeName, set);
        return (E) this;
    }

    default E themeNames(String... themeNames) {
        get().addThemeNames(themeNames);
        return (E) this;
    }

    default E hasThemeName(String hasThemeName) {
        get().hasThemeName(hasThemeName);
        return (E) this;
    }

    default E removeThemeNames(String... removeThemeNames) {
        get().removeThemeNames(removeThemeNames);
        return (E) this;
    }
}
