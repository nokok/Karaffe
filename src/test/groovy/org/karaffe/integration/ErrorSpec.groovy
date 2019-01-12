package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ErrorSpec extends Specification {
  def "class 1"() {
    setup:
    def context = new CompilerContext()
    context.add(KaraffeSource.fromString("class 1"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()

    expect:
    context.outputFiles.size() == 0
    context.outputText == """[ERROR] Syntax Error at 1:6:<unknown>
                                |class 1
                                |      ^""".stripMargin()
  }

  def "def i"() {
    setup:
    def context = new CompilerContext()
    context.add(KaraffeSource.fromString("class A { def i }"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()

    expect:
    context.outputFiles.size() == 0
    context.outputText == """[ERROR] Syntax Error at 1:16:<unknown>
                            |class A { def i }
                            |                ^""".stripMargin()
  }
}
