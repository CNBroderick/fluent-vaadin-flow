/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.navigation.tab.ClosableNaviTab
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.navigation.tab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.util.lumo.FontSize;
import org.bklab.flow.util.lumo.UIUtils;

public class ClosableNaviTab extends NaviTab {

    private final Button close;

    public ClosableNaviTab(String label,
                           Class<? extends Component> navigationTarget) {
        super(label, navigationTarget);
        getElement().setAttribute("closable", true);

        close = UIUtils.createButton(VaadinIcon.CLOSE, ButtonVariant.LUMO_TERTIARY_INLINE);
        // ButtonVariant.LUMO_SMALL isn't small enough.
        UIUtils.setFontSize(FontSize.XXS, close);
        add(close);
    }

    public Button getCloseButton() {
        return close;
    }
}
