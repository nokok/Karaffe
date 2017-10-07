package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Select extends AbstractNode {

    public Select(final Node name) {
        this(new ArrayList<>(Arrays.asList(name)));
    }

    public Select(final Node... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public Select(final Node target, final Node selector) {
        super(NodeType.SELECT, new ArrayList<>(Arrays.asList(target, selector)));
    }

    public Select(final List<Node> names) {
        super(NodeType.SELECT, names);
    }

    public List<Name> getNames() {
        return (List<Name>) this.getChildren();
    }

    @Override
    public String toString() {
        return String.format("(Select %s)", this.toString(" "));
    }

    public String toString(final String delimiter) {
        return String.join(
                delimiter,
                this.getNames()
                        .stream()
                        .map(t -> t.toString())
                        .collect(Collectors.toList()));
    }

}
