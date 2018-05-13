package org.karaffe.compiler.base.task;

public enum TaskResult {
    SUCCESS,
    SUCCESS_WITH_WARN,
    NON_RECOVERABLE,;

    public TaskResult ifFailed(Runnable runnable) {
        if (this == NON_RECOVERABLE) {
            runnable.run();
        }
        return this;
    }

    public TaskResult ifSuccess(Runnable runnable) {
        if (this != NON_RECOVERABLE) {
            runnable.run();
        }
        return this;
    }

}
