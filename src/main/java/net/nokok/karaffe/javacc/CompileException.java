package net.nokok.karaffe.javacc;

import java.util.List;

public class CompileException extends RuntimeException {

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }

    public CompileException(String message) {
        super(message);
    }

    public CompileException(List<String> lines, Token token) {
        super();

        if ( lines.isEmpty() ) {
            System.out.println("source code not found.");
            return;
        }

        if ( token.beginLine == token.endLine ) {
            String line = lines.get(token.endLine);
            System.out.println(lines.get(token.endLine));
            for ( int i = 0; i < token.endColumn; i++ ) {
                System.out.print(" ");
            }
            System.out.println("^");
        } else if ( token.beginLine < token.endLine ) {
            for ( int i = token.beginLine; i < token.endLine; i++ ) {
                System.out.println(lines.get(i));
            }
        }

        System.out.println("beginc" + token.beginColumn);
        System.out.println("beginl" + token.beginLine);
        System.out.println("endc" + token.endColumn);
        System.out.println("endl" + token.endLine);
        System.out.println("kind" + token.kind);
        System.out.println("next" + token.next);
        System.out.println("sptoken" + token.specialToken);
    }

    public CompileException() {

    }

}
