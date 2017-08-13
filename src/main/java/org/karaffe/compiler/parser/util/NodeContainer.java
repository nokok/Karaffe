package org.karaffe.compiler.parser.util;

import java.util.Optional;

import org.karaffe.compiler.tree.base.NodeD;

public interface NodeContainer {

    public Optional<NodeD> getNode();
}
