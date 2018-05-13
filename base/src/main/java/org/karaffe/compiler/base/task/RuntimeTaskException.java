package org.karaffe.compiler.base.task;

public class RuntimeTaskException extends RuntimeException {
    public RuntimeTaskException() {
    }

    public RuntimeTaskException(String s) {
        super(s);
    }

    public RuntimeTaskException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RuntimeTaskException(Throwable throwable) {
        super(throwable);
    }

    public RuntimeTaskException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
