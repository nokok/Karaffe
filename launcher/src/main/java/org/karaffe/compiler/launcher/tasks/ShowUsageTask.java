package org.karaffe.compiler.launcher.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.AbstractTask;

public class ShowUsageTask extends AbstractTask implements NoDescriptionTask {
    @Override
    public String name() {
        return "show-usage";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        context.printUsage();
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.hasInvalidCmdLineArg() || context.getCmdLineOptions().showHelp;
    }

    @Override
    public boolean changed() {
        return false;
    }

    @Override
    public boolean isRequired(CompilerContext context) {
        return false;
    }
}
