package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.HasMenuItems;

public interface HasMenuItemsFactory<C extends Component & HasMenuItems, E extends HasMenuItemsFactory<C, E>> {
}
