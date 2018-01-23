package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Block extends AbstractNode {

    public Block(final Node... nodes) {
        this(new ArrayList<>(Arrays.asList(nodes)));
    }

    public Block(final List<Node> nodes) {
        super(NodeType.BLOCK, nodes);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<Node> findBlockBody() {
        return new ArrayList<>(getChildren());
    }

    @Override
    public String vSource() {
        return String.format("{%s}", String.join(";", findBlockBody().stream().map(Node::vSource).collect(Collectors.toList())));
    }
}
