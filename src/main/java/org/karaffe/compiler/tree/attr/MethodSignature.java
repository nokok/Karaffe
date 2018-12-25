package org.karaffe.compiler.tree.attr;

import org.karaffe.compiler.util.MethodParameterEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MethodSignature extends Attribute {
    private Class<?> returnClass;
    private List<MethodParameterEntry> parameterEntries;

    public MethodSignature(Class<?> returnClass) {
        this(returnClass, new ArrayList<>(0));
    }

    public MethodSignature(Class<?> returnClass, List<MethodParameterEntry> parameterEntries) {
        this.returnClass = Objects.requireNonNull(returnClass);
        this.parameterEntries = Objects.requireNonNull(parameterEntries);
    }

    @Override
    public String toString() {
        return "MethodSignature=(" + parameterEntries.stream().map(MethodParameterEntry::toString).collect(Collectors.joining(", ")) + ") -> " + returnClass.getCanonicalName();
    }
}
