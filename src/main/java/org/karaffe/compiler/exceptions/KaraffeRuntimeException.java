package org.karaffe.compiler.exceptions;

public class KaraffeRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8362986544732517899L;

    public KaraffeRuntimeException() {
        super();
    }

    public KaraffeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KaraffeRuntimeException(String message) {
        super(message);
    }

    public KaraffeRuntimeException(Throwable cause) {
        super(cause);
    }

}
