package org.karaffe.compiler.frontend.karaffe.tasks;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.TaskResult;

import java.util.BitSet;

class ErrorListener implements ANTLRErrorListener {

    private TaskResult result = TaskResult.SUCCESS;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.result = TaskResult.FAILED;
        Errors.syntaxError(Position.of(recognizer.getInputStream().getSourceName(), line, charPositionInLine), msg);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {

    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {

    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {

    }

    TaskResult getResult() {
        return this.result;
    }
}
