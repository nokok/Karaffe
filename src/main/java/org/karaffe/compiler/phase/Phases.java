package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

import java.util.Optional;

public interface Phases extends Iterable<Phase> {
  static Phases createPhasesFromContext(CompilerContext context) {
    return PhasesFactory.defaultFactory().createPhases(context);
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
