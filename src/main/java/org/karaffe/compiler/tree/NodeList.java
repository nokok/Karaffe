package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;

public class NodeList extends AbstractNodes {

    public NodeList() {
        this(new ArrayList<>(0));
    }

    public NodeList(final List<? extends Node> nodes) {
        super(NodeType.NODELIST, new ArrayList<>(nodes));
    }

    public NodeList(final Node node) {
        this(new ArrayList<>(Arrays.asList(node)));
    }

    public NodeList(final Node... nodes) {
        this(new ArrayList<>(Arrays.asList(nodes)));
    }

    public List<Node> flatten() {
        List<Node> nodes = new ArrayList<>();
        for (final Node node : this.getChildren()) {
            if (node instanceof NodeList) {
                final NodeList nl = (NodeList) node;
                nodes.addAll(nl.flatten());
                continue;
            }
            nodes.add(node);
        }

        // Remove Empty Node
        nodes = nodes.stream().filter(Node::isNonEmptyNode).collect(Collectors.toList());

        // Check
        for (final Node node : nodes) {
            if (node instanceof NodeList) {
                throw new IllegalStateException();
            }
        }

        return nodes;
    }

    private Node last() {
        final int childrenSize = this.getChildren().size();
        if (childrenSize == 0) {
            return new NodeList();
        }
        return this.getChildren().get(childrenSize - 1);
    }

    public Node lastAssignName() {
        final Node last = this.last();
        if (last instanceof Assign) {
            final Assign assign = (Assign) last;
            return assign.findTarget();
        }
        throw new UnsupportedOperationException(last.getClass().getName());
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return new NodeList(this.flatten());
    }

    public Node toSimpleNode() {
        if (this.isEmpty()) {
            return new Empty();
        }
        final List<? extends Node> children = this.getChildren();
        final int childrenSize = children.size();
        if (childrenSize == 1) {
            return children.get(0);
        }
        return this;
    }

    @Override
    public String toString() {
        return "[" + String.join(",", this.getChildren().stream().map(n -> n.toString()).collect(Collectors.toList()))
                + "]";
    }

    @Override
    public String vSource() {
        return String.format("{%s}",
                this.getChildren().stream().map(Node::vSource).map(v -> v + ";").reduce((l, r) -> l + r).orElse(""));
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeContext context) {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        List<Node> nodes = this.flatten();
        for (Node node : nodes) {
            if (node.isNamedDef()) {
                NamedDef def = (NamedDef) node;
                context.addDef(def);
            }
            Optional<InferResult> resultOpt = node.tryTypeInference(context);
            if (!resultOpt.isPresent()) {
                continue;
            }
            InferResult inferResult = resultOpt.get();
            if (node.isAssign()) {
                Assign assign = (Assign) node;
                context.updateType(assign.findTarget(), inferResult);
            }
        }
        return context.getInferredType((Name) this.lastAssignName());
    }
}
