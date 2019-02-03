package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.ManualTreeWalker;
import org.karaffe.compiler.util.CompilerContext;

public class ScopeWalker extends ManualTreeWalker {

  private final CompilerContext context;

  public ScopeWalker(CompilerContext context) {
    this.context = context;
  }

  @Override
  protected void walk1(Tree tree) {
    invokeEventMethod(tree);
  }
}
