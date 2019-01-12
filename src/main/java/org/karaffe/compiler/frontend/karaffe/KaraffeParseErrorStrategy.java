package org.karaffe.compiler.frontend.karaffe;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

public class KaraffeParseErrorStrategy extends DefaultErrorStrategy {
  private int firstErrorTokenIndex = -1;

  @Override
  public void recover(Parser recognizer, RecognitionException e) {
    int errIndex = recognizer.getInputStream().index();
    if (firstErrorTokenIndex == -1) {
      firstErrorTokenIndex = errIndex;
    }
    TokenStream input = recognizer.getInputStream();
    if (input.index() < input.size() - 1) {
      recognizer.consume();
    } else {
      throw new InputMismatchException(recognizer);
    }
  }

  @Override
  public Token recoverInline(Parser recognizer) throws RecognitionException {
    throw new InputMismatchException(recognizer);
  }

  @Override
  public void sync(Parser recognizer) {
  }
}
