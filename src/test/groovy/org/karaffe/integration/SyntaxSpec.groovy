package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification
import spock.lang.Unroll

class SyntaxSpec extends Specification {

  private CompilerContext runTest(String source) {
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run"] as String[])
    context.add(KaraffeSource.fromString(source, "Main.krf"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()
    context
  }

  @Unroll
  def "simple"() {
    setup:
    def context = runTest(source)

    expect:
    !context.hasError()

    where:
    source << [
      "class A",
    ]
  }

  @Unroll
  def "invalid"() {
    setup:
    def context = runTest(source)

    expect:
    context.hasError()
    context.outputText == errorMessage

    where:
    source    || errorMessage
    "class +" || '''[ERROR] Name validation failed : ERR_INVALID_JAVA_IDENTIFIER at 1:0:Main.krf
                    |class +
                    |~~~~~~^'''.stripMargin()
  }
}
