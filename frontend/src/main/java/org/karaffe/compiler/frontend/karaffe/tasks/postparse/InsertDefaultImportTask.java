package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractTask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InsertDefaultImportTask extends AbstractTask implements NoDescriptionTask {

    private static final Set<String> defaultImportClasses;

    static {
        // load classes
        defaultImportClasses = new HashSet<>(Arrays.asList(
                java.lang.String.class.getCanonicalName(),
                java.lang.Integer.class.getCanonicalName()
        ));
    }

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
                for (String defaultImport : defaultImportClasses) {
                    tree.addFirst(Defs.importDef(tree, defaultImport));
                    context.onFileImportDef(tree.getPos().getSourceName(), defaultImport);
                }
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
