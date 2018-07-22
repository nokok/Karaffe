package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;

public class TypeCheckTask extends AbstractTask implements ReadOnlyTask {
    @Override
    public String name() {
        return "frontend-karaffe-typecheck";
    }

    @Override
    public String description() {
        return "Check the type of AST";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Tree compilationUnit = context.getCompilationUnit();
        return TaskResult.SUCCESSFUL;
    }

}
