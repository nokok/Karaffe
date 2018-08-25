package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;

public class CleanupTask extends AbstractTask implements MIRTask {
    @Override
    public TaskResult run(DeprecatedInstructions instructions, CompilerContext context) {
        /* 今は何もしない */
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-cleanup";
    }

    @Override
    public String description() {
        return "Clean up instructions";
    }

    @Override
    public boolean changed() {
        return true;
    }
}
