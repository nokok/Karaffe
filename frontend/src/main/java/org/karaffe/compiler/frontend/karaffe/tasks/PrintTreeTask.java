package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;

public class PrintTreeTask extends AbstractReadOnlyTask {
    @Override
    public String name() {
        return "print tree";
    }

    @Override
    public String description() {
        return "Print tree each task";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Platform.stdOut(context);
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isRequired(CompilerContext context) {
        return false;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().printTree;
    }

    @Override
    public boolean isRepetable(CompilerContext context) {
        return context.getCmdLineOptions().printTree;
    }
}
