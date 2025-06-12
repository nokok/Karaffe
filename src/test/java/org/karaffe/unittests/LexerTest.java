package org.karaffe.unittests;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.util.testing.TestUtil; // Updated import
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LexerTest {

    @ParameterizedTest(name = "tokenTypeTest {index} => source={0}")
    @MethodSource("tokenTypeDataSource")
    void tokenTypeTest(String source, int tokenType) {
        KaraffeLexer lexer = new KaraffeLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(TestUtil.DEFAULT_ERROR_LISTENER);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        stream.consume(); // Load all tokens
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertEquals(tokenType, token.getType());
    }

    static Stream<Arguments> tokenTypeDataSource() {
        return Stream.of(
                Arguments.of("A", KaraffeLexer.Identifier),
                Arguments.of("Hello", KaraffeLexer.Identifier),
                Arguments.of("a", KaraffeLexer.Identifier),
                Arguments.of("z", KaraffeLexer.Identifier),
                Arguments.of("HB", KaraffeLexer.Identifier),
                Arguments.of("H1", KaraffeLexer.Identifier),
                Arguments.of("+", KaraffeLexer.Identifier),
                Arguments.of("-", KaraffeLexer.Identifier),
                Arguments.of("*", KaraffeLexer.Identifier),
                Arguments.of("/", KaraffeLexer.Identifier),
                Arguments.of("!", KaraffeLexer.Identifier),
                Arguments.of("%", KaraffeLexer.Identifier),
                Arguments.of("&", KaraffeLexer.Identifier),
                Arguments.of(">", KaraffeLexer.Identifier),
                Arguments.of("<", KaraffeLexer.Identifier),
                Arguments.of("^", KaraffeLexer.Identifier),
                Arguments.of("~", KaraffeLexer.Identifier),
                Arguments.of(">>", KaraffeLexer.Identifier),
                Arguments.of("1", KaraffeLexer.IntegerLiteral),
                Arguments.of("\"Hello\"", KaraffeLexer.StringLiteral),
                Arguments.of("{", KaraffeLexer.LBRACE),
                Arguments.of("}", KaraffeLexer.RBRACE),
                Arguments.of("entrypoint", KaraffeLexer.ENTRYPOINT),
                Arguments.of("class", KaraffeLexer.CLASS),
                Arguments.of("CLASS", KaraffeLexer.Identifier)
        );
    }

    @ParameterizedTest(name = "keywordIsNotIdentifierTest {index} => source={0}")
    @ValueSource(strings = {"entrypoint", "class", "def", "init", "this"})
    void keywordIsNotIdentifierTest(String source) {
        KaraffeLexer lexer = new KaraffeLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(TestUtil.DEFAULT_ERROR_LISTENER);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        stream.consume();
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertNotEquals(KaraffeLexer.Identifier, token.getType());
    }

    @ParameterizedTest(name = "invalidTokenTypeTest {index} => source={0}")
    @MethodSource("invalidTokenDataSource")
    void invalidTokenTypeTest(String source, String expectedErrorMessage) {
        KaraffeLexer lexer = new KaraffeLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(TestUtil.DEFAULT_ERROR_LISTENER);
        CommonTokenStream stream = new CommonTokenStream(lexer);

        Exception exception = assertThrows(RuntimeException.class, stream::consume);
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    static Stream<Arguments> invalidTokenDataSource() {
        return Stream.of(
                Arguments.of("_", "Syntax Error at 1 : 0 , token recognition error at: '_'"),
                Arguments.of("HOGE_FUGA", "Syntax Error at 1 : 4 , token recognition error at: '_'")
        );
    }

    @ParameterizedTest(name = "stringLiteralTest {index} => source={0}")
    @ValueSource(strings = {"\"\"", "\"1234\"", "\"Hello\""})
    void stringLiteralTest(String source) {
        KaraffeLexer lexer = new KaraffeLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(TestUtil.DEFAULT_ERROR_LISTENER);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        stream.consume();
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertEquals(KaraffeLexer.StringLiteral, token.getType());
    }
}
