package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.frontend.karaffe.visitor.CreateASTVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenASTTask extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenASTTask.class);

    @Override
    public String name() {
        return "frontend-karaffe-ast";
    }

    @Override
    public String description() {
        return "generate AST from ParserContext";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        CreateASTVisitor visitor = new CreateASTVisitor(context);
        context.contextStream()
                .peek(c -> LOGGER.debug(c.toString()))
                .map(c -> c.accept(visitor))
                .reduce((l, r) -> r)
                .ifPresent(context::setCompilationUnit);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
