package org.karaffe.unittests

import org.karaffe.compiler.util.StartupEnv
import spock.lang.Specification

class StartupEnvSpec extends Specification {
  def "commandlineargs"() {
    setup:
    def env = StartupEnv.create(["a"] as String[], [] as Map<String, String>)

    expect:
    env.commandLineArgs[0] == "a"
  }

  def "env"() {
    setup:
    def env = StartupEnv.create([] as String[], [HOGE: "HOGE"])

    expect:
    env.getEnv("HOGE").isPresent()
    !env.getEnv("FUGA").isPresent()
  }
}
