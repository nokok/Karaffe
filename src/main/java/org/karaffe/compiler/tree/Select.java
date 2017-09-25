package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;
import org.karaffe.compiler.tree.base.TermNode;

public class Select extends AbstractNode {

    private final List<Name> names;

    public Select(final Name name) {
        this(new ArrayList<>(Arrays.asList(name)));
    }

    public Select(final List<Name> names) {
        super(NodeType.SELECT);
        this.names = names;
    }

    @Override
    public String toString() {
        return String.format("(Select %s)", this.toString("/"));
    }

    public String toString(final String delimiter) {
        return String.join(
                delimiter,
                this.names
                        .stream()
                        .map(TermNode::getText)
                        .collect(Collectors.toList()));
    }

}
