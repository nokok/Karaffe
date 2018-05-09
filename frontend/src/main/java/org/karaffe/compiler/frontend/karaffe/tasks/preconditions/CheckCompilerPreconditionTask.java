package org.karaffe.compiler.frontend.karaffe.tasks.preconditions;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCompilerPreconditionTask extends AbstractReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCompilerPreconditionTask.class);

    @Override
    public String name() {
        return "compiler-precondition";
    }

    @Override
    public String description() {
        return "Check precondition";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        try {
            if (!context.cmdLineOptions.skipPackageCheck) {
                LOGGER.trace("Check Package : {}", "karaffe.core");
                Class.forName("karaffe.core.Any");
                Package pkg = Package.getPackage("karaffe.core");
                if (pkg == null) {
                    LOGGER.error("karaffe.core package is not found.");
                    return TaskResult.NON_RECOVERABLE;
                }
                LOGGER.trace("OK");
            }
            triggerSuccess();
            return TaskResult.SUCCESS;
        } catch (ClassNotFoundException e) {
            triggerFailure();
            return TaskResult.NON_RECOVERABLE;
        }
    }

}
