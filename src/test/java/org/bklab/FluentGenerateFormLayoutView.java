/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.FluentGenerateFormLayoutView
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

import java.util.Set;

@Route(value = "form", layout = View.class)
@PageTitle("FluentGenerateFormLayout --Broderick Labs")
public class FluentGenerateFormLayoutView extends TitleLayout {

    private FluentGenerateFormLayout formLayout;

    public FluentGenerateFormLayoutView() {
        super("Fluent Generate Form");

        Div preview = new DivFactory(new EmptyLayout("no form config data.")).border().width("70%").padding("2em 0").heightFull().get();

        TextArea data = new TextAreaFactory()
                .placeholder("please paste your form json in there. the generate websites can be enter by top right links.")
                .widthFull().minWidth("20vw").lumoSmall().height("40vh").get();

        TextArea value = new TextAreaFactory()
                .placeholder("this form value will be display in there.")
                .widthFull().minWidth("20vw").lumoSmall().height("40vh").get();

        Div dataLayout = new TitleLayout("Form Config").right(
                new ButtonFactory("generate", VaadinIcon.CHECK, e -> saveLayout(data.getValue(), preview)).lumoPrimary().get()
        ).content(data).asFactory().height("50%").get();

        Div valueLayout = new TitleLayout("Form Config").right(
                new ButtonFactory("set form value", VaadinIcon.EDIT, e -> saveValue(value.getValue())).lumoPrimary().get(),
                new ButtonFactory("get form value", VaadinIcon.BOOK, e -> getValue(value)).lumoPrimary().get()
        ).content(value).asFactory().height("50%").get();

        Div config = new DivFactory(dataLayout, valueLayout).border().width("30%").minWidth("40em").heightFull().get();

        content(new HorizontalLayoutFactory(config, preview).sizeFull().get()).right(
                new AnchorFactory("https://mrhj.gitee.io/form-generator/#/", "China Generate Server").target("_blank").get(),
                new AnchorFactory("https://jakhuang.github.io/form-generator", "International Generate Server").target("_blank").get()
        );
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
            Set<String> invalids = formLayout.validate();
            if (invalids.isEmpty()) value.setValue(formLayout.getValue());
            else {
                new ErrorDialog("请检查" + String.join("、", invalids) + "是否输入正确。").build().open();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new ErrorDialog("Error: " + e.getMessage(), e).build().open();
        }
    }

    private void saveLayout(String config, Div div) {
        try {
            formLayout = FluentGenerateFormLayout.create(config).strictMode(true).build();
            div.removeAll();
            div.add(formLayout);
        } catch (Exception e) {
            div.removeAll();
            new ErrorDialog("Error: " + e.getMessage(), e).build().open();
            div.add(new EmptyLayout("Error: " + e.getMessage()));
        }
    }
}
