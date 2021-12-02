/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 16:59:42
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.flow.dialog.timerange.DateTimeRangeDialogField
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.dialog.timerange;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import org.bklab.flow.base.HasFlowFactory;
import org.bklab.flow.dialog.crud.Buildable;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.TextFieldFactory;

@Getter
public class DateTimeRangeDialogField extends TextField implements HasFlowFactory<TextField, TextFieldFactory>, Buildable<DateTimeRangeDialogField> {

    private final TextFieldFactory factory = new TextFieldFactory(this);
    private final DateTimeRangeDialog dateTimeRangeDialog;
    private Button clearButton;
    private Button openButton;

    public DateTimeRangeDialogField(DateTimeRangeDialog dateTimeRangeDialog) {
        this.dateTimeRangeDialog = dateTimeRangeDialog;
    }

    @Override
    public TextFieldFactory asFactory() {
        return factory;
    }

    @Override
    public DateTimeRangeDialogField build() {
        clearButton = new ButtonFactory().icon(VaadinIcon.CLOSE_SMALL).tooltip("选择全部时间。").lumoSmall().lumoIcon().lumoTertiaryInline().get();
        openButton = new ButtonFactory().icon(VaadinIcon.CLOCK).tooltip("打开“选择时间范围”对话框。").lumoSmall().lumoIcon().lumoTertiaryInline().get();
        factory.lumoSmall().width("12em").readOnly().clearButtonVisible(false);
        dateTimeRangeDialog.addValueChangeListener(event -> refreshValue());
        setSuffixComponent(new Span(clearButton, openButton));

        clearButton.addClickListener(event -> {
            dateTimeRangeDialog.setToGlobalValue();
            dateTimeRangeDialog.rebuild();
            refreshValue();
        });

        openButton.addClickListener(event -> dateTimeRangeDialog.open());
        refreshValue();

        return this;
    }

    public void refreshValue() {
        ITimeRangeSupplier value = dateTimeRangeDialog.getValue();
        clearButton.setVisible(value != null && dateTimeRangeDialog.globalMaxDuration() == null);
        setValue(value == null ? "全部时间范围" : value.getLabel());
        factory.tooltip(value == null ? null : "[" + value.getName() + "]" + value.getLabel());
    }

}
