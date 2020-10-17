package org.bklab.flow.base;

@SuppressWarnings("unchecked")
public interface HasReturnThis<C extends HasReturnThis<C>> {

    default C thisObject() {
        return (C) this;
    }

}
