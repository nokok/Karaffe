package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

public final class TreeFactory {
  public static Tree newTree(NodeType nodeType) {
    return new SimpleTree(nodeType);
  }

  public static Tree newTree(NodeType nodeType, String name) {
    return new NamedTree(nodeType, name);
  }

  public static Tree newTree(NodeType nodeType, Position position) {
    return new PositionedSimpleTree(nodeType, position);
  }

  public static Tree newTree(NodeType nodeType, String name, Position position) {
    return new NamedPositionTree(nodeType, name, position);
  }
}
