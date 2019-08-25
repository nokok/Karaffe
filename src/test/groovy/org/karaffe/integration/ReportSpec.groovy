package org.karaffe.integration

import org.karaffe.compiler.CompilerConstants
import org.karaffe.compiler.phase.Phases
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.StartupEnv
import spock.lang.Specification

class ReportSpec extends Specification {
  def "compilerVersion"() {
    setup:
    def ctx = CompilerContext.createInitialContext(StartupEnv.create(["--version"] as String[], [:]))
    def phases = Phases.createPhasesFromContext(ctx)
    phases.executeAll(ctx)

    expect:
    ctx.getReports().size() == 1
    ctx.getReports().get(0).getHeader() == "Karaffe compiler version: " + CompilerConstants.VERSION
  }

  def "fileNotFound"() {
    def ctx = CompilerContext.createInitialContext(StartupEnv.create(["Invalid.krf"] as String[], [:]))
    def phases = Phases.createPhasesFromContext(ctx)
    phases.executeAll(ctx)

    expect:
    ctx.getReports().size() == 1
    ctx.getReports().get(0).getHeader() == "FileNotFound : Invalid.krf"
  }
}
