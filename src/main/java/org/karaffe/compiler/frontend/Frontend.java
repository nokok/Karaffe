package org.karaffe.compiler.frontend;

import org.karaffe.compiler.frontend.karaffe.KaraffeFrontend;
import org.karaffe.compiler.util.CompilerContext;

public interface Frontend {
  static Frontend getFrontend(CompilerContext context) {
    return new KaraffeFrontend(context);
  }

  String getName();

  CompilerContext execute();
}
