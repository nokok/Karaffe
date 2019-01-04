package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

public class AnonymousTree extends Tree {
  public AnonymousTree(NodeType nodeType, Position position) {
    super(nodeType, "", position);
  }
}
