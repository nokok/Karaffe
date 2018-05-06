package org.karaffe.compiler.frontend.karaffe.transformer;

public class PhaseException extends RuntimeException {
    public PhaseException() {
    }

    public PhaseException(String s) {
        super(s);
    }

    public PhaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PhaseException(Throwable throwable) {
        super(throwable);
    }

    public PhaseException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
