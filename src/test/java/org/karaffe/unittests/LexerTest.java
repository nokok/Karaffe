package org.karaffe.unittests;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ANTLRErrorListener; // Required for custom error listener
import org.antlr.v4.runtime.BaseErrorListener; // Base for a simple error listener
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
// import org.karaffe.compiler.util.KaraffeCompilerException; // Will use RuntimeException
// import org.karaffe.compiler.util.Util; // Will handle error listener directly

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
// import org.junit.jupiter.api.function.Executable; // Not explicitly used

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class LexerTest {

    // Custom error listener to capture syntax errors and throw an exception
    static class TestErrorListener extends BaseErrorListener {
        private List<String> syntaxErrors = new ArrayList<>();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            String errorMessage = String.format("Syntax Error at %d : %d , %s", line, charPositionInLine, msg);
            syntaxErrors.add(errorMessage);
            // To mimic original behavior of throwing, we throw a RuntimeException here.
            // The actual KaraffeCompilerException might have more specific details or a different base.
            throw new RuntimeException(errorMessage); 
        }

        public boolean hasErrors() {
            return !syntaxErrors.isEmpty();
        }

        public String getFirstError() {
            return syntaxErrors.isEmpty() ? null : syntaxErrors.get(0);
        }
    }


    private CommonTokenStream createTokenStream(String source, ANTLRErrorListener errorListener) {
        KaraffeLexer lexer = new KaraffeLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        if (errorListener != null) {
            lexer.addErrorListener(errorListener);
        }
        return new CommonTokenStream(lexer);
    }

    @ParameterizedTest(name = "token Type {0}")
    @CsvSource({
        "A,          " + KaraffeLexer.Identifier,
        "Hello,      " + KaraffeLexer.Identifier,
        "a,          " + KaraffeLexer.Identifier,
        "z,          " + KaraffeLexer.Identifier,
        "HB,         " + KaraffeLexer.Identifier,
        "H1,         " + KaraffeLexer.Identifier,
        "+,          " + KaraffeLexer.Identifier, 
        "-,          " + KaraffeLexer.Identifier, 
        "*,          " + KaraffeLexer.Identifier, 
        "/,          " + KaraffeLexer.Identifier, 
        "!,          " + KaraffeLexer.Identifier, 
        "%,          " + KaraffeLexer.Identifier, 
        "&,          " + KaraffeLexer.Identifier, 
        ">,          " + KaraffeLexer.Identifier, 
        "<,          " + KaraffeLexer.Identifier, 
        "^,          " + KaraffeLexer.Identifier, 
        "~,          " + KaraffeLexer.Identifier, 
        ">>,         " + KaraffeLexer.Identifier, 
        "1,          " + KaraffeLexer.IntegerLiteral,
        // Removed: "'"Hello"',  " + KaraffeLexer.StringLiteral, // Handled by tokenTypeStringLiteral
        "{,          " + KaraffeLexer.LBRACE,
        "},          " + KaraffeLexer.RBRACE,
        "entrypoint, " + KaraffeLexer.ENTRYPOINT,
        "class,      " + KaraffeLexer.CLASS,
        "CLASS,      " + KaraffeLexer.Identifier
    })
    void tokenType(String source, int expectedTokenType) {
        TestErrorListener errorListener = new TestErrorListener();
        CommonTokenStream stream = createTokenStream(source, errorListener);
        stream.consume(); 
        if (errorListener.hasErrors()) {
            fail("Lexer error for source '" + source + "': " + errorListener.getFirstError());
        }
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertEquals(expectedTokenType, token.getType());
    }
    
    @Test
    void tokenTypeStringLiteral() {
        String source = "\"Hello\""; // Corrected: Java string for source "Hello"
        int expectedTokenType = KaraffeLexer.StringLiteral;
        TestErrorListener errorListener = new TestErrorListener();
        CommonTokenStream stream = createTokenStream(source, errorListener);
        stream.consume();
        if (errorListener.hasErrors()) {
            fail("Lexer error for source '" + source + "': " + errorListener.getFirstError());
        }
        Token token = stream.get(0);
        String tokenText = token.getText();
        assertEquals(source, tokenText);
        assertEquals(expectedTokenType, token.getType());
    }


    @ParameterizedTest(name = "keyword {0} is not Identifier")
    @ValueSource(strings = {"entrypoint", "class", "def", "init", "this"})
    void keywordIsNotIdentifier(String source) {
        TestErrorListener errorListener = new TestErrorListener();
        CommonTokenStream stream = createTokenStream(source, errorListener);
        stream.consume();
        if (errorListener.hasErrors()) {
            fail("Lexer error for source '" + source + "': " + errorListener.getFirstError());
        }
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertNotEquals(KaraffeLexer.Identifier, token.getType());
    }

    @ParameterizedTest(name = "invalid token Type {0}")
    @CsvSource({
        "_        , 'Syntax Error at 1 : 0 , token recognition error at: ''_'''",
        "HOGE_FUGA, 'Syntax Error at 1 : 4 , token recognition error at: ''_'''"
    })
    void invalidTokenType(String source, String expectedErrorMessage) {
        TestErrorListener errorListener = new TestErrorListener(); // Error listener will throw
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            // Pass the error listener that throws.
            // The consumption of tokens will trigger syntaxError in the listener if input is invalid.
            createTokenStream(source, errorListener).consume(); 
        });
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @ParameterizedTest(name = "test StringLiteral {0}")
    @ValueSource(strings = {"\"\"", "\"1234\"", "\"Hello\""}) // Corrected Java strings
    void testStringLiteral(String source) {
        TestErrorListener errorListener = new TestErrorListener();
        CommonTokenStream stream = createTokenStream(source, errorListener);
        stream.consume();
        if (errorListener.hasErrors()) {
            fail("Lexer error for source '" + source + "': " + errorListener.getFirstError());
        }
        Token token = stream.get(0);
        String tokenText = token.getText();

        assertEquals(source, tokenText);
        assertEquals(KaraffeLexer.StringLiteral, token.getType());
    }
}
