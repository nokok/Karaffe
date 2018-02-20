package org.karaffe.compiler.tree.v2.names;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class TypeName extends AbstractTree {
    private final SimpleName name;
    private final List<TypeName> parameterizedType;

    public TypeName(SimpleName name) {
        this(name, new ArrayList<>(0));
    }

    public TypeName(SimpleName name, List<TypeName> parameterizedType) {
        this.name = name;
        this.parameterizedType = new ArrayList<>(parameterizedType);
    }

    @Override
    public String toString() {
        if (this.parameterizedType.isEmpty()) {
            return this.name.toString();
        }
        return String.format("%s[%s]",
                this.name,
                String.join(", ", this.parameterizedType.stream().map(TypeName::toString).collect(Collectors.toList())));
    }
}
