package org.karaffe.regression

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ISE extends Specification {
  def "1"() {
    setup:
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run"] as String[])
    context.add(KaraffeSource.fromString(
      """class Main {
        |  entrypoint {
        |    def a Int = 1
        |    print(a)
        |  }
        |}""".stripMargin()
    ))
    def compiler = new KaraffeCompiler(context)

    when:
    compiler.run()

    then:
    notThrown(IllegalStateException)

  }
}
