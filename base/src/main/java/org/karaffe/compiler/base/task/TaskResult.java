package org.karaffe.compiler.base.task;

public enum TaskResult {
    SUCCESSFUL,
    SUCCESSFUL_WITH_WARN,
    RETRY,
    FAILED,
    ;

    public TaskResult ifFailed(Runnable runnable) {
        if (this == FAILED) {
            runnable.run();
        }
        return this;
    }

    public TaskResult ifSuccess(Runnable runnable) {
        if (this != FAILED /*SUCCESSFUL, SUCCESSFUL_WITH_WARN or RETRY*/) {
            runnable.run();
        }
        return this;
    }

}
