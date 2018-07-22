package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.CompilerContextJsonConverter;
import org.karaffe.compiler.base.util.Platform;

public class PrintLastTreeTask extends AbstractTask implements ReadOnlyTask {
    @Override
    public String name() {
        return "frontend-karaffe-print-lasttree";
    }

    @Override
    public String description() {
        return "Print tree after the last task executed";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        String json = CompilerContextJsonConverter.toJson(context);
        Platform.stdOut(json);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean isFinally(CompilerContext context) {
        return true;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCmdLineOptions().showLastTree && context.getCompilationUnit() != null;
    }

}
