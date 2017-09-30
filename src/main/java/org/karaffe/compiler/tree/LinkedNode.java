package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.tree.base.Node;

public interface LinkedNode {

    Optional<Node> getNextNode();

}