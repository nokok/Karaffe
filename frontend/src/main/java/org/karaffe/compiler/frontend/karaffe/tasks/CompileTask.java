package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;

public class CompileTask extends AbstractTask {

    @Override
    public String name() {
        return "compile";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
