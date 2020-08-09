package org.bklab.flow.dialog.search;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.factory.TextFieldFactory;

public class AdvanceSearchField<E extends Dialog> extends TextField {

    private final E dialog;
    private final Button clearButton;
    private final Button openButton;

    public AdvanceSearchField(E dialog) {
        this.dialog = dialog;
        asFactory().lumoSmall().minWidth("200px").width("40vw").maxWidth("50vw").readOnly().value("高级搜索");
        clearButton = new FluentButton(VaadinIcon.CLOSE.create()).link().asFactory().clickListener(e -> {
            clear();
            e.getSource().setVisible(false);
        }).visible(false).padding("0").get();
        openButton = new FluentButton(VaadinIcon.SEARCH.create()).link().asFactory().enabled(true).padding("0").clickListener(e -> dialog.open()).get();
        addValueChangeListener(e -> clearButton.setVisible(e.getValue() != null));
        dialog.addOpenedChangeListener(e -> openButton.setEnabled(!e.isOpened()));
        setSuffixComponent(new DivFactory(clearButton, openButton).displayFlex().get());
    }

    public E getDialog() {
        return dialog;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getOpenButton() {
        return openButton;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value == null || value.trim().isEmpty() ? "高级搜索" : value);
    }

    public TextFieldFactory asFactory() {
        return new TextFieldFactory(this);
    }
}
