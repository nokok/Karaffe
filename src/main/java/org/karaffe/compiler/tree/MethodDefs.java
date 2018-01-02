package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class MethodDefs extends AbstractNodes {

    public MethodDefs() {
        this(new ArrayList<>(0));
    }

    public MethodDefs(final Node method) {
        this(new ArrayList<>(Arrays.asList(method)));
    }

    public MethodDefs(final Node... modifiers) {
        this(new ArrayList<>(Arrays.asList(modifiers)));
    }

    public MethodDefs(final List<Node> modifiers) {
        super(NodeType.S_METHODDEF, modifiers);
    }

}
