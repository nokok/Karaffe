package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckLogLevelTask extends AbstractOptionTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckLogLevelTask.class);

    @Override
    public TaskResult run(Options options) {
        boolean invalid = false;
        if (options.isDebugLog) {
            invalid = options.isInfoLog || options.isTraceLog;
            LOGGER.warn("InvalidLog Level : {}", options);
        }
        if (options.isInfoLog) {
            invalid = options.isDebugLog || options.isTraceLog;
            LOGGER.warn("InvalidLog Level : {}", options);
        }
        if (options.isTraceLog) {
            invalid = options.isDebugLog || options.isInfoLog;
            LOGGER.warn("InvalidLog Level : {}", options);
        }
        if (invalid) {
            triggerFailure();
            return TaskResult.NON_RECOVERABLE;
        } else {
            triggerSuccess();
            return TaskResult.SUCCESS;
        }
    }

    @Override
    public String name() {
        return "check log level";
    }
}
