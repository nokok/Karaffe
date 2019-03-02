package org.karaffe.compiler.backend;

import org.karaffe.compiler.backend.jvm.JVMBackend;
import org.karaffe.compiler.util.CompilerContext;

public interface Backend {
  static Backend getBackend(CompilerContext context) {
    return new JVMBackend(context);
  }

  String getName();

  void execute();
}
