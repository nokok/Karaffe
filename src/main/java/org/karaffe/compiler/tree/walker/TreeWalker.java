package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

public abstract class TreeWalker extends AbstractTreeWalker {

  public final void walk(Tree tree) {
    try {
      walk1(tree);
    } catch (TreeWalkCancellationException e) {
      // ignore
    } catch (RuntimeException e) {
      onException(tree, e);
      throw e;
    }
  }

  protected void walk1(Tree tree) {
    if (tree == null) {
      onNullTree();
      return;
    }
    onEveryTree(tree);
    if (tree.hasChildren()) {
      for (Tree child : tree.getChildren()) {
        walk1(child);
      }
    }
    invokeEventMethod(tree);
  }

  protected final void bailout() {
    throw new TreeWalkCancellationException();
  }

}
