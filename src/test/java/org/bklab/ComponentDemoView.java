package org.bklab;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.text.TitleLabel;

@Route(value = "components", layout = View.class)
@PageTitle("工具栏&对话框 --Broderick Labs")
public class ComponentDemoView extends Div {

    public ComponentDemoView() {

        PaperSlider paperSlider = new PaperSlider();
        add(paperSlider);
        add(new ToolBar()
                .left(new TitleLabel("工具栏"))
                .middle(new TitleLabel("工具栏中"))
                .right(new TitleLabel("工具栏右"))
        );
        add(new ButtonFactory().clickListener(event -> {
            new ModalDialog().title("对话框")
                    .content(new Span("Some contents..."))
                    .content(new Span("Some contents..."))
                    .content(new Span("Some contents..."))
                    .addButton((buttonFactory, modalDialog) ->
                            modalDialog.footerRight(buttonFactory.lumoPrimary().text("保存")
                                    .clickListener(event1 -> modalDialog.close()).get()))
                    .addCloseButton()
                    .width("521px")
                    .open();
        }).text("打开对话框").lumoPrimary().lumoSmall().get());

        add(new EmptyLayout());

        setSizeFull();
//        add( new VerticalLayoutFactory(new SimplyChart())
//                .maxWidth("300px").maxHeight("300px").get());
    }
}
