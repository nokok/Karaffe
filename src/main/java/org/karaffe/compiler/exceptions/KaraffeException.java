package org.karaffe.compiler.exceptions;

public class KaraffeException extends KaraffeCompilerThrowable {
    private static final long serialVersionUID = 5401392614569393218L;

    public KaraffeException() {
        super();
    }

    public KaraffeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KaraffeException(String message) {
        super(message);
    }

    public KaraffeException(Throwable cause) {
        super(cause);
    }

}
