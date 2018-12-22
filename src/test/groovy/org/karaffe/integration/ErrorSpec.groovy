package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ErrorSpec extends Specification {
    def "String+int"() {
        setup:
        def context = new CompilerContext()
        context.add(KaraffeSource.fromString("""class Hoge { entrypoint {print("Hello World" + 1)}}"""))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputFiles.size() == 0
        context.outputText == """[ERROR] 'karaffe.core.String'+'karaffe.core.Int' is not applicable at 1:45:<unknown>
                                |class Hoge { entrypoint {print("Hello World" + 1)}}
                                |                                             ^""".stripMargin()
    }

    def "errorText"() {
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
}
