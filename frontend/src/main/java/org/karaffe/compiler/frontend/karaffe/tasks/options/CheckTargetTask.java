package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.base.util.config.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckTargetTask extends AbstractOptionTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTargetTask.class);

    @Override
    public String name() {
        return "check-target";
    }

    @Override
    public TaskResult run(Options options, CompilerContext contextss) {
        if (options.targetName == null) {
            return TaskResult.FAILED;
        } else if (options.targetName.toLowerCase().equals("jvm")) {
            LOGGER.debug("Target : JVM");
            return TaskResult.SUCCESSFUL;
        }
        Platform.stdErr("Unknown target : " + options.targetName);
        return TaskResult.FAILED;
    }
}
