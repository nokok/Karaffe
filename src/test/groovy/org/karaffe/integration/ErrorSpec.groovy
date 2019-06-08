package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ErrorSpec extends Specification {
  /*
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

  def "duplicate"() {
    setup:
    def context = new CompilerContext()
    context.add(KaraffeSource.fromString("class A\nclass A"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()

    expect:
    context.outputFiles.size() == 0
    context.outputText == """[ERROR] Duplicate declaration at 2:0:<unknown>
                            |class A
                            |~~~~~~^""".stripMargin()
  }

  def "shadowing"() {
    setup:
    def context = new CompilerContext()
    context.add(KaraffeSource.fromString("""class A {
                                            |  def i Int = 0
                                            |  init {
                                            |    def i Int = 0
                                            |  }
                                            }""".stripMargin()))
    def compiler = new KaraffeCompiler(context)
    compiler.run()

    expect:
    context.outputFiles.size() == 0
    context.outputText == """[ERROR] Shadowing is disabled at 4:4:<unknown>
                            |    def i Int = 0
                            |    ~~~~~~~~~~~~^""".stripMargin()
  }
   */
}
