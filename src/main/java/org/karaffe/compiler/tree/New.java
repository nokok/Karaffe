package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class New extends AbstractNode {

    public New(final Node newInstanceTarget) {
        super(NodeType.NEW, newInstanceTarget);
    }

    public Node newInstanceTarget() {
        return this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final Name name = context.nextName(nodes);
        Select target = (Select) this.newInstanceTarget();
        nodes.add(new Assign(name, new Select(target.getChildren().get(0), new Name("<init>"))));
        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return "new " + this.newInstanceTarget().vSource();
    }
}
