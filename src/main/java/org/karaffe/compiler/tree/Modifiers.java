package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Modifiers extends AbstractNodes {

    public Modifiers() {
        this(new ArrayList<>(0));
    }

    public Modifiers(final Modifier modifier) {
        this(new ArrayList<>(Arrays.asList(modifier)));
    }

    public Modifiers(final Modifier... modifiers) {
        this(new ArrayList<>(Arrays.asList(modifiers)));
    }

    public Modifiers(final List<Node> modifiers) {
        super(NodeType.S_MODIFIER, modifiers);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String vSource() {
        return String.join(" ", getChildren().stream().map(Node::vSource).collect(Collectors.toList()));
    }

}
