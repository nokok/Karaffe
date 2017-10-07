package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        this(target, new ArrayList<>());
    }

    public Apply(final Node target, final Node... args) {
        this(target, Arrays.asList(args));
    }

    public Apply(final Node target, final List<Node> args) {
        super(NodeType.APPLY, target);
        args.stream().forEach(this::addChild);
    }

}
