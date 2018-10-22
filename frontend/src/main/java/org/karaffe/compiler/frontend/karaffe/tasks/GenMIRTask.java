package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;

public class GenMIRTask extends AbstractTask implements ASTTask {

    @Override
    public String name() {
        return "frontend-karaffe-mir";
    }

    @Override
    public String description() {
        return "Generate MIR";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCompilationUnit() != null;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
