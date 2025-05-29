package org.karaffe.unittests;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.visitor.KaraffeASTCreateVisitor;
import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.tree.Tree; // Assuming KaraffeASTCreateVisitor.visit() and visitExpr() return this or a subtype
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ASTTest {

    @Test
    void simpleClass() {
        // setup:
        var parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString("class A"))));
        var result = parser.sourceFile(); // Assuming sourceFile() is the correct entry point
        var visitor = new KaraffeASTCreateVisitor(CompilerContext.createInitialContext());
        Tree ast = visitor.visit(result); // Ensure 'Tree' or the actual return type is imported and used

        // expect:
        assertEquals("SourceFile (\"\", [Identifier (\"<unknown>\", []), DefClass (\"\", [Identifier (\"A\", []), SuperClass (\"\", [TypeName (\"Object\", [])]), Modifiers (\"\", [Modifier (\"public\", [])]), Body (\"\", [])])])", ast.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "'1',         'IntLiteral (\"1\", [])'",
        "'1 + 1',     'Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])])])])'",
        "'1 + 2 + 3', 'Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])'", // ((1 + 2) + 3)
        "'1 - 2',     'Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])])'",
        "'1 + 2 - 3', 'Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])'" // ((1 + 2) + 3)
    })
    void testSource(String source, String expectAST) { // Renamed from #source to be a valid Java method name
        // setup:
        var parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source))));
        var result = parser.expr(); // Assuming expr() is the correct entry point for these expressions
        var context = CompilerContext.createInitialContext();
        var visitor = new KaraffeASTCreateVisitor(context);
        Tree exprAst = visitor.visitExpr(result); // Ensure 'Tree' or the actual return type is imported and used
        var walker = new FlatApplyWalker();
        walker.walk(exprAst);

        // expect:
        assertEquals(expectAST, exprAst.toString());
    }

    @Test
    void expr() {
        // setup:
        String source1 = """
                entrypoint {
                  def a String = "Hello World"
                  println(a)
                }
                """.stripIndent().replace("|", ""); // stripMargin equivalent
        var parser1 = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source1))));
        var context1Result = parser1.entryPointBlock(); // Assuming entryPointBlock() is correct
        var visitor1 = new KaraffeASTCreateVisitor(CompilerContext.createInitialContext());
        Tree ast1 = visitor1.visit(context1Result); // Ensure 'Tree' or the actual return type is imported and used

        // expect:
        assertEquals("DefMethod (\"\", [Identifier (\"main\", []), Modifiers (\"\", [Modifier (\"public\", []), Modifier (\"static\", [])]), ReturnType (\"\", [TypeName (\"Unit\", [])]), Parameters (\"\", [Parameter (\"\", [Identifier (\"args\", []), ArrayTypeName (\"java.lang.String\", [])])]), Body (\"\", [DefVar (\"\", [Identifier (\"a\", []), TypeName (\"String\", []), Body (\"\", [StringLiteral (\"\"Hello World\"\", [])])]), Apply (\"\", [Empty (\"\", []), VarName (\"println\", []), Arguments (\"\", [Argument (\"\", [VarName (\"a\", [])])])])])])", ast1.toString());
    }
}
