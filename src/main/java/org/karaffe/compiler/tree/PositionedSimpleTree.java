package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class PositionedSimpleTree extends SimpleTree {
  private final Position position;

  PositionedSimpleTree(NodeType nodeType, Position position) {
    super(nodeType);
    this.position = Objects.requireNonNull(position);
  }

  @Override
  public Position getPosition() {
    return this.position;
  }
}
