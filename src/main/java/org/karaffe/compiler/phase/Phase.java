package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

public interface Phase {

  String getName();

  default void execute() {
    execute(CompilerContext.createInitialContext());
  }

  void execute(CompilerContext context);
}
