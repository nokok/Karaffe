package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNode;

public class Select extends AbstractNode {

    public Select(final VarName name) {
        this(new ArrayList<>(Arrays.asList(name)));
    }

    public Select(final List<VarName> names) {
        super(NodeType.SELECT, names);
    }

    public List<VarName> getNames() {
        return (List<VarName>) this.getChildren();
    }

    public String toString(final String delimiter) {
        return String.join(
                delimiter,
                this.getNames()
                        .stream()
                        .map(TermNode::getText)
                        .collect(Collectors.toList()));
    }

}
