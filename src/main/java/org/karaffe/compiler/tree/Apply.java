package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        super(NodeType.APPLY, new ArrayList<>(Arrays.asList(target)));
    }

    public Apply(final Node target, final Node... args) {
        super(NodeType.APPLY, Arrays.asList(args));
    }

    public Apply(final Node target, final List<Node> args) {
        super(NodeType.APPLY, args);
    }

}
