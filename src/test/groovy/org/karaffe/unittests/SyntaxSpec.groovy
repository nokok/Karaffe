package org.karaffe.unittests

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser
import spock.lang.Specification
import spock.lang.Unroll

class SyntaxSpec extends Specification {

    public static final ANTLRErrorListener DEFULT_ERROR_LISTENER = new ANTLRErrorListener() {
        @Override
        void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            throw new RuntimeException("Syntax Error at $line : $charPositionInLine , $msg")
        }

        @Override
        void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
            throw new RuntimeException("reportAmbiguity : $dfa , $startIndex, $stopIndex")
        }

        @Override
        void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
            throw new RuntimeException("reportAttemptingFullContext : $dfa , $startIndex, $stopIndex")
        }

        @Override
        void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
            throw new RuntimeException("reportContextSensitivity : $dfa , $startIndex, $stopIndex")
        }
    }

    def "empty source"() {
        setup:
        def source = ""
        def lexer = new KaraffeLexer(CharStreams.fromString(source))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parser = new KaraffeParser(new CommonTokenStream(lexer))
        parser.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parser.sourceFile()

        expect:
        context != null
    }

    def "classDef"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString("class A"))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "A"
    }

    def "classDef2"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString("class A {}"))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "A"
    }

    def "entryPoint"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(
                """class Main {
                  |  entrypoint {
                  |  }
                  |}""".stripMargin()
        ))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "Main"
        context.typeDefBody().statement(0).entryPointBlock().ENTRYPOINT() != null
    }

    def "nullExpr"() {
        def lexer = new KaraffeLexer(CharStreams.fromString("print()"))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.printFunction()

        expect:
        context != null
        context.expr() == null
    }

    @Unroll
    def "stringLiteral #literal"() {
        def lexer = new KaraffeLexer(CharStreams.fromString(literal))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.literal()

        expect:
        context != null
        if (context.StringLiteral() == null) {
            assert expectedText.isEmpty()
            return
        }

        context.StringLiteral().getText() == expectedText

        where:
        literal              || expectedText
        '""'                 || '""'
        '''"Hello"'''        || '"Hello"'
        '''"Hello World!"''' || '"Hello World!"'
    }

    @Unroll
    def "intLiteral #literal"() {
        def lexer = new KaraffeLexer(CharStreams.fromString(literal))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.literal()

        expect:
        context != null
        if (context.IntegerLiteral() == null) {
            assert expectedText.isEmpty()
            return
        }

        context.IntegerLiteral().getText() == expectedText

        where:
        literal || expectedText
        "0"     || "0"
        "1"     || "1"
        "150"   || "150"
    }

    def "field"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(
                """class Main {
                  |  def i
                  |}""".stripMargin()
        ))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "Main"
        context.typeDefBody().statement(0) != null
    }

    def "constructor"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(
                """class Main {
                  |  init {
                  |  }
                  |}""".stripMargin()
        ))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "Main"
        context.typeDefBody().statement(0)
    }

    def "initialize"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(
                """class Main {
                  |  def i
                  |  init {
                  |    this.i = 0
                  |  }
                  |}""".stripMargin()
        ))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "Main"
        context.typeDefBody().statement(0)
    }


    def "infixOp"() {
        setup:
        def lexer = new KaraffeLexer(CharStreams.fromString(
                """class Main {
                  |  def i
                  |  init {
                  |    1 + 1
                  |    1 plus 1
                  |  }
                  |}""".stripMargin()
        ))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parse = new KaraffeParser(new CommonTokenStream(lexer))
        parse.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parse.classDef()

        expect:
        context != null
        context.Identifier().getText() == "Main"
        context.typeDefBody().statement(0)
    }
}
