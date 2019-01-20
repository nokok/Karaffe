package org.karaffe.compiler.tree;

import java.util.Objects;

public class NamedTree extends SimpleTree {
  private String name;

  NamedTree(NodeType nodeType, String name) {
    super(nodeType);
    this.name = Objects.requireNonNull(name);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return String.format("%s (\"%s\", %s)", this.getNodeType().name(), this.name, this.getChildren());
  }
}
