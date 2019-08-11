package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

public interface PhasesFactory {
  Phases createPhases(CompilerContext ctx);

  static PhasesFactory defaultFactory() {
    return new DefaultPhasesFactory();
  }
}
