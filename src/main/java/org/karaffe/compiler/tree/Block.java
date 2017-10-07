package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Block extends AbstractNode {

    public Block(final Node... nodes) {
        this(new ArrayList<>(Arrays.asList(nodes)));
    }

    public Block(final List<Node> nodes) {
        super(NodeType.BLOCK, nodes);
    }

}
