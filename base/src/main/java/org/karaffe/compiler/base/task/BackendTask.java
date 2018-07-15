package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instructions;

public interface BackendTask extends Task {
    @Override
    default TaskResult run(CompilerContext context) {
        return run(context.getInstructions(), context);
    }

    TaskResult run(Instructions instructions, CompilerContext context);

    @Override
    default boolean isRunnable(CompilerContext context) {
        return context.getInstructions() != null;
    }
}
