/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-06-05 14:27:48
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：org.bklab.flow.login.FluentLoginModalLayout
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;

/**
 * A Designer generated component for the fluent-login-modal-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("fluent-login-modal-view")
@JsModule("./src/org/bklab/flow/login/fluent-login-modal-view.ts")
public class FluentLoginModalLayout extends LitTemplate {

    @Id("copyright")
    private HorizontalLayout copyright;
    @Id("content")
    private VerticalLayout content;
    @Id("outerLayout")
    private VerticalLayout outerLayout;

    /**
     * Creates a new FluentLoginModalView.
     */
    public FluentLoginModalLayout() {
        // You can initialise any data required for the connected UI components here.
    }

    public HorizontalLayout getCopyright() {
        return copyright;
    }

    public FluentLoginModalLayout setCopyright(HorizontalLayout copyright) {
        this.copyright = copyright;
        return this;
    }

    public VerticalLayout getContent() {
        return content;
    }

    public FluentLoginModalLayout setContent(VerticalLayout content) {
        this.content = content;
        return this;
    }

    public VerticalLayout getOuterLayout() {
        return outerLayout;
    }

    public FluentLoginModalLayout setOuterLayout(VerticalLayout outerLayout) {
        this.outerLayout = outerLayout;
        return this;
    }
}
