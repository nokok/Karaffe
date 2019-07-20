package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

public interface Phase {

  String getName();

  void execute(CompilerContext context);
}
