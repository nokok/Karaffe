package org.karaffe.compiler.tree.typed;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.Position;

public class Term extends Tree {
  public Term(NodeType nodeType, String name) {
    super(nodeType, name);
  }

  public Term(NodeType nodeType, String name, Position position) {
    super(nodeType, name, position);
  }
}
