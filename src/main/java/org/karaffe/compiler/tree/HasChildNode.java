package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.tree.base.Node;

public interface HasChildNode {

    Optional<Node> getChildNode();

}