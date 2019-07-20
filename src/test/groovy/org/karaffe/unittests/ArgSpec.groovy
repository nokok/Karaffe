package org.karaffe.unittests

import org.karaffe.compiler.util.args.ArgsParser
import spock.lang.Specification
import spock.lang.Unroll

class ArgSpec extends Specification {

  @Unroll
  def "testParse #input"() {
    setup:
    def parser = new ArgsParser()
    def opt = parser.parse(input as String[])

    expect:
    opt.toString() == output

    where:
    input         || output
    []            || "Options{}"
    ["--help"]    || "Options{--help}"
    ["--version"] || "Options{--version}"
    ["-g"]        || "Options{-g}"
  }

}
