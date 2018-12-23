package org.karaffe.regression


import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class InvalidEntrypoint extends Specification {

    def "invalidEntryPoint"() {
        setup:
        def context = new CompilerContext()
        context.add(KaraffeSource.fromString(
                """class A {
                  |  def i1
                  |  enrypoint {
                  |    print("Hello World")
                  |  }
                  |}""".stripMargin()))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        !context.hasError()
    }
}
