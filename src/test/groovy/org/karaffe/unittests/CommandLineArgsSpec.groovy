package org.karaffe.unittests

import org.karaffe.compiler.args.ArgsParser
import org.karaffe.compiler.args.Flag
import spock.lang.Specification
import spock.lang.Unroll

class CommandLineArgsSpec extends Specification {
  def "parse empty"() {
    setup:
    def parser = new ArgsParser()
    def optOption = parser.parse([] as String[])

    expect:
    optOption.isPresent()
    optOption.get().isEmpty()
  }

  @Unroll
  def "flags #input"() {
    setup:
    def parser = new ArgsParser()
    def optOption = parser.parse([input] as String[])

    expect:
    optOption.isPresent()
    optOption.get().hasFlag(flag)

    where:
    input       || flag
    "--dry-run" || Flag.DRY_RUN
    "--version" || Flag.VERSION
  }
}
