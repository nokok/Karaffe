package org.karaffe.compiler;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.report.ReportCode;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.BitSet;

public class DefaultErrorListener implements ANTLRErrorListener {

  private final CompilerContext context;
  private boolean hasSyntaxError = false;

  public DefaultErrorListener(CompilerContext context) {
    this.context = context;
  }

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
    Position position = new Position(line, charPositionInLine, recognizer);
    context.add(Report.newReport(ReportCode.ERR_SYNTAX).with(position).build());
    this.hasSyntaxError = true;
  }

  @Override
  public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
//        throw new IllegalStateException("reportAmbiguity : " + dfa + ", " + startIndex + ", " + stopIndex);
  }

  @Override
  public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
//        throw new IllegalStateException("reportAttemptingFullContext : " + dfa + ", " + startIndex + ", " + stopIndex);
  }

  @Override
  public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
//        throw new IllegalStateException("reportContextSensitivity : " + dfa + ", " + startIndex + ", " + stopIndex);
  }

  public boolean hasSyntaxError() {
    return this.hasSyntaxError;
  }
}
