package org.karaffe.compiler.frontend.karaffe.transformer;

public class PassAbortedException extends RuntimeException {
    public PassAbortedException() {
    }

    public PassAbortedException(String s) {
        super(s);
    }

    public PassAbortedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PassAbortedException(Throwable throwable) {
        super(throwable);
    }

    public PassAbortedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
