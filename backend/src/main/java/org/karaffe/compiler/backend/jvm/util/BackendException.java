package org.karaffe.compiler.backend.jvm.util;

import org.karaffe.compiler.base.report.Report;

public abstract class BackendException extends Exception {
    public BackendException() {
    }

    public BackendException(String s) {
        super(s);
    }

    public BackendException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BackendException(Throwable throwable) {
        super(throwable);
    }

    public BackendException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }

    public abstract Report getReport();
}
