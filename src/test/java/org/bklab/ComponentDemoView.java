/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 17:28:34
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: org.bklab.ComponentDemoView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.bklab.flow.components.selector.button.ButtonSelector;
import org.bklab.flow.components.slider.PaperSlider;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.MessageDialog;
import org.bklab.flow.dialog.ModalDialog;
import org.bklab.flow.dialog.timerange.DateTimeRangeDialog;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.flow.layout.ToolBar;
import org.bklab.flow.text.TitleLabel;

import java.util.function.Consumer;

@RouteAlias("")
@Route(value = "components", layout = View.class)
@PageTitle("工具栏&对话框 --Broderick Labs")
public class ComponentDemoView extends Div {

    public ComponentDemoView() {

        getStyle().set("background-color", "var(--lumo-base-color)");
        Span span = new Span();
        PaperSlider paperSlider = new PaperSlider(0, 60, 49).valueChangeListener(e -> span.setText("当前值：" + e.getValue()));
        add(span, paperSlider);
        span.setText("初始值：" + paperSlider);
        HorizontalLayout layout = new HorizontalLayout();
        DateTimeRangeDialog dateTimeRangeDialog = new DateTimeRangeDialog().build();
        layout.add(new ButtonFactory().lumoSmall().lumoPrimary().text("日期选择对话框").clickListener(e -> {
            try {
                dateTimeRangeDialog.open();
            } catch (Exception exception) {
                new ErrorDialog(exception).build().open();
            }
        }).get());
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

        setSizeFull();
//        add( new VerticalLayoutFactory(new SimplyChart())
//                .maxWidth("300px").maxHeight("300px").get());
    }
}
