package org.bklab;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.components.button.FluentButton;
import org.bklab.flow.components.select.MultiSelectComboBox;
import org.bklab.flow.components.selector.ButtonSelector;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.MessageDialog;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.flow.layout.EmptyLayout;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.text.TitleLabel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Route(value = "components", layout = View.class)
@PageTitle("工具栏&对话框 --Broderick Labs")
public class ComponentDemoView extends Div {

    public ComponentDemoView() {

        PaperSlider paperSlider = new PaperSlider();
        add(paperSlider);
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("普通对话框").clickListener(e -> new MessageDialog().message("这是一个则消息").header("提示").build().open()).get());
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("成功对话框").clickListener(e -> new MessageDialog().message("这是一个则成功消息").header("成功").forSuccess().build().open()).get());
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("警告对话框").clickListener(e -> new MessageDialog().message("这是一个则警告消息").header("警告").forWarning().build().open()).get());

        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("无标题普通对话框").clickListener(e -> new MessageDialog().message("这是一个则消息").build().open()).get());
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("无标题成功对话框").clickListener(e -> new MessageDialog().message("这是一个则成功消息").forSuccess().build().open()).get());
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("无标题警告对话框").clickListener(e -> new MessageDialog().message("这是一个则警告消息").forWarning().build().open()).get());

        layout.add(new ButtonFactory().lumoSmall().lumoError().text("错误对话框").clickListener(e ->
                new ErrorDialog(new RuntimeException("错误404. 未找到此网页。")).message("这是一个则警告消息").header("错误").forWarning().build().open()).get());

        layout.add(new ButtonFactory().lumoSmall().lumoError().text("无标题错误对话框").clickListener(e ->
                new ErrorDialog(new RuntimeException("错误404. 未找到此网页。")).message("这是一个则警告消息").forWarning().build().open()).get());

        add(layout);

        add(new ToolBar()
                .left(new TitleLabel("工具栏"))
                .middle(new TitleLabel("工具栏中"))
                .right(new TitleLabel("工具栏右"))
        );
        add(new ButtonFactory().clickListener(event -> {
            new ModalDialog().title("对话框")
                    .content(new Div(new Span("Some contents...")))
                    .content(new Div(new Span("Some contents...")))
                    .content(new Div(new Span("Some contents...")))
                    .addCloseButton()
                    .addSaveButton(buttonClickEvent -> {

                    })
                    .width("521px")
                    .open();
        }).text("打开对话框").lumoPrimary().lumoSmall().get());

        Consumer<String> consumer = s -> new NotificationFactory(s).lumoSuccess().positionTopEnd().duration(3000).open();
        add(new ButtonSelector()
                .add("第1个", e -> consumer.accept("第1个"))
                .add("第2个", e -> consumer.accept("第2个"))
                .add("第3个", e -> consumer.accept("第3个"))
                .add("第4个", e -> consumer.accept("第4个"))
                .add("第5个", e -> consumer.accept("第5个"))
                .add("第6个", e -> consumer.accept("第6个"))
                .activeFirst());

        MultiSelectComboBox<LocalDateTime> objectMultiSelectComboBox = new MultiSelectComboBox<>();
        objectMultiSelectComboBox.addSelectionListener(e -> {
            new NotificationFactory(e.getValue().toString()).duration(3000).positionTopEnd().open();
        });
        objectMultiSelectComboBox.itemComponentGenerator(e ->
                new FluentButton(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(e)));
        objectMultiSelectComboBox.items(IntStream.range(1, 13).mapToObj(r -> LocalDateTime.now().plusMonths(r)));

        add(objectMultiSelectComboBox);
        add(new EmptyLayout());

        setSizeFull();
//        add( new VerticalLayoutFactory(new SimplyChart())
//                .maxWidth("300px").maxHeight("300px").get());
    }
}
