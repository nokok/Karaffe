package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.instructions.IR;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;

public class InstructionSelectTask extends AbstractTask implements MIRTask, NoDescriptionTask {
    @Override
    public TaskResult run(IR instructions, CompilerContext context) {
        return null;
    }

    @Override
    public String name() {
        return "backend-jvm-instsel";
    }

    @Override
    public boolean changed() {
        return false;
    }
}
