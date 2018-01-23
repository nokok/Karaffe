package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

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
        super(NodeType.NODELIST, new ArrayList<>(nodes));
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "[" + String.join(",", this.getChildren().stream().map(n -> n.toString()).collect(Collectors.toList())) + "]";
    }

    public List<Node> flatten() {
        List<Node> nodes = new ArrayList<>();
        for (Node node : this.getChildren()) {
            if (node instanceof NodeList) {
                NodeList nl = (NodeList) node;
                nodes.addAll(nl.flatten());
                continue;
            }
            nodes.add(node);
        }

        // Check
        for (Node node : nodes) {
            if (node instanceof NodeList) {
                throw new IllegalStateException();
            }
        }

        return nodes;
    }

    private Node last() {
        int childrenSize = this.getChildren().size();
        if (childrenSize == 0) {
            return new NodeList();
        }
        return this.getChildren().get(childrenSize - 1);
    }

    public Node lastAssignName() {
        Node last = this.last();
        if (last instanceof Assign) {
            Assign assign = (Assign) last;
            return assign.findTarget();
        }
        throw new UnsupportedOperationException(last.getClass().getName());
    }

    @Override
    public String vSource() {
        return String.format("{%s}", getChildren().stream().map(Node::vSource).map(v -> v + ";").reduce((l, r) -> l + r).orElse(""));
    }
}
