package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class NodeList extends AbstractNodes {

    public NodeList() {
        this(new ArrayList<>(0));
    }

    public NodeList(Node node) {
        this(new ArrayList<>(Arrays.asList(node)));
    }

    public NodeList(Node... nodes) {
        this(new ArrayList<>(Arrays.asList(nodes)));
    }

    public NodeList(List<? extends Node> nodes) {
        super(NodeType.NODELIST, nodes);
    }

}
