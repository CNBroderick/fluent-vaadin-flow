/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 13:46:13
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.dialog.FluentDialog
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.dialog;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.IFlowFactory;
import org.bklab.flow.base.HasSizeFactory;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.DialogFactory;
import org.bklab.flow.layout.LmrHorizontalLayout;
import org.bklab.flow.layout.TmbVerticalLayout;
import org.bklab.flow.text.TitleLabel;

@CssImport(value = "./styles/components/vaadin-dialog-overlay.css", themeFor = "vaadin-dialog-overlay")
public class FluentDialog extends Dialog implements IFlowFactory<FluentDialog>, HasSizeFactory<FluentDialog, FluentDialog> {

    private final LmrHorizontalLayout headers = new LmrHorizontalLayout();
    private final LmrHorizontalLayout bottoms = new LmrHorizontalLayout();
    private final TmbVerticalLayout main = new TmbVerticalLayout();
    private final Button closeButton = new ButtonFactory().lumoSmall().lumoIcon()
            .lumoTertiaryInline().clickListener(e -> close()).icon(VaadinIcon.CLOSE).get();

    public FluentDialog() {
        headers.getLeft().get().getStyle().set("padding-left", "1em");
        add(main.top(headers.right(closeButton)).bottom(bottoms));
        getElement().getStyle().set("padding", "0").set("margin", "0");
        closeButton.getStyle().set("color", "rgba(0,0,0,0.45)");
        setDraggable(true);
    }

    public FluentDialog title(String title) {
        headers.left(new TitleLabel(title));
        return this;
    }

    public FluentDialog title(Component title) {
        headers.left(title);
        return this;
    }

    public FluentDialog content(Component... component) {
        main.clearMiddle().middle(component);
        return this;
    }

    public FluentDialog rightHeader(Component component) {
        headers.getRight().peek(h -> h.addComponentAtIndex(Math.max(h.indexOf(closeButton) - 1, 0), component));
        return this;
    }

    public FluentDialog leftBottom(Component... component) {
        bottoms.left(component);
        return this;
    }

    public FluentDialog rightBottom(Component... component) {
        bottoms.right(component);
        return this;
    }

    public LmrHorizontalLayout getHeaders() {
        return headers;
    }

    public LmrHorizontalLayout getBottoms() {
        return bottoms;
    }

    public TmbVerticalLayout getMain() {
        return main;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public DialogFactory asFactory() {
        return new DialogFactory(this);
    }

    @Override
    public FluentDialog get() {
        return this;
    }
}
