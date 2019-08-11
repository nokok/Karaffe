package org.karaffe.compiler.phase;

import org.karaffe.compiler.phase.frontend.karaffe.KaraffeFrontend;
import org.karaffe.compiler.phase.util.ShowReportsPhase;
import org.karaffe.compiler.phase.util.ShowUsagePhase;
import org.karaffe.compiler.phase.util.ShowVersionPhase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.ParameterName;

public class DefaultPhasesFactory implements PhasesFactory {

  @Override
  public Phases createPhases(CompilerContext ctx) {
    MutablePhases phases = new SequentialPhases();
    if (ctx.isEmptyArgs() || ctx.hasInvalidArgs() || ctx.hasFlag(Flag.HELP)) {
      phases.add(new ShowUsagePhase(Flag.values(), ParameterName.values()));
    }
    if (ctx.hasFlag(Flag.VERSION)) {
      phases.add(new ShowVersionPhase());
    }
    if (ctx.hasSourceFile()) {
      phases.addAll(new KaraffeFrontend());
    }
    phases.add(new ShowReportsPhase());
    return phases;
  }
}
