package org.karaffe.compiler.phase;

import org.karaffe.compiler.phase.util.ShowReportsPhase;
import org.karaffe.compiler.phase.util.ShowUsagePhase;
import org.karaffe.compiler.phase.util.ShowVersionPhase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.ParameterName;

import java.util.Optional;

public interface Phases extends Iterable<Phase> {
  static Phases createPhasesFromContext(CompilerContext context) {
    MutablePhases phases = new SequentialPhases();
    if (context.hasInvalidArgs() || context.hasFlag(Flag.HELP)) {
      phases.add(new ShowUsagePhase(Flag.values(), ParameterName.values()));
    }
    if (context.hasFlag(Flag.VERSION)) {
      phases.add(new ShowVersionPhase());
    }
    phases.add(new ShowReportsPhase());
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
