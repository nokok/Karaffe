package org.karaffe.unittests;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.visitor.KaraffeASTCreateVisitor;
import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.tree.Tree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ASTTest {

    @Test
    void simpleClass() {
        KaraffeParser parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString("class A"))));
        KaraffeParser.SourceFileContext result = parser.sourceFile();
        KaraffeASTCreateVisitor visitor = new KaraffeASTCreateVisitor(CompilerContext.createInitialContext());
        Tree ast = visitor.visit(result);

        assertEquals("SourceFile (\"\", [Identifier (\"<unknown>\", []), DefClass (\"\", [Identifier (\"A\", []), SuperClass (\"\", [TypeName (\"Object\", [])]), Modifiers (\"\", [Modifier (\"public\", [])]), Body (\"\", [])])])", ast.toString());
    }

    @ParameterizedTest
    @MethodSource("sourceToAstData")
    void sourceToAstConversion(String source, String expectedAST) {
        KaraffeParser parser = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source))));
        KaraffeParser.ExprContext result = parser.expr();
        CompilerContext context = CompilerContext.createInitialContext();
        KaraffeASTCreateVisitor visitor = new KaraffeASTCreateVisitor(context);
        Tree expr = visitor.visitExpr(result);
        FlatApplyWalker walker = new FlatApplyWalker();
        walker.walk(expr);

        assertEquals(expectedAST, expr.toString());
    }

    static Stream<Arguments> sourceToAstData() {
        return Stream.of(
                Arguments.of("1", "IntLiteral (\"1\", [])"),
                Arguments.of("1 + 1", "Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"1\", [])])])])"),
                Arguments.of("1 + 2 + 3", "Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])"),
                Arguments.of("1 - 2", "Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])])"),
                Arguments.of("1 + 2 - 3", "Apply (\"\", [Apply (\"\", [IntLiteral (\"1\", []), BinOp (\"+\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"2\", [])])])]), BinOp (\"-\", []), Arguments (\"\", [Argument (\"\", [IntLiteral (\"3\", [])])])])")
        );
    }

    // @Test
    // void expr() {
    //     String source1 = "entrypoint {\n" +
    //                      "  def a String = \"Hello World\"\n" +
    //                      "  println(a)\n" +
    //                      "}";
    //     KaraffeParser parser1 = new KaraffeParser(new CommonTokenStream(new KaraffeLexer(CharStreams.fromString(source1))));
    //     KaraffeParser.EntryPointBlockContext context1 = parser1.entryPointBlock();
    //     KaraffeASTCreateVisitor visitor1 = new KaraffeASTCreateVisitor(CompilerContext.createInitialContext());
    //     Tree ast1 = visitor1.visit(context1);
    //     String expectedString = "DefMethod (\"\", [Identifier (\"main\", []), Modifiers (\"\", [Modifier (\"public\", []), Modifier (\"static\", [])]), ReturnType (\"\", [TypeName (\"Unit\", [])]), Parameters (\"\", [Parameter (\"\", [Identifier (\"args\", []), ArrayTypeName (\"java.lang.String\", [])])]), Body (\"\", [DefVar (\"\", [Identifier (\"a\", []), TypeName (\"String\", []), Body (\"\", [StringLiteral (\"Hello World\", [])])]), Apply (\"\", [Empty (\"\", []), VarName (\"println\", []), Arguments (\"\", [Argument (\"\", [VarName (\"a\", [])])])])])])";
    //     assertEquals(expectedString, ast1.toString(), "AST string mismatch. ACTUAL: " + ast1.toString());
    // }
}
