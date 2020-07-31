package org.bklab.flow.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.factory.DivFactory;
import org.bklab.flow.factory.FlexLayoutFactory;

@CssImport(value = "./styles/components/layout/tool-bar.css")
public class ToolBar extends FlexLayout {
    private final Div left = new Div();
    private final Div middle = new Div();
    private final Div right = new Div();

    public ToolBar() {
        asFactory()
                .add(left, middle, right)
                .widthFull()
                .flexWrapWrap()
                .alignSelfStart(left)
                .alignSelfCenter(middle)
                .alignSelfEnd(right)
        ;

        asLeftFactory().className("tool-bar-left").displayFlex();
        asMiddleFactory().className("tool-bar-middle").displayFlex().margin("0", "auto");
        asRightFactory().className("tool-bar-right").displayFlex();
    }

    public ToolBar left(Component... components) {
        left.add(components);
        return this;
    }

    public ToolBar middle(Component... components) {
        middle.add(components);
        return this;
    }

    public ToolBar right(Component... components) {
        right.add(components);
        return this;
    }

    public ToolBar clearLeft() {
        left.removeAll();
        return this;
    }

    public ToolBar clearMiddle() {
        middle.removeAll();
        return this;
    }

    public ToolBar clearRight() {
        right.removeAll();
        return this;
    }

    public ToolBar removeLeft(Component... components) {
        left.remove(components);
        return this;
    }

    public ToolBar removeMiddle(Component... components) {
        middle.remove(components);
        return this;
    }

    public ToolBar removeRight(Component... components) {
        right.remove(components);
        return this;
    }

    public Div getLeft() {
        return left;
    }

    public Div getMiddle() {
        return middle;
    }

    public Div getRight() {
        return right;
    }

    public FlexLayoutFactory asFactory() {
        return new FlexLayoutFactory(this);
    }

    public DivFactory asLeftFactory() {
        return new DivFactory(left);
    }

    public DivFactory asMiddleFactory() {
        return new DivFactory(middle);
    }

    public DivFactory asRightFactory() {
        return new DivFactory(right);
    }
}
