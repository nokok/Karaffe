package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;

public interface Phases extends Iterable<Phase> {
  static Phases createPhasesFromContext(CompilerContext context) {
    MutablePhases phases = new SequentialPhases();
    if (context.hasFlag(Flag.VERSION)) {

    }
    return phases;
  }

  void executeAll(CompilerContext context);

}
