package org.bklab.flow.base;

import com.vaadin.flow.component.*;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface KeyNotifierFactory<T extends Component & KeyNotifier, E extends KeyNotifierFactory<T, E>> extends IFlowFactory<T> {
    default E keyPressListener(Key key, ComponentEventListener<KeyPressEvent> listener, KeyModifier... modifiers) {
        get().addKeyPressListener(key, listener, modifiers);
        return (E) this;
    }

    default E keyPressListener(ComponentEventListener<KeyPressEvent> listener) {
        get().addKeyPressListener(listener);
        return (E) this;
    }

    default E keyUpListener(Key key, ComponentEventListener<KeyUpEvent> keyUpEvent, KeyModifier... modifiers) {
        get().addKeyUpListener(key, keyUpEvent, modifiers);
        return (E) this;
    }

    default E keyUpListener(ComponentEventListener<KeyUpEvent> keyUpListener) {
        get().addKeyUpListener(keyUpListener);
        return (E) this;
    }

    default E keyDownListener(ComponentEventListener<KeyDownEvent> keyDownListener) {
        get().addKeyDownListener(keyDownListener);
        return (E) this;
    }

    default E keyDownListener(Key key, ComponentEventListener<KeyDownEvent> keyDownEvent, KeyModifier... modifiers) {
        get().addKeyDownListener(key, keyDownEvent, modifiers);
        return (E) this;
    }
}
