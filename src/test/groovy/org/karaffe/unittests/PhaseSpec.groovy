package org.karaffe.unittests

import org.karaffe.compiler.phase.Phase
import org.karaffe.compiler.phase.SequentialPhases
import org.karaffe.compiler.util.CompilerContext
import spock.lang.Specification

class PhaseSpec extends Specification {

  class Phase1 implements Phase {
    private int value = 0

    @Override
    String getName() {
      return null
    }

    @Override
    void execute(CompilerContext context) { this.value = 1 }
  }

  class Phase2 implements Phase {
    private int value = 0

    @Override
    String getName() {
      return null
    }

    @Override
    void execute(CompilerContext context) { this.value = 2 }
  }

  def "testSequentialPhases"() {
    setup:
    def p = new SequentialPhases()
    def ph1 = new Phase1()
    p.add(ph1)
    def ph2 = new Phase2()
    p.add(ph2)
    p.executeAll()

    expect:
    ph1.value == 1
    ph2.value == 2
  }
}
