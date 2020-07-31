package org.bklab.flow.dialog;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.AbstractStreamResource;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.HorizontalLayoutFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.layout.LmrHorizontalLayout;
import org.bklab.flow.layout.TmbVerticalLayout;

public class DownloadDialog extends FluentDialog {
    private final Anchor anchor;
    private final LmrHorizontalLayout bottom;
    private Icon icon;
    private String header;
    private String message;

    public DownloadDialog(String message, AbstractStreamResource streamResource) {
        this.message = message;
        this.anchor = new Anchor(streamResource, null);
        anchor.add(new ButtonFactory().text("下载").clickListener(e -> close()).lumoSmall().lumoPrimary().get());
        this.bottom = new LmrHorizontalLayout();
        setMinHeight("185px");
        setWidth("420px");
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public LmrHorizontalLayout getBottom() {
        return bottom;
    }

    public DownloadDialog icon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public DownloadDialog header(String header) {
        this.header = header;
        return this;
    }

    public DownloadDialog message(String message) {
        this.message = message;
        return this;
    }

    public DownloadDialog build() {
        removeAll();

        if (icon == null) {
            icon = VaadinIcon.DOWNLOAD.create();
            icon.getStyle().set("color", "var(--lumo-primary-color-50pct)");
        }

        icon.getStyle().set("min-width", "1.8em");

        bottom.right(anchor, new ButtonFactory().lumoSmall().text("取消").clickListener(x -> close()).get());
        if (header == null) return buildNoHeader();

        Span message = new Span(this.message);
        Span header = new Span(this.header);
        header.getStyle().set("font-weight", "600").set("font-size", "var(--lumo-font-size-m)");
        message.getStyle().set("font-size", "var(--lumo-font-size-s)").set("padding-left", "3.2em");

        TmbVerticalLayout main = new TmbVerticalLayout().noBorder();
        main.top(new HorizontalLayoutFactory(icon, header).expand(header).widthFull().get());
        main.middle(message);
        main.bottom(bottom);
        main.getStyle().set("padding", "1em 1em 1em 0");
        add(main);

        return this;
    }

    private DownloadDialog buildNoHeader() {
        Span span = new Span(message);
        span.getStyle().set("font-size", "var(--lumo-font-size-s)");
        HorizontalLayout content = new HorizontalLayoutFactory(icon, span).expand(span).widthFull().get();
        VerticalLayout main = new VerticalLayoutFactory(content, bottom).expand(content).widthFull().get();
        main.getStyle().set("padding", "1.5em 1.5em 1em 1.5em");
        add(main);
        return this;
    }

    @Override
    public DownloadDialog get() {
        return this;
    }
}
