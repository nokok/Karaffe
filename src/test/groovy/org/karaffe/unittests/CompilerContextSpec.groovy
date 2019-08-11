package org.karaffe.unittests

import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.StartupEnv
import spock.lang.Specification

class CompilerContextSpec extends Specification {

  def "createContext"() {
    def env = StartupEnv.create([] as String[], [:])
    def ctx = CompilerContext.createInitialContext(env)

    expect:
    ctx != null
  }

  def "context#isEmptyArgs1"() {
    def env = StartupEnv.create([] as String[], [:])
    def ctx = CompilerContext.createInitialContext(env)

    expect:
    ctx.isEmptyArgs()
  }

  def "context#isEmptyArgs2"() {
    def env = StartupEnv.create(["--help"] as String[], [:])
    def ctx = CompilerContext.createInitialContext(env)

    expect:
    !ctx.isEmptyArgs()
  }

}
