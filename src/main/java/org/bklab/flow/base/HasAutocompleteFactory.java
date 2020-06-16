package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.HasAutocomplete;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasAutocompleteFactory<T extends Component & HasAutocomplete, E extends HasAutocompleteFactory<T, E>> extends IFlowFactory<T> {
    default E autocomplete(Autocomplete autocomplete) {
        get().setAutocomplete(autocomplete);
        return (E) this;
    }

}
