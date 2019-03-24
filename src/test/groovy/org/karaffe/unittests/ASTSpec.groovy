package org.karaffe.unittests

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser
import org.karaffe.compiler.frontend.karaffe.visitor.KaraffeASTCreateVisitor
import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker
import org.karaffe.compiler.util.CompilerContext
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
    ast.toString() == 'SourceFile ("", [Identifier ("<unknown>", []), DefClass ("", [Identifier ("A", []), SuperClass ("", [TypeName ("Object", [])]), Modifiers ("", [Modifier ("public", [])]), Body ("", [])])])'
  }

  @Unroll
  def "#source"() {
    setup:
    def parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source))))
    def result = parser.expr()
    def context = new CompilerContext()
    def visitor = new KaraffeASTCreateVisitor(context)
    def expr = visitor.visitExpr(result)
    def walker = new FlatApplyWalker()
    walker.walk(expr)

    expect:
    expr.toString() == expectAST

    where:
    source      || expectAST
    "1"         || 'IntLiteral ("1", [])'
    "1 + 1"     || 'Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("1", [])])])])'
    "1 + 2 + 3" || 'Apply ("", [Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("2", [])])])]), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("3", [])])])])' // ((1 + 2) + 3)
    "1 - 2"     || 'Apply ("", [IntLiteral ("1", []), BinOp ("-", []), Arguments ("", [Argument ("", [IntLiteral ("2", [])])])])'
    "1 + 2 - 3" || 'Apply ("", [Apply ("", [IntLiteral ("1", []), BinOp ("+", []), Arguments ("", [Argument ("", [IntLiteral ("2", [])])])]), BinOp ("-", []), Arguments ("", [Argument ("", [IntLiteral ("3", [])])])])' // ((1 + 2) + 3)

  }

  def "expr"() {
    setup:
    def source1 = '''entrypoint {
                   |  def a String = "Hello World"
                   |  println(a)
                   |}'''.stripMargin()
    def parser1 = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source1))))
    def context1 = parser1.entryPointBlock()
    def visitor1 = new KaraffeASTCreateVisitor(new CompilerContext())
    def ast1 = visitor1.visit(context1)

    expect:
    ast1.toString() == 'DefMethod ("", [Identifier ("main", []), Modifiers ("", [Modifier ("public", []), Modifier ("static", [])]), ReturnType ("", [TypeName ("Unit", [])]), Parameters ("", [Parameter ("", [Identifier ("args", []), ArrayTypeName ("java.lang.String", [])])]), Body ("", [DefVar ("", [Identifier ("a", []), TypeName ("String", []), Body ("", [StringLiteral (""Hello World"", [])])]), Apply ("", [Empty ("", []), VarName ("println", []), Arguments ("", [Argument ("", [VarName ("a", [])])])])])])'
  }
}
