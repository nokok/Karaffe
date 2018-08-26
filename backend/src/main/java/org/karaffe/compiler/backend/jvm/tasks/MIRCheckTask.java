package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;

public class MIRCheckTask extends AbstractTask implements MIRTask, ReadOnlyTask {

    @Override
    public TaskResult run(IR ir, CompilerContext context) {
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-mircheck";
    }

    @Override
    public String description() {
        return "MIR validation";
    }

}
