package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

public abstract class TopdownTreeWalker extends TreeWalker {

  @Override
  protected void walk1(Tree tree) {
    if (tree == null) {
      onNullTree();
      return;
    }
    onEveryTree(tree);
    invokeEventMethod(tree);
    if (tree.hasChildren()) {
      for (Tree child : tree.getChildren()) {
        walk1(child);
      }
    }
  }

}
