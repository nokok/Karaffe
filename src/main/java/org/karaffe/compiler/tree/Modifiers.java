package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class Modifiers extends AbstractNodes {

    public Modifiers() {
        this(new ArrayList<>(0));
    }

    public Modifiers(final List<Node> modifiers) {
        super(NodeType.S_MODIFIER, modifiers);
    }

    public Modifiers(final Modifier modifier) {
        this(new ArrayList<>(Arrays.asList(modifier)));
    }

    public Modifiers(final Modifier... modifiers) {
        this(new ArrayList<>(Arrays.asList(modifiers)));
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

    @Override
    public String vSource() {
        return String.join(" ", this.getChildren().stream().map(Node::vSource).collect(Collectors.toList()));
    }

}
