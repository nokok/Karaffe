package org.karaffe.compiler.phase;

import org.karaffe.compiler.util.CompilerContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SequentialPhases implements MutablePhases {

  private final List<Phase> phases = new ArrayList<>();

  public SequentialPhases() {

  }

  public SequentialPhases(CompilerContext context) {

  }

  @Override
  public void executeAll() {
    for (Phase phase : phases) {
      phase.execute();
    }
  }

  @Override
  public void add(Phase phase) {
    this.phases.add(Objects.requireNonNull(phase));
  }

  @Override
  public void remove(Phase phase) {
    this.phases.remove(phase);
  }

  @Override
  public void remove(int index) {
    this.phases.remove(index);
  }

  @Override
  public Iterator<Phase> iterator() {
    return phases.iterator();
  }
}
