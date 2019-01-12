package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.frontend.Frontend;
import org.karaffe.compiler.frontend.karaffe.walker.TreeSchemaValidator;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.CompilerContext;

import java.util.ArrayList;
import java.util.List;

public class KaraffeFrontend implements Frontend {
  private final CompilerContext context;
  private final List<TreeWalker> walkers = new ArrayList<>();

  public KaraffeFrontend(CompilerContext context) {
    this.context = context;
    this.walkers.add(new TreeSchemaValidator());
  }

  @Override
  public String getName() {
    return "karaffe";
  }

  @Override
  public CompilerContext execute() {
    return this.context;
  }
}
