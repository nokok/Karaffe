package org.karaffe.unittests

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ASTSpec extends Specification {
    def "simpleClass"() {
        setup:
        def context = new CompilerContext()
        def source = KaraffeSource.fromString("class A")
        context.add(source)
        def compiler = new KaraffeCompiler(context)
        compiler.run()
        def ast = context.getCurrentAST()

        expect:
        ast.toString() == 'CompilationUnit ("", [], [SourceFile ("<unknown>", [], [DefClass ("A", [SuperClass=java.lang.Object, ModifierAttribute=[PUBLIC]], [])])])'
    }
}
