package org.bklab.flow.layout;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bklab.flow.factory.ImageFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.factory.VerticalLayoutFactory;
import org.bklab.flow.image.ImageBase;

public class EmptyLayout extends Div {

    private final Text text = new Text("");

    public EmptyLayout() {
        this("暂无数据");
    }

    public EmptyLayout(String text) {
        super(new Span());
        VerticalLayout verticalLayout = new VerticalLayoutFactory(
                new ImageFactory(ImageBase.class, "empty.svg").get(),
                new SpanFactory(text).fontSizeS()
                        .style("font-variant", "tabular-nums")
                        .style("font-family", "-apple-system,BlinkMacSystemFont,segoe ui,Roboto,helvetica neue,Arial,noto sans,sans-serif,apple color emoji,segoe ui emoji,segoe ui symbol,noto color emoji")
                        .style("line-height", "1.5715")
                        .style("text-align", "center")
                        .style("color", "rgba(0,0,0,.25)")
                        .style("padding", "0")
                        .style("box-sizing", "border-box")
                        .style("margin", "0")
                        .get()
        ).defaultHorizontalComponentAlignmentCenter().justifyContentModeCenter().sizeFull().get();
        add(verticalLayout);
        setSizeFull();
        this.text.setText(text);
    }

    public EmptyLayout text(String text) {
        this.text.setText(text);
        return this;
    }

    public Text getTextComponent() {
        return text;
    }
}
