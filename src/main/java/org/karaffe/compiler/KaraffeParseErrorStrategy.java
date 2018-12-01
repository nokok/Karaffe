package org.karaffe.compiler;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class KaraffeParseErrorStrategy extends DefaultErrorStrategy {
    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        throw new InputMismatchException(recognizer);
    }

    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw new InputMismatchException(recognizer);
    }
}
