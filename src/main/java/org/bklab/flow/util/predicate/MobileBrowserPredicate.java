package org.bklab.flow.util.predicate;

import com.vaadin.flow.server.WebBrowser;

import java.util.function.Predicate;

public class MobileBrowserPredicate implements Predicate<WebBrowser> {
    @Override
    public boolean test(WebBrowser b) {
        return b.isAndroid() || b.isIPhone() || b.isWindowsPhone();
    }
}
