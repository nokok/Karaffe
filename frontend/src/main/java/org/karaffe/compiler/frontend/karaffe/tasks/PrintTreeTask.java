package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Platform;

public class PrintTreeTask extends AbstractTask implements ReadOnlyTask {

    private String lastString = "";

    @Override
    public String name() {
        return "frontend-karaffe-print-tree";
    }

    @Override
    public String description() {
        return "Print tree each task";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Platform.stdOut("===" + context.getState() + "===");
        if(lastString.equals(context.getCompilationUnit().toString())) {
            Platform.stdOut("(No change)");
            return TaskResult.SUCCESSFUL;
        }
        lastString = context.getCompilationUnit().toString();
        Platform.stdOut(lastString);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean isRequired(CompilerContext context) {
        return false;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().printTree && context.getCompilationUnit() != null;
    }

    @Override
    public boolean isRepetable(CompilerContext context) {
        return context.getCmdLineOptions().printTree;
    }
}
