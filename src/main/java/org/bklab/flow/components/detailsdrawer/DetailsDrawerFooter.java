/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.detailsdrawer.DetailsDrawerFooter
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.detailsdrawer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.shared.Registration;
import org.bklab.flow.layout.FlexBoxLayout;
import org.bklab.flow.layout.size.Horizontal;
import org.bklab.flow.layout.size.Right;
import org.bklab.flow.layout.size.Vertical;
import org.bklab.flow.util.lumo.LumoStyles;
import org.bklab.flow.util.lumo.UIUtils;

public class DetailsDrawerFooter extends FlexBoxLayout {

    private final Button save;
    private final Button cancel;

    public DetailsDrawerFooter() {
        setBackgroundColor(LumoStyles.Color.Contrast._5);
        setPadding(Horizontal.RESPONSIVE_L, Vertical.S);
        setSpacing(Right.S);
        setWidthFull();

        save = UIUtils.createPrimaryButton("Save");
        cancel = UIUtils.createTertiaryButton("Cancel");
        add(save, cancel);
    }

    public Registration addSaveListener(
            ComponentEventListener<ClickEvent<Button>> listener) {
        return save.addClickListener(listener);
    }

    public Registration addCancelListener(
            ComponentEventListener<ClickEvent<Button>> listener) {
        return cancel.addClickListener(listener);
    }

}
