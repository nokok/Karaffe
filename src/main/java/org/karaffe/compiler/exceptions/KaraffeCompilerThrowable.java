package org.karaffe.compiler.exceptions;

public class KaraffeCompilerThrowable extends Exception {

    private static final long serialVersionUID = 7527372602471655978L;

    public KaraffeCompilerThrowable() {
        super();
    }

    public KaraffeCompilerThrowable(String message, Throwable cause) {
        super(message, cause);
    }

    public KaraffeCompilerThrowable(String message) {
        super(message);
    }

    public KaraffeCompilerThrowable(Throwable cause) {
        super(cause);
    }

}
