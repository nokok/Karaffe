package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeWalker extends AbstractTreeWalkerAdapter {

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
      List<Tree> children = new ArrayList<>(tree.getChildren());
      for (int i = 0; i < children.size(); i++) {
        walk1(children.get(i));
      }
    }
    invokeEventMethod(tree);
  }

  protected final void bailout() {
    throw new TreeWalkCancellationException();
  }

}
