package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.frontend.Frontend;
import org.karaffe.compiler.util.CompilerContext;

public class KaraffeFrontend implements Frontend {
  private final CompilerContext context;

  public KaraffeFrontend(CompilerContext context) {
    this.context = context;
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
