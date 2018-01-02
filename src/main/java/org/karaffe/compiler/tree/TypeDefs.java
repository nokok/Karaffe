package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class TypeDefs extends AbstractNodes {
    public TypeDefs() {
        this(new ArrayList<>());
    }

    public TypeDefs(final TypeDef def) {
        this(new ArrayList<>(Arrays.asList(def)));
    }

    public TypeDefs(final List<Node> defs) {
        super(NodeType.S_TYPEDEF, defs);
    }

}
