package org.bklab.flow.parameter;

import org.bklab.flow.layout.tab.FluentTabView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ParameterCrudView extends FluentTabView {

    private final Map<String, List<ParameterEntry>> parameters = new LinkedHashMap<>();

    public ParameterCrudView() {

    }

    public void build(List<ParameterEntry> parameterEntries) {
        Map<String, List<ParameterEntry>> map = parameterEntries.stream()
                .sorted(Comparator.comparingInt(ParameterEntry::getSequence))
                .collect(Collectors.groupingBy(ParameterEntry::getGroup, Collectors.mapping(Function.identity(), Collectors.toList())));


    }

}
