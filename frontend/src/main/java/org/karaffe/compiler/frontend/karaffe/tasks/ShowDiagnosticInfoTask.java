package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.DiagnosticInfo;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;

public class ShowDiagnosticInfoTask extends AbstractTask {
    @Override
    public String name() {
        return "show diag";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Platform.stdOut(DiagnosticInfo.INSTANCE.toString());
        triggerSuccess();
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.cmdLineOptions.showDiag;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
