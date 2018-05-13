package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.config.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckTargetTask extends AbstractOptionTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTargetTask.class);

    @Override
    public String name() {
        return "check target";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(Options options) {
        if (options.targetName == null) {
            LOGGER.warn("TargetName is null");
            return TaskResult.FAILED;
        } else if (options.targetName.equals("jvm")) {
            LOGGER.debug("JVM Target");
            return TaskResult.SUCCESS;
        }
        LOGGER.warn("Unknown target : {}", options.targetName);
        return TaskResult.FAILED;
    }
}
