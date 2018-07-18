package org.karaffe.compiler.base.task.util;

import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.TaskResult;

public class ResultRecorder {
    private int success = 0;
    private int warn = 0;
    private boolean hasError;

    public void record(TaskResult taskResult) {
        if (taskResult == TaskResult.SUCCESSFUL) {
            this.success++;
        } else if (taskResult == TaskResult.FAILED) {
            this.hasError = true;
        } else if (taskResult == TaskResult.SUCCESSFUL_WITH_WARN) {
            this.warn++;
        } else if (taskResult == TaskResult.RETRY) {
            /* no op */
        } else {
            throw new IllegalStateException(taskResult.toString());
        }
    }

    public boolean isSuccessAll() {
        return this.success >= 0 && warn == 0 && !hasError;
    }

    public boolean isSuccessWithWarning() {
        return this.success >= 0 && warn > 0 && !hasError;
    }

    public boolean hasError() {
        return hasError;
    }

    public RunnerResult toRunnerResult() {
        if (this.isSuccessAll()) {
            return RunnerResult.SUCCESS_ALL;
        } else if (this.isSuccessWithWarning()) {
            return RunnerResult.SUCCESS_WITH_WARNING;
        } else if (this.hasError()) {
            return RunnerResult.FAILED;
        }
        throw new IllegalStateException(String.format("success = %d, warn = %d, hasError = %s", this.success, this.warn, this.hasError));
    }
}
