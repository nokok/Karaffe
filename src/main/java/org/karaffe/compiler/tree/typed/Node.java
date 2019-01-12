package org.karaffe.compiler.tree.typed;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.Position;

public class Node extends Tree {
  public Node(NodeType nodeType) {
    super(nodeType);
  }

  public Node(NodeType nodeType, Position position) {
    super(nodeType, position);
  }
}
