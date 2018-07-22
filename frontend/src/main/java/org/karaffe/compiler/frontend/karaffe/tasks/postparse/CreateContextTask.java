package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.CompilationUnitTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.util.Scope;
import org.karaffe.compiler.frontend.karaffe.visitor.CreateScopeVisitor;

public class CreateContextTask extends AbstractTask implements ReadOnlyTask, CompilationUnitTask {

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
        compilationUnit.accept(new DefaultVisitor<Void>() {
            @Override
            public Tree visitPackageDef(PackageDef tree, Void aVoid) {
                context.onPackageFilePair(tree.getPos().getSourceName(), super.visitPackageDef(tree, aVoid).getName().toString());
                return tree;
            }

            @Override
            public Tree visitSimpleImportDef(SimpleImport tree, Void aVoid) {
                context.onFileImportDef(tree.getPos(), (Def) super.visitSimpleImportDef(tree, aVoid));
                return tree;
            }

            @Override
            public Tree visitOnDemandImportDef(OnDemandImport tree, Void aVoid) {
                context.onFileImportDef(tree.getPos(), (Def) super.visitOnDemandImportDef(tree, aVoid));
                return tree;
            }
        }, null);

        Scope globalScope = new Scope();
        CreateScopeVisitor visitor = new CreateScopeVisitor();
        compilationUnit.accept(visitor, globalScope);

        return TaskResult.SUCCESSFUL;
    }
}
