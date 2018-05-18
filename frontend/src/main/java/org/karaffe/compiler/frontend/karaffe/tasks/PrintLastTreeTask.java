package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;

public class PrintLastTreeTask extends AbstractReadOnlyTask implements NoDescriptionTask {
    @Override
    public String name() {
        return "print lasttree";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Platform.stdOut(context);
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isRequired(CompilerContext context) {
        return context.getCmdLineOptions().printTree;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getState().equals("terminal");
    }
}
