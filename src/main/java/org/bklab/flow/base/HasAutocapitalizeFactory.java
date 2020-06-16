package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.HasAutocapitalize;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public interface HasAutocapitalizeFactory<T extends Component & HasAutocapitalize, E extends HasAutocapitalizeFactory<T, E>> extends IFlowFactory<T> {
    default E autocapitalize(Autocapitalize autocapitalize) {
        get().setAutocapitalize(autocapitalize);
        return (E) this;
    }
}
