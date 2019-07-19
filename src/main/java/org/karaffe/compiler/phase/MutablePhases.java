package org.karaffe.compiler.phase;


public interface MutablePhases extends Phases {

  void add(Phase phase);

  void remove(Phase phase);

  void remove(int index);

}
