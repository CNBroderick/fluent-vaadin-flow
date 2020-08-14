package org.bklab.flow.dialog;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;
import org.bklab.flow.factory.ButtonFactory;
import org.bklab.flow.factory.HorizontalLayoutFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.layout.LmrHorizontalLayout;
import org.bklab.flow.layout.TmbVerticalLayout;

import java.io.*;

public class DownloadDialog extends FluentDialog {
    private final Anchor anchor;
    private final LmrHorizontalLayout bottom;
    private Icon icon;
    private String header;
    private String message;
    private Component preview;

    public DownloadDialog(String message, AbstractStreamResource streamResource) {
        this.message = message;
        this.anchor = new Anchor(streamResource, null);
        anchor.add(new ButtonFactory().text("下载").clickListener(e -> close()).lumoSmall().lumoPrimary().get());
        this.bottom = new LmrHorizontalLayout();
        setWidth("420px");
    }

    public DownloadDialog(String message, File file) {
        this(message, new StreamResource(file.getName(), () -> {
            try {
                return new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                new ErrorDialog(e).build().open();
                return null;
            }
        }));
    }

    public DownloadDialog(String message, String fileName, InputStream inputStream) {
        this(message, new StreamResource(fileName, () -> inputStream));
    }

    public DownloadDialog preview(Component preview) {
        this.preview = preview;
        return this;
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

        if (preview != null) {
            preview.getElement().getStyle().set("align-self", "baseline");
            bottom.right(preview);
        }
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
        main.getStyle().set("padding", "1em 1em 1em 0").set("display", "inline-block");
        add(main);

        return this;
    }

    private DownloadDialog buildNoHeader() {
        Span span = new Span(message);
        span.getStyle().set("font-size", "var(--lumo-font-size-s)");
        HorizontalLayout content = new HorizontalLayoutFactory(icon, span).expand(span).widthFull().get();
        VerticalLayout main = new VerticalLayoutFactory(content, bottom).expand(content).widthFull().get();
        main.getStyle().set("padding", "1.5em 1.5em 1em 1.5em").set("display", "inline-block");
        add(main);
        return this;
    }

    @Override
    public DownloadDialog get() {
        return this;
    }
}
