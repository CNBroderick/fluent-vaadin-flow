package org.bklab.flow.layout;

import com.vaadin.flow.component.html.Div;
import org.bklab.flow.factory.DivFactory;

public class FlexDrawerLayout extends Div {

    private final ToolBar header = new ToolBar();
    private final Div leftContent = new Div();
    private final Div rightContent = new Div();
    private final Div content = new Div(leftContent, rightContent);
    private final ToolBar footer = new ToolBar();

    public FlexDrawerLayout() {
        rightContent.setVisible(false);
        header.setVisible(false);
        footer.setVisible(false);

        content.setSizeFull();
        new DivFactory(content).flexGrow(1).displayFlex();
        rightContent.setMaxWidth("50%");
        rightContent.setHeightFull();
        leftContent.setSizeFull();

        header.setWidthFull();
        footer.setWidthFull();
    }

    public ToolBar getHeader() {
        return header;
    }

    public Div getLeftContent() {
        return leftContent;
    }

    public Div getRightContent() {
        return rightContent;
    }

    public Div getContent() {
        return content;
    }

    public ToolBar getFooter() {
        return footer;
    }

}
