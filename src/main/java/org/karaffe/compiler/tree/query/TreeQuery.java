package org.karaffe.compiler.tree.query;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import java.util.Optional;

public class TreeQuery {

  private NodeType[] paths;

  public TreeQuery(NodeType... paths) {
    this.paths = paths;
  }

  public Optional<Tree> executeChildren(Tree tree) {
    Tree currentTree = tree;
    try {
      for (NodeType path : paths) {
        currentTree = currentTree.findFirstFromChildren(path).orElseThrow(IllegalStateException::new);
      }
      return Optional.of(currentTree);
    } catch (IllegalStateException e) {
      return Optional.empty();
    }
  }

  public Optional<Tree> executeParent(Tree tree) {
    Tree currentTree = tree;
    NodeType nodeType = paths[0];
    while (true) {
      currentTree = currentTree.getParent();
      if (currentTree == null) {
        return Optional.empty();
      }
      if (currentTree.getNodeType() == nodeType) {
        return Optional.of(currentTree);
      }
    }
  }

  public static TreeQuery buildFrom(NodeType... simpleQuery) {
    return new TreeQuery(simpleQuery);
  }
}
