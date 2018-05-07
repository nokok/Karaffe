package org.karaffe.compiler.frontend.karaffe.transformer;

public class TaskException extends RuntimeException {
    public TaskException() {
    }

    public TaskException(String s) {
        super(s);
    }

    public TaskException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TaskException(Throwable throwable) {
        super(throwable);
    }

    public TaskException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
