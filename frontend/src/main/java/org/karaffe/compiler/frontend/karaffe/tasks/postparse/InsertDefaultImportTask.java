package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;

public class InsertDefaultImportTask extends AbstractTask implements NoDescriptionTask {
    @Override
    public String name() {
        return "insert-default-import";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        Tree compilationUnit = context.getCompilationUnit();

        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitCompileUnit(Tree.CompilationUnit tree, Void aVoid) {
                super.visitCompileUnit(tree, aVoid);
                tree.addTopLevel(Defs.importDef(tree, "java.lang.Integer"));
                return tree;
            }
        }, null);

        return TaskResult.SUCCESS;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
