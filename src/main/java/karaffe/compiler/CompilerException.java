/**
 * Karaffe Programming Language
 */
package karaffe.compiler;

import karaffe.compiler.phase.parser.Token;
import karaffe.compiler.util.ErrorType;

public class CompilerException extends RuntimeException {

    public CompilerException() {
    }

    public CompilerException(ErrorType type) {
        super(type.toString());
    }

    public CompilerException(ErrorType type, Token t) {
        super(type.toString() + " : " + tokenToString(t));
    }

    public CompilerException(String message) {
        super(message);
    }

    public CompilerException(Throwable cause) {
        super(cause);
    }

    public CompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilerException(ParserException ex) {
        super(ex);
    }

    /**
     * @param t
     *
     * @return
     */
    public static String tokenToString(Token t) {
        StringBuilder sb = new StringBuilder();
        sb.append("Line: ").append(t.beginLine).append("〜").append(t.endLine);
        sb.append("Column: ").append(t.beginColumn).append("〜").append(t.endColumn);
        return sb.toString();
    }
}
