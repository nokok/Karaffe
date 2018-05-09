package org.karaffe.compiler.frontend.karaffe.tasks.util;

public enum TaskResult {
    SUCCESS,
    SUCCESS_WITH_WARN,
    NON_RECOVERABLE,;

    public RunnerResult toRunnerResult() {
        if (this == SUCCESS) {
            return RunnerResult.SUCCESS_ALL;
        } else if (this == SUCCESS_WITH_WARN) {
            return RunnerResult.SUCCESS_WITH_WARNING;
        } else if (this == NON_RECOVERABLE) {
            return RunnerResult.FAILED;
        } else {
            throw new IllegalStateException(this.toString());
        }
    }
}
