package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.DataGenerator;
import com.vaadin.flow.data.provider.HasDataGenerators;
import org.bklab.flow.IFlowFactory;

@SuppressWarnings("unchecked")
public interface HasDataGeneratorsFactory<T, C extends Component & HasDataGenerators<T>,
        E extends HasDataGeneratorsFactory<T, C, E>> extends IFlowFactory<C> {

    default E dataGenerator(DataGenerator<T> dataGenerator) {
        get().addDataGenerator(dataGenerator);
        return (E) this;
    }
}
