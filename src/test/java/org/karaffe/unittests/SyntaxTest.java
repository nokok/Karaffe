package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
// Corrected import for TestUtil based on its actual new location
import org.karaffe.compiler.util.testing.TestUtil;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SyntaxTest {

    @Test
    void emptySource() {
        KaraffeParser parser = TestUtil.getParser("");
        KaraffeParser.SourceFileContext context = parser.sourceFile();
        assertNotNull(context);
    }

    @Test
    void classDef() {
        KaraffeParser parser = TestUtil.getParser("class A");
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("A", context.Identifier().getText());
    }

    @Test
    void classDef2() {
        KaraffeParser parser = TestUtil.getParser("class A {}");
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("A", context.Identifier().getText());
    }

    @Test
    void entryPoint() {
        String source = "class Main {\n" +
                        "  entrypoint {\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0).entryPointBlock().ENTRYPOINT());
    }

    @Test
    void nullExpr() {
        KaraffeParser parser = TestUtil.getParser("print()");
        KaraffeParser.ExprContext context = parser.expr();
        assertNotNull(context);
        assertNotNull(context.function); // Assuming 'function' is a field in ExprContext
        // The original Spock test had 'context.args == null'.
        // In ANTLR generated parsers, optional elements often result in null if not present,
        // or an empty list if it's a repeated element. Assuming 'args' is a field that can be null.
        // If args is a list, use context.args().isEmpty() or similar.
        // For now, direct null check as per Spock:
        // This might need adjustment based on how 'args' is defined in KaraffeParser.ExprContext
        assertEquals(null, context.args);
    }

    @ParameterizedTest(name = "stringLiteralTest {index} => literal={0}")
    @MethodSource("stringLiteralDataSource")
    void stringLiteralTest(String literal, String expectedText) {
        KaraffeParser parser = TestUtil.getParser(literal);
        KaraffeParser.LiteralContext context = parser.literal();
        assertNotNull(context);
        if (context.StringLiteral() == null) {
            assertTrue(expectedText.isEmpty(), "Expected text to be empty if StringLiteral is null");
            return;
        }
        assertEquals(expectedText, context.StringLiteral().getText());
    }

    static Stream<Arguments> stringLiteralDataSource() {
        return Stream.of(
                Arguments.of("\"\"", "\"\""),
                Arguments.of("\"Hello\"", "\"Hello\""),
                Arguments.of("\"Hello World!\"", "\"Hello World!\"")
        );
    }

    @ParameterizedTest(name = "intLiteralTest {index} => literal={0}")
    @MethodSource("intLiteralDataSource")
    void intLiteralTest(String literal, String expectedText) {
        KaraffeParser parser = TestUtil.getParser(literal);
        KaraffeParser.LiteralContext context = parser.literal();
        assertNotNull(context);
        if (context.IntegerLiteral() == null) {
            assertTrue(expectedText.isEmpty(), "Expected text to be empty if IntegerLiteral is null");
            return;
        }
        assertEquals(expectedText, context.IntegerLiteral().getText());
    }

    static Stream<Arguments> intLiteralDataSource() {
        return Stream.of(
                Arguments.of("0", "0"),
                Arguments.of("1", "1"),
                Arguments.of("150", "150")
        );
    }

    @Test
    void field() {
        String source = "class Main {\n" +
                        "  def i Int\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }

    @Test
    void constructor() {
        String source = "class Main {\n" +
                        "  init {\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }

    @Test
    void initialize() {
        String source = "class Main {\n" +
                        "  def i Int\n" +
                        "  init {\n" +
                        "    this.i := 0\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }

    @Test
    void infixOp() {
        String source = "class Main {\n" +
                        "  def i Int\n" +
                        "  init {\n" +
                        "    1 + 1\n" +
                        "    1 plus 1\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }

    @Test
    void invokeMethod() {
        String source = "class Main {\n" +
                        "  entrypoint {\n" +
                        "    this.hoge()\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }

    @Test
    void simpleInvoke() {
        String source = "class Main {\n" +
                        "  entrypoint {\n" +
                        "    doSomething()\n" +
                        "  }\n" +
                        "}";
        KaraffeParser parser = TestUtil.getParser(source);
        KaraffeParser.ClassDefContext context = parser.classDef();
        assertNotNull(context);
        assertEquals("Main", context.Identifier().getText());
        assertNotNull(context.typeDefBody().statement(0));
    }
}
