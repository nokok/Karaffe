package org.karaffe.unittests

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.visitor.KaraffeASTCreateVisitor
import spock.lang.Specification
import spock.lang.Unroll

class ASTSpec extends Specification {
    def "simpleClass"() {
        setup:
        def parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString("class A"))))
        def result = parser.sourceFile()
        def visitor = new KaraffeASTCreateVisitor(new CompilerContext())
        def ast = visitor.visit(result)

        expect:
        ast.toString() == 'SourceFile ("<unknown>", [DefClass ("A", [SuperClass ("", [TypeName ("java.lang.Object", [])]), Modifiers ("", [Modifier ("public", [])])])])'
    }

    @Unroll
    def "#source"() {
        setup:
        def parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source))))
        def result = parser.expr()
        def context = new CompilerContext()
        def visitor = new KaraffeASTCreateVisitor(context)
        def expr = visitor.visitExpr(result)

        expect:
        expr.toString() == expectAST

        where:
        source      || expectAST
        "1"         || 'IntLiteral ("1", [])'
        "1 + 1"     || 'Apply ("()", [Select ("+", [IntLiteral ("1", []), IntLiteral ("1", [])])])'
        "1 + 2 + 3" || 'Apply ("()", [Select ("+", [Apply ("()", [Select ("+", [IntLiteral ("1", []), IntLiteral ("2", [])])]), IntLiteral ("3", [])])])' // ((1 + 2) + 3)
        "1 - 2"     || 'Apply ("()", [Select ("-", [IntLiteral ("1", []), IntLiteral ("2", [])])])'
        "1 + 2 - 3" || 'Apply ("()", [Select ("-", [Apply ("()", [Select ("+", [IntLiteral ("1", []), IntLiteral ("2", [])])]), IntLiteral ("3", [])])])' // ((1 + 2) + 3)

    }
}
