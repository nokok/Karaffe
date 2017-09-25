package org.karaffe.compiler.tree;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.HasChildNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.base.NodeType;

public class Apply extends AbstractNode implements HasChildNode {

    private final Node selector;
    private final List<Node> args;

    public Apply(final Node selector, final List<Node> args) {
        super(NodeType.APPLY);
        this.selector = selector;
        this.args = args;
    }

    @Override
    public Optional<Node> getChildNode() {
        return Optional.of(this.selector);
    }

    public List<Node> arguments() {
        return this.args;
    }

    @Override
    public String toString() {
        return String.format("(Apply %s %s)", this.selector, this.args);
    }

}
