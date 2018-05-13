package org.karaffe.compiler.base.task;

public enum RunnerResult {
    SUCCESS_ALL,
    PARTICALLY_SUCCESSFUL,
    SUCCESS_WITH_WARNING,
    FAILED;

    public TaskResult toTaskResult() {
        if (this == SUCCESS_ALL) {
            return TaskResult.SUCCESS;
        }
        if (this == SUCCESS_WITH_WARNING) {
            return TaskResult.SUCCESS_WITH_WARN;
        }
        return TaskResult.FAILED;
    }
}
