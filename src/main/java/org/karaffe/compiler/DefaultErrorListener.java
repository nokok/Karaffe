package org.karaffe.compiler;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

public class DefaultErrorListener implements ANTLRErrorListener {

    private final CompilerContext context;

    public DefaultErrorListener(CompilerContext context) {
        this.context = context;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        context.addOutputText("Syntax Error at " + line + ":" + charPositionInLine + ", " + msg);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        throw new IllegalStateException("reportAmbiguity : " + dfa + ", " + startIndex + ", " + stopIndex);
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        throw new IllegalStateException("reportAttemptingFullContext : " + dfa + ", " + startIndex + ", " + stopIndex);
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        throw new IllegalStateException("reportContextSensitivity : " + dfa + ", " + startIndex + ", " + stopIndex);
    }
}
