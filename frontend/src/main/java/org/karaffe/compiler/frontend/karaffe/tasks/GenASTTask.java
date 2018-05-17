package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.frontend.karaffe.visitor.NewASTVisitor;

public class GenASTTask extends AbstractTask {
    @Override
    public String name() {
        return "gen ast";
    }

    @Override
    public String description() {
        return "generate AST from ParserContext";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        NewASTVisitor visitor = new NewASTVisitor();
        context.contextStream()
                .map(c -> c.accept(visitor))
                .reduce((l, r) -> r)
                .ifPresent(context::setCompilationUnit);
        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
