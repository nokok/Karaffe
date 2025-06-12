package org.karaffe.compiler.util.testing;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

public class TestUtil {

    public static final ANTLRErrorListener DEFAULT_ERROR_LISTENER = new ANTLRErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            // In Java, string formatting is different. Using String.format or simple concatenation.
            // The original Groovy used GString: "Syntax Error at $line : $charPositionInLine , $msg"
            throw new RuntimeException(String.format("Syntax Error at %d : %d , %s", line, charPositionInLine, msg));
        }

        @Override
        public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
            // Empty implementation as in Groovy version
        }

        @Override
        public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
            // Empty implementation as in Groovy version
        }

        @Override
        public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
            // Empty implementation as in Groovy version
        }
    };

    public static org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser getParser(String source) {
        org.antlr.v4.runtime.CharStream charStream = org.antlr.v4.runtime.CharStreams.fromString(source);
        org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer lexer = new org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer(charStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(DEFAULT_ERROR_LISTENER);
        org.antlr.v4.runtime.CommonTokenStream tokenStream = new org.antlr.v4.runtime.CommonTokenStream(lexer);
        org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser parser = new org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(DEFAULT_ERROR_LISTENER);
        return parser;
    }
}
