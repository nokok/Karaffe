package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.config.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckLogLevelTask extends AbstractOptionTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckLogLevelTask.class);

    @Override
    public TaskResult run(Options options, CompilerContext context) {
        boolean invalid = false;
        if (options.isDebugLog) {
            invalid = options.isInfoLog || options.isTraceLog;
        } else if (options.isInfoLog) {
            invalid = options.isDebugLog || options.isTraceLog;
        } else if (options.isTraceLog) {
            invalid = options.isDebugLog || options.isInfoLog;
        }
        if (invalid) {
            LOGGER.warn("Failed : {}", options);
            return TaskResult.FAILED;
        } else {
            LOGGER.debug("Passed");
            return TaskResult.SUCCESS;
        }
    }

    @Override
    public String name() {
        return "check log level";
    }
}
