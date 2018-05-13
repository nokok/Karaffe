package org.karaffe.compiler.base.task;

public enum TaskResult {
    SUCCESS,
    SUCCESS_WITH_WARN,
    FAILED,;

    public TaskResult ifFailed(Runnable runnable) {
        if (this == FAILED) {
            runnable.run();
        }
        return this;
    }

    public TaskResult ifSuccess(Runnable runnable) {
        if (this != FAILED) {
            runnable.run();
        }
        return this;
    }

}
