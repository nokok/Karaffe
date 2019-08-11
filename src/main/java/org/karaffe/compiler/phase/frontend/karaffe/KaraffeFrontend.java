package org.karaffe.compiler.phase.frontend.karaffe;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.phase.Phases;
import org.karaffe.compiler.phase.SequentialPhases;
import org.karaffe.compiler.util.CompilerContext;

import java.util.Iterator;

public class KaraffeFrontend implements Phases {

  private final Phases phases = new SequentialPhases();

  public KaraffeFrontend() {
    
  }

  @Override
  public void executeAll(CompilerContext context) {
    phases.executeAll(context);
  }

  @Override
  public Iterator<Phase> iterator() {
    return this.phases.iterator();
  }
}
