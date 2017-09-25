package org.karaffe.compiler.parser.util;

import java.util.Optional;

import org.karaffe.compiler.tree.base.Node;

public interface NodeContainer {

    public Optional<Node> getNode();
}
