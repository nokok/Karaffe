package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        super(NodeType.APPLY, new ArrayList<>(Arrays.asList(target)));
    }

}
