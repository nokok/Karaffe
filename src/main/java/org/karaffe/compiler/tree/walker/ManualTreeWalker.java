package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

public abstract class ManualTreeWalker extends TreeWalker {

  @Override
  protected abstract void walk1(Tree tree);
}
