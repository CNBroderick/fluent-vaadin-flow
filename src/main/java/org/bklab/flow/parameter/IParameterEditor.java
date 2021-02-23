package org.bklab.flow.parameter;

import org.bklab.flow.util.element.HasSaveListeners;

public interface IParameterEditor extends HasSaveListeners<String, IParameterEditor> {

    boolean supportEdit(ParameterEntry parameterEntry);

    void edit(ParameterEntry parameterEntry);
}
