package org.karaffe.unittests

import org.karaffe.compiler.util.args.ArgsParser
import org.karaffe.compiler.util.args.Flag
import org.karaffe.compiler.util.args.Options
import org.karaffe.compiler.util.args.ParameterName
import spock.lang.Specification
import spock.lang.Unroll

class ArgSpec extends Specification {

  @Unroll
  def "flagConfiguration"() {
    expect:
    flag.fullName.ifPresent({ fullName ->
      assert fullName.startsWith("--")
    })
    flag.shortName.ifPresent({ shortName ->
      assert shortName.startsWith("-")

      // krfc -
      if (shortName.length() > 1) {
        assert shortName.charAt(1) != '-'.toCharacter()
      }
    })

    where:
    flag << Arrays.asList(Flag.values())
  }

  def "parameterConfiguration"() {
    setup:
    def parameters = ParameterName.values()

    expect:
    for (parameter in parameters) {
      parameter.fullName.ifPresent({ fullName ->
        assert fullName.startsWith("--")
      })
      parameter.shortName.ifPresent({ shortName ->
        assert shortName.startsWith("-")
        assert shortName.charAt(1) != '-'.toCharacter()
      })
    }
  }

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
