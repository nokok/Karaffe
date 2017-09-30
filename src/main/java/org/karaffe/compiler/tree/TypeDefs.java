package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;

public class TypeDefs extends AbstractNodes<TypeDef> {
    public TypeDefs() {
        this(new ArrayList<>());
    }

    public TypeDefs(final TypeDef def) {
        this(new ArrayList<>(Arrays.asList(def)));
    }

    public TypeDefs(final List<TypeDef> defs) {
        super(NodeType.S_TYPEDEF, defs);
    }

}
