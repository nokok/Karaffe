/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.excptn;

import net.nokok.karaffe.parser.Token;
import net.nokok.karaffe.parser.util.ErrorType;

public class KaraffeCompilerException extends RuntimeException {

    public KaraffeCompilerException() {
    }

    public KaraffeCompilerException(ErrorType type) {
        super(type.toString());
    }

    public KaraffeCompilerException(ErrorType type, Token t) {
        super(type.toString() + " : " + tokenToString(t));
    }

    public KaraffeCompilerException(String message) {
        super(message);
    }

    public KaraffeCompilerException(Throwable cause) {
        super(cause);
    }

    public KaraffeCompilerException(String message, Throwable cause) {
        super(message, cause);
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
