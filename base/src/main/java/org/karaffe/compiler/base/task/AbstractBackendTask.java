package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instructions;

public abstract class AbstractBackendTask extends AbstractTask {
    @Override
    public TaskResult run(CompilerContext context) {
        return run(context.getInstructions());
    }

    public abstract TaskResult run(Instructions instructions);

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getInstructions() != null;
    }
}
