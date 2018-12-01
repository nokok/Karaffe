package org.karaffe.compiler;

public class KaraffeCompilerRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public KaraffeCompilerRuntimeException() {
    }

    public KaraffeCompilerRuntimeException(String message) {
        super(message);
    }

    public KaraffeCompilerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KaraffeCompilerRuntimeException(Throwable cause) {
        super(cause);
    }

    public KaraffeCompilerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
