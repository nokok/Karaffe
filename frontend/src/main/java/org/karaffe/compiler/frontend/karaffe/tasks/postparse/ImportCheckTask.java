package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class ImportCheckTask extends AbstractTask implements ReadOnlyTask, NoDescriptionTask, ASTTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportCheckTask.class);

    @Override
    public String name() {
        return "frontend-karaffe-postparse-importcheck";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        AtomicBoolean hasError = new AtomicBoolean(false);
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visit(SimpleImport tree, Void o) {
                Tree name = tree.getName();
                try {
                    Class.forName(tree.getName().toString());
                } catch (ClassNotFoundException e) {
                    LOGGER.error("Import name not found. : {}", tree.getName());
                    context.addReport(Errors.symbolNotFound(tree.getPos(), name.toString()));
                    hasError.set(true);
                }
                return tree;
            }
        }, null);
        if (hasError.get()) {
            LOGGER.error("hasError");
            return TaskResult.FAILED;
        }
        return TaskResult.SUCCESSFUL;
    }
}
