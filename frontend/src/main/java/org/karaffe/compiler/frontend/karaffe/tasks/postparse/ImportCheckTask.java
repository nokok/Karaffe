package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;

import java.util.concurrent.atomic.AtomicBoolean;

public class ImportCheckTask extends AbstractReadOnlyTask implements NoDescriptionTask {
    @Override
    public String name() {
        return "import-check";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Tree compilationUnit = context.getCompilationUnit();
        AtomicBoolean hasError = new AtomicBoolean(false);
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitSimpleImportDef(Def tree, Void o) {
                Tree name = tree.getName();
                try {
                    Class.forName(tree.getName().toString());
                } catch (ClassNotFoundException e) {
                    Errors.symbolNotFound(tree.getPos(), name.toString());
                    hasError.set(true);
                }
                return tree;
            }
        }, null);
        if (hasError.get()) {
            return TaskResult.FAILED;
        }
        return TaskResult.SUCCESS;
    }
}
