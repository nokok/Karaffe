package org.karaffe.compiler.exceptions;

public class KaraffeCompilerThrowable extends Exception {

    private static final long serialVersionUID = 7527372602471655978L;

    public KaraffeCompilerThrowable() {
        super();
    }

    public KaraffeCompilerThrowable(final String message) {
        super(message);
    }

    public KaraffeCompilerThrowable(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KaraffeCompilerThrowable(final Throwable cause) {
        super(cause);
    }

}
