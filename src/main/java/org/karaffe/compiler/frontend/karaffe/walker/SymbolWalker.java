package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalkerAdapter;

public class SymbolWalker extends TreeWalkerAdapter {
  @Override
  public void onTypeName(Tree tree) {
    Tree typeName = tree;
    Tree owner = tree.getParent();
  }

  @Override
  public void onDefMethod(Tree tree) {
    Tree methodName = tree;
    Tree owner = tree.getParent();
  }

  @Override
  public void onDefClass(Tree tree) {
    Tree clazz = tree;
    Tree owner = tree.getParent();
  }
}
