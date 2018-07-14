package org.karaffe.compiler.base.task;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.tree.Tree;

public interface CompilationUnitTask extends Task {

    @Override
    default TaskResult run(CompilerContext context) {
        return run(context.getCompilationUnit(), context);
    }

    TaskResult run(Tree compilationUnit, CompilerContext context);

    @Override
    default boolean isRunnable(CompilerContext context) {
        return context.getCompilationUnit() != null;
    }
}
