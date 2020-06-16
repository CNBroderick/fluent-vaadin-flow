package org.bklab.flow.factory;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.ClickNotifierFactory;
import org.bklab.flow.base.HasStyleFactory;

public class IconFactory extends FlowFactory<Icon, IconFactory> implements
        HasStyleFactory<Icon, IconFactory>,
        ClickNotifierFactory<Icon, IconFactory> {

    public IconFactory() {
        this(new Icon());
    }

    public IconFactory(Icon component) {
        super(component);
    }

    public IconFactory(VaadinIcon icon) {
        this(new Icon(icon));
    }

    public IconFactory(String icon) {
        this(new Icon(icon));
    }

    @Deprecated
    public IconFactory(String collection, String icon) {
        this(new Icon(collection, icon));
    }

    public IconFactory size(String size) {
        get().setSize(size);
        return this;
    }

    public IconFactory color(String color) {
        get().setColor(color);
        return this;
    }
}
