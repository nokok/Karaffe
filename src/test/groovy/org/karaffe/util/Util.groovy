package org.karaffe.util

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser

class Util {
  public static final ANTLRErrorListener DEFULT_ERROR_LISTENER = new ANTLRErrorListener() {
    @Override
    void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
      throw new RuntimeException("Syntax Error at $line : $charPositionInLine , $msg")
    }

    @Override
    void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
    }

    @Override
    void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
    }

    @Override
    void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
    }
  }

  public static KaraffeParser getParser(String source) {
    def lexer = new KaraffeLexer(CharStreams.fromString(source))
    lexer.addErrorListener(DEFULT_ERROR_LISTENER)
    def parse = new KaraffeParser(new CommonTokenStream(lexer))
    parse.addErrorListener(DEFULT_ERROR_LISTENER)
    return parse
  }
}
