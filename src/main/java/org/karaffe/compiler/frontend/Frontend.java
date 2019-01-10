package org.karaffe.compiler.frontend;

import org.karaffe.compiler.frontend.karaffe.KaraffeFrontend;
import org.karaffe.compiler.util.CompilerContext;

public interface Frontend {
  String getName();

  CompilerContext execute();

  public static Frontend getFrontend(CompilerContext context) {
    return new KaraffeFrontend(context);
  }
}
