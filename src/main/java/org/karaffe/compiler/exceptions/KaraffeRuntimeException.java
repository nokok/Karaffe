package org.karaffe.compiler.exceptions;

public class KaraffeRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8362986544732517899L;

    public KaraffeRuntimeException() {
        super();
    }

    public KaraffeRuntimeException(final String message) {
        super(message);
    }

    public KaraffeRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KaraffeRuntimeException(final Throwable cause) {
        super(cause);
    }

}
