package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;

public class GenHIRTask extends AbstractTask implements ASTTask {
    @Override
    public String name() {
        return "frontend-karaffe-genmir";
    }

    @Override
    public String description() {
        return "Generate High-level Intermeidate Representation(HIR)";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
