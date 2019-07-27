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
}
