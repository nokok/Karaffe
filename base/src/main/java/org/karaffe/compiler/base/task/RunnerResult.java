package org.karaffe.compiler.base.task;

public enum RunnerResult {
    SUCCESS_ALL,
    SUCCESS_WITH_WARNING,
    FAILED;

    public TaskResult toTaskResult() {
        if (this == SUCCESS_ALL) {
            return TaskResult.SUCCESSFUL;
        }
        if (this == SUCCESS_WITH_WARNING) {
            return TaskResult.SUCCESSFUL_WITH_WARN;
        }
        return TaskResult.FAILED;
    }
}
