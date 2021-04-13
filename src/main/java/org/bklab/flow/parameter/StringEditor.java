package org.bklab.flow.parameter;

import java.util.List;
import java.util.function.Consumer;

class StringEditor implements IParameterEditor {


    @Override
    public boolean supportEdit(ParameterEntry parameterEntry) {
        return false;
    }

    @Override
    public void edit(ParameterEntry parameterEntry) {

    }

    @Override
    public List<Consumer<String>> getSaveListeners() {
        return null;
    }
}
