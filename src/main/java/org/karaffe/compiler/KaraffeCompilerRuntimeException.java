package org.karaffe.compiler;

import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class KaraffeCompilerRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Position position = new Position(-1, -1, "<unknown>");

    public void setPosition(Position position) {
        this.position = Objects.requireNonNull(position);
    }

    public Position getPosition() {
        return position;
    }

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
