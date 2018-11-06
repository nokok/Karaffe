package unittests

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser
import spock.lang.Specification

class SyntaxSpec extends Specification {

    private static final ANTLRErrorListener DEFULT_ERROR_LISTENER = new ANTLRErrorListener() {
        @Override
        void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            throw new RuntimeException()
        }

        @Override
        void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
            throw new RuntimeException()
        }

        @Override
        void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
            throw new RuntimeException()
        }

        @Override
        void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
            throw new RuntimeException()
        }
    }

    def "empty source"() {
        setup:
        def source = ""
        def lexer = new KaraffeLexer(CharStreams.fromString(source))
        lexer.addErrorListener(DEFULT_ERROR_LISTENER)
        def parser = new KaraffeParser(new CommonTokenStream(lexer))
        parser.addErrorListener(DEFULT_ERROR_LISTENER)
        def context = parser.compilationUnit()

        expect:
        context != null
    }
}
