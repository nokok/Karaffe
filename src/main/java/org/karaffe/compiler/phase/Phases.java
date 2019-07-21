package org.karaffe.compiler.phase;

import org.karaffe.compiler.phase.util.ShowVersionPhase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;

import java.util.Optional;

public interface Phases extends Iterable<Phase> {
  static Phases createPhasesFromContext(CompilerContext context) {
    MutablePhases phases = new SequentialPhases();
    if (context.hasFlag(Flag.VERSION)) {
      phases.add(new ShowVersionPhase());
    }
    return phases;
  }

  void executeAll(CompilerContext context);

  default Optional<Phase> getPhase(String phaseName) {
    for (Phase phase : this) {
      if (phase.getName().equals(phaseName)) {
        return Optional.of(phase);
      }
    }
    return Optional.empty();
  }
}
