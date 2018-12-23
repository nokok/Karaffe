package org.karaffe.regression

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

import java.nio.file.Paths

class MissingSourceInfo extends Specification {
    def "reg1"() {
        setup:
        def context = new CompilerContext()
        context.add(KaraffeSource.fromPath(Paths.get("src/test/resources/MissingSourceInfo.krf")))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        context.outputText == """[ERROR] Syntax Error at 1:6:src/test/resources/MissingSourceInfo.krf
class 1
      ^"""
    }
}
