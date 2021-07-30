/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 17:19:45
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.FluentGenerateFormLayoutView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.factory.*;
import org.bklab.flow.form.FluentGenerateFormLayout;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.TitleLayout;

@Route("form")
@PageTitle("FluentGenerateFormLayout --Broderick Labs")
public class FluentGenerateFormLayoutView extends TitleLayout {

    private FluentGenerateFormLayout formLayout;

    public FluentGenerateFormLayoutView() {
        super("Fluent Generate Form");

        Div preview = new DivFactory(new EmptyLayout("no form config data.")).border().width("70%").heightFull().get();

        TextArea data = new TextAreaFactory()
                .placeholder("please paste your form json in there. the generate websites can be enter by top right links.")
                .width("30%").minWidth("20vw").lumoSmall().heightFull().get();

        TextArea value = new TextAreaFactory()
                .placeholder("please paste your form json in there. the generate websites can be enter by top right links.")
                .width("30%").minWidth("20vw").lumoSmall().heightFull().get();

        Div dataLayout = new TitleLayout("Form Config").right(
                new AnchorFactory("https://mrhj.gitee.io/form-generator/#/", "China Generate Server").target("_blank").get(),
                new AnchorFactory("https://jakhuang.github.io/form-generator", "International Generate Server").target("_blank").get(),
                new ButtonFactory("generate", VaadinIcon.CHECK, e -> saveLayout(data.getValue(), preview)).lumoPrimary().lumoTertiaryInline().get()
        ).content(data).asFactory().height("50%").get();

        Div valueLayout = new TitleLayout("Form Config").right(
                new ButtonFactory("set form value", VaadinIcon.EDIT, e -> saveValue(value.getValue())).lumoPrimary().lumoTertiaryInline().get(),
                new ButtonFactory("get form value", VaadinIcon.BOOK, e -> saveLayout(data.getValue(), preview)).lumoPrimary().lumoTertiaryInline().get()
        ).content(data).asFactory().height("50%").get();

        Div config = new DivFactory(data, value).border().width("30%").minWidth("20vw").heightFull().get();

        add(new HorizontalLayoutFactory(config, preview).sizeFull().get());
    }

    private void saveValue(String value) {
        try {
            if (formLayout != null) formLayout.setValue(value);
        } catch (Exception e) {
            new ErrorDialog("Error: " + e.getMessage(), e).build().open();
        }
    }

    private void getValue(TextArea value) {
        try {
            value.setValue(formLayout.getValue());
        } catch (Exception e) {
            new ErrorDialog("Error: " + e.getMessage(), e).build().open();
        }
    }

    private void saveLayout(String config, Div div) {
        try {
            formLayout = FluentGenerateFormLayout.create(config).strictMode(true).build();
        } catch (Exception e) {
            div.removeAll();
            new ErrorDialog("Error: " + e.getMessage(), e).build().open();
            div.add(new EmptyLayout("Error: " + e.getMessage()));
        }
    }
}
