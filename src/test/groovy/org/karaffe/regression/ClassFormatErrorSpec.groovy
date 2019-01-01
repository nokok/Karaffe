package org.karaffe.regression

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification

class ClassFormatErrorSpec extends Specification {
    /**
     * java.lang.LinkageError: loader (instance of  org/karaffe/compiler/util/DynamicClassLoader): attempted  duplicate class definition for name: "A"
     *
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
     * 	at org.karaffe.compiler.util.DynamicClassLoader.define(DynamicClassLoader.java:9)
     * 	at org.karaffe.compiler.util.CompilerContext.add(CompilerContext.java:71)
     * 	at org.karaffe.compiler.visitor.KaraffeParserVisitor.visitClassDef(KaraffeParserVisitor.java:33)
     * 	at org.karaffe.compiler.visitor.KaraffeParserVisitor.visitClassDef(KaraffeParserVisitor.java:19)
     * 	at org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser$ClassDefContext.accept(KaraffeParser.java:173)
     * 	at org.antlr.v4.runtime.tree.AbstractParseTreeVisitor.visitChildren(AbstractParseTreeVisitor.java:46)
     * 	at org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor.visitSourceFile(KaraffeBaseVisitor.java:20)
     * 	at org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser$SourceFileContext.accept(KaraffeParser.java:112)
     * 	at org.karaffe.compiler.KaraffeCompiler.lambda$run$1(KaraffeCompiler.java:40)
     * 	at java.util.Optional.ifPresent(Optional.java:159)
     * 	at org.karaffe.compiler.KaraffeCompiler.run(KaraffeCompiler.java:40)
     * 	at org.karaffe.regression.ClassFormatErrorSpec.test(ClassFormatErrorSpec.groovy:33)
     */
    def "test"() {
        setup:
        def context = new CompilerContext()
        context.add(KaraffeSource.fromString("class A"))
        context.add(KaraffeSource.fromString("class A"))
        def compiler = new KaraffeCompiler(context)
        compiler.run()

        expect:
        !context.hasError()
        context.outputText == """[ERROR] Duplicate class : A at 1:0:<unknown>
                                |class A
                                |~~~~~~^""".stripMargin()
    }
}
