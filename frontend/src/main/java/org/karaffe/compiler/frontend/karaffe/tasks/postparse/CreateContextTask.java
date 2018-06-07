package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;

public class CreateContextTask extends AbstractReadOnlyTask implements NoDescriptionTask {

    @Override
    public String name() {
        return "create-context";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Tree compilationUnit = context.getCompilationUnit();
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitPackageDef(Def tree, Void aVoid) {
                super.visitPackageDef(tree, aVoid);
                context.onPackageFilePair(tree.getPos().getSourceName(), tree.getName().toString());
                return tree;
            }

            @Override
            public Tree visitSimpleImportDef(Def tree, Void aVoid) {
                super.visitSimpleImportDef(tree, aVoid);
                context.onFileImportDef(tree.getPos().getSourceName(), tree.getName().toString());
                return tree;
            }
        }, null);

        return TaskResult.SUCCESS;
    }
}
