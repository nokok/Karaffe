package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

public interface Phases extends Iterable<Phase> {
  public static Phases createPhasesFromContext(CompilerContext context) {
    return new SequentialPhases(context);
  }

  public void executeAll();

}
