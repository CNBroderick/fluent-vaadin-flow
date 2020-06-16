package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasSizeFactory<T extends Component & HasSize, E extends HasSizeFactory<T, E>> extends IFlowFactory<T> {
    default E minWidth(String minWidth) {
        get().setMinWidth(minWidth);
        return (E) this;
    }

    default E sizeFull() {
        get().setSizeFull();
        return (E) this;
    }

    default E sizeUndefined() {
        get().setSizeUndefined();
        return (E) this;
    }

    default E minHeight(String minHeight) {
        get().setMinHeight(minHeight);
        return (E) this;
    }

    default E height(String height) {
        get().setHeight(height);
        return (E) this;
    }

    default E widthFull() {
        get().setWidthFull();
        return (E) this;
    }

    default E maxHeight(String maxHeight) {
        get().setMaxHeight(maxHeight);
        return (E) this;
    }

    default E width(String width) {
        get().setWidth(width);
        return (E) this;
    }

    default E maxWidth(String maxWidth) {
        get().setMaxWidth(maxWidth);
        return (E) this;
    }

    default E heightFull() {
        get().setHeightFull();
        return (E) this;
    }
}
