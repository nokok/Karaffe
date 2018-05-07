package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.DiagnosticInfo;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

public class ShowDiagnosticInfo implements Task {
    @Override
    public String name() {
        return "show diag";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Result run(CompilerContext context) {
        Platform.stdOut(DiagnosticInfo.INSTANCE.toString());
        return Result.SUCCESS;
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
