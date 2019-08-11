package org.karaffe.unittests

import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.file.Paths

class PathNameSpec extends Specification {
  def "KaraffeSource#getSourceName"() {
    setup:
    def s = KaraffeSource.fromPath(Paths.get("src/test/resources/Main.krf"))

    expect:
    s.getSourceName() == "src/test/resources/Main.krf"
  }
}
