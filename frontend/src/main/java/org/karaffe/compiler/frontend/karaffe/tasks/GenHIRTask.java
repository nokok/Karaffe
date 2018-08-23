package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;

public class GenMIRTask2 extends AbstractTask {
    @Override
    public String name() {
        return "frontend-karaffe-genmir";
    }

    @Override
    public String description() {
        return "Generate High-level Intermeidate Representation(HIR)";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        return null;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
