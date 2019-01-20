package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class NamedPositionTree extends PositionedSimpleTree {
  private final String name;

  NamedPositionTree(NodeType nodeType, String name, Position position) {
    super(nodeType, position);
    this.name = Objects.requireNonNull(name);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("%s (\"%s\", %s)", this.getNodeType().name(), this.name, this.getChildren());
  }
}
