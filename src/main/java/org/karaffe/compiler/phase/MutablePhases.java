package org.karaffe.compiler.phase;


public interface MutablePhases extends Phases {

  void add(Phase phase);

  default void addAll(Iterable<Phase> phases) {
    for (Phase p : phases) {
      this.add(p);
    }
  }

  void remove(Phase phase);

  void remove(int index);

}
