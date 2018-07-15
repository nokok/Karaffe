package org.karaffe.compiler.frontend.karaffe.tasks.preconditions;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCorePackageTask extends AbstractTask implements ReadOnlyTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCorePackageTask.class);

    @Override
    public String name() {
        return "check-corelib";
    }

    @Override
    public String description() {
        return "Check core packages(karaffe.core ...) and classes(karaffe.core.Any ...)";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        try {
            LOGGER.trace("Check Package : {}", "karaffe.core");
            Class.forName("karaffe.core.Any");
            Package pkg = Package.getPackage("karaffe.core");
            if (pkg == null) {
                LOGGER.error("karaffe.core package is not found.");
                return TaskResult.FAILED;
            }
            LOGGER.trace("OK");
            return TaskResult.SUCCESS;
        } catch (ClassNotFoundException e) {
            return TaskResult.FAILED;
        }
    }
}
