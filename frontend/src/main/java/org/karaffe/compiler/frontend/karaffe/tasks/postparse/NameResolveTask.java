package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;

public class NameResolveTask extends AbstractTask {
    @Override
    public String name() {
        return "name-resolver";
    }

    @Override
    public String description() {
        return "resolve simple names";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Tree compilationUnit = context.getCompilationUnit();
        compilationUnit.accept(new DefaultVisitor<Void>() {
            // TODO
        }, null);
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
