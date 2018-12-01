package org.karaffe.unittests

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import spock.lang.Specification
import spock.lang.Unroll

class LexerSpec extends Specification {

    @Unroll
    def "token Type #source"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(source))
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxSpec.DEFULT_ERROR_LISTENER)
        def stream = new CommonTokenStream(lexer)
        stream.consume()
        def token = stream.get(0)
        def tokenText = token.getText()

        expect:
        tokenText == source
        token.getType() == tokenType

        where:
        source       || tokenType
        "A"          || KaraffeLexer.Identifier
        "Hello"      || KaraffeLexer.Identifier
        "a"          || KaraffeLexer.Identifier
        "z"          || KaraffeLexer.Identifier
        "HB"         || KaraffeLexer.Identifier
        "H1"         || KaraffeLexer.Identifier
        "1"          || KaraffeLexer.IntegerLiteral
        '"Hello"'    || KaraffeLexer.StringLiteral
        "{"          || KaraffeLexer.LBRACE
        "}"          || KaraffeLexer.RBRACE
        "+"          || KaraffeLexer.PLUS
        "entrypoint" || KaraffeLexer.ENTRYPOINT
        "class"      || KaraffeLexer.CLASS
        "CLASS"      || KaraffeLexer.Identifier
    }

    @Unroll
    def "invalid token Type #source"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(source))
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxSpec.DEFULT_ERROR_LISTENER)
        def stream = new CommonTokenStream(lexer)
        String errorMessage = ""
        try {
            stream.consume()
            assert false: "assertion failed ${source}"
        } catch (RuntimeException e) {
            errorMessage = e.getMessage()
        }

        expect:
        errorMessage == expectedErrorMessage

        where:
        source      || expectedErrorMessage
        "_"         || "Syntax Error at 1 : 0 , token recognition error at: '_'"
        "HOGE_FUGA" || "Syntax Error at 1 : 4 , token recognition error at: '_'"
        "100,000"   || "Syntax Error at 1 : 3 , token recognition error at: ','"
    }

    @Unroll
    def "test StringLiteral #source"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(source))
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxSpec.DEFULT_ERROR_LISTENER)
        def stream = new CommonTokenStream(lexer)
        stream.consume()
        def token = stream.get(0)
        def tokenText = token.getText()

        expect:
        tokenText == source
        token.getType() == KaraffeLexer.StringLiteral

        where:
        source << [
                '""',
                '"1234"',
                '"Hello"'
        ]
    }
}
