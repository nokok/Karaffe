package org.karaffe.compiler.base.task;

public enum TaskResult {
    SUCCESS,
    SUCCESS_WITH_WARN,
    RETRY,
    FAILED,;

    public TaskResult ifFailed(Runnable runnable) {
        if (this == FAILED) {
            runnable.run();
        }
        return this;
    }

    public TaskResult ifSuccess(Runnable runnable) {
        if (this != FAILED /*SUCCESS and SUCCESS_WITH_WARN*/) {
            runnable.run();
        }
        return this;
    }

}
