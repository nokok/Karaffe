package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.util.Scope;
import org.karaffe.compiler.frontend.karaffe.visitor.CreateScopeVisitor;

public class CreateContextTask extends AbstractTask implements ReadOnlyTask, ASTTask {

    @Override
    public String name() {
        return "frontend-karaffe-postparse-context";
    }

    @Override
    public String description() {
        return "Create context from AST";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        Scope globalScope = new Scope();
        CreateScopeVisitor visitor = new CreateScopeVisitor();
        compilationUnit.accept(visitor, globalScope);

        return TaskResult.SUCCESSFUL;
    }
}
