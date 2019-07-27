package org.karaffe.integration

import org.karaffe.compiler.phase.Phases
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.StartupEnv
import spock.lang.Specification

class PhasesSpec extends Specification {

  def "version"() {
    setup:
    def ctx = CompilerContext.createInitialContext(StartupEnv.create(["--version"] as String[], [:]))
    def phases = Phases.createPhasesFromContext(ctx)

    expect:
    phases.getPhase("show-version").isPresent()
  }

  def "reporting"() {
    setup:
    def ctx = CompilerContext.createInitialContext(StartupEnv.create([] as String[], [:]))
    def phases = Phases.createPhasesFromContext(ctx)

    expect:
    phases.getPhase("show-reports").isPresent()
  }
}
