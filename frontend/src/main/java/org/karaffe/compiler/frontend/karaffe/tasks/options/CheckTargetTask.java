package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.BackendType;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.FrontendType;
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
    public TaskResult run(Options options, CompilerContext context) {
        // frontend
        if (options.frontendName == null) {
            return TaskResult.FAILED;
        } else if (options.frontendName.toLowerCase().equals("karaffe")) {
            LOGGER.debug("Frontend : Karaffe");
            context.setFrontendType(FrontendType.KARAFFE);
        } else {
            Platform.stdErr("Unknown frontend : " + options.frontendName);
            return TaskResult.FAILED;
        }

        // backend
        if (options.backendName == null) {
            return TaskResult.FAILED;
        } else if (options.backendName.toLowerCase().equals("jvm")) {
            LOGGER.debug("Backend : JVM");
            context.setTargetBackendType(BackendType.JVM);
            return TaskResult.SUCCESSFUL;
        } else if (options.backendName.toLowerCase().equals("wasm")) {
            LOGGER.debug("Backend : wasm");
            context.setTargetBackendType(BackendType.WASM);
            return TaskResult.SUCCESSFUL;
        } else {
            Platform.stdErr("Unknown target : " + options.backendName);
            return TaskResult.FAILED;
        }
    }
}
