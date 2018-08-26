package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;

public interface MIRTask extends Task {
    @Override
    default TaskResult run(CompilerContext context) {
        return run(context.getIR(), context);
    }

    TaskResult run(IR ir, CompilerContext context);

    @Override
    default boolean isRunnable(CompilerContext context) {
        return context.getIR() != null;
    }
}
