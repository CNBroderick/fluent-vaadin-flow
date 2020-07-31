package org.bklab.flow.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.text.TitleLabel;

import java.util.Objects;

@CssImport(value = "./styles/components/layout/fluent-title-layout.css")
public class TitleLayout extends Div {
    private final static String CLASS_NAME = "fluent-title-layout";

    private final ToolBar header = new ToolBar();
    private final Div content = new Div();
    private final TitleLabel title;

    {
        addClassName(CLASS_NAME);
        content.addClassName(CLASS_NAME + "__content");
        header.addClassName(CLASS_NAME + "__header");
        add(header, content);
        content.getElement().getStyle().set("flex-grow", "1");
    }

    public TitleLayout() {
        this.title = null;
    }

    public TitleLayout(String title) {
        this(new TitleLabel(title));
    }

    public TitleLayout(String title, Component... components) {
        this(new TitleLabel(title));
        this.content.add(components);
    }

    public TitleLayout(Component componentTitle, Component... components) {
        this(new TitleLabel(componentTitle));
        this.content.add(components);
    }

    public TitleLayout(Component componentTitle) {
        this(new TitleLabel(componentTitle));
    }

    public TitleLayout(TitleLabel titleLabel) {
        this.title = titleLabel;
        header.left(title);
    }

    public TitleLayout content(Component... components) {
        this.content.add(components);
        return this;
    }

    public ToolBar header() {
        return header;
    }

    public TitleLayout left(Component... components) {
        header.left(components);
        return this;
    }

    public TitleLayout right(Component... components) {
        header.right(components);
        return this;
    }

    public TitleLayout middle(Component... components) {
        header.middle(components);
        return this;
    }

    public TitleLabel getTitleLabel() {
        return Objects.requireNonNull(title, "未设置TitleLabel");
    }

    public TitleLayout sizeFull() {
        setSizeFull();
        return this;
    }

    public TitleLayout heightFull() {
        setHeightFull();
        return this;
    }

    public TitleLayout widthFull() {
        setWidthFull();
        return this;
    }

    public ToolBar getHeader() {
        return header;
    }

    public Div getContent() {
        return content;
    }

    public TitleLayout setContent(Component... components) {
        this.content.removeAll();
        this.content.add(components);
        return this;
    }

    public DivFactory asFactory() {
        return new DivFactory(this);
    }
}
