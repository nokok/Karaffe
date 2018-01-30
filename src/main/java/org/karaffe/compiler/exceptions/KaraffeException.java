package org.karaffe.compiler.exceptions;

public class KaraffeException extends KaraffeCompilerThrowable {
    private static final long serialVersionUID = 5401392614569393218L;

    public KaraffeException() {
        super();
    }

    public KaraffeException(final String message) {
        super(message);
    }

    public KaraffeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KaraffeException(final Throwable cause) {
        super(cause);
    }

}
