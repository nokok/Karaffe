package org.karaffe.compiler.tree;

import java.util.List;

import org.karaffe.compiler.tree.base.NodeD;

public class Arguments implements NodeD {

    private final List<Argument> arguments;

    public Arguments(final List<Argument> arguments) {
        this.arguments = arguments;
    }

    public List<Argument> getArguments() {
        return this.arguments;
    }
}
