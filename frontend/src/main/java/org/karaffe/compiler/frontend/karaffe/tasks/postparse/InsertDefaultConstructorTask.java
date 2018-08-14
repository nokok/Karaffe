package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.def.Defs;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.expr.Exprs;
import org.karaffe.compiler.base.tree.stmt.Stmts;
import org.karaffe.compiler.base.tree.term.Terms;

public class InsertDefaultConstructorTask extends AbstractTask implements ASTTask {
    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        compilationUnit.accept(new DefaultVisitor<Void>() {

            boolean hasCtor;

            @Override
            public Tree visitClassDef(ClassDef tree, Void aVoid) {
                hasCtor = false;
                super.visitClassDef(tree, aVoid);
                if (!hasCtor) {
                    Def ctor = Defs.methodDef(
                            Position.noPos(),
                            tree,
                            "<init>",
                            Terms.primitiveVoid(Position.noPos()),
                            Exprs.tuple()
                    );
                    ctor.setParent(tree);
                    ctor.addChild(Exprs.apply(
                            Position.noPos(),
                            Exprs.superInstance(ctor.getPos()),
                            Terms.varName(Position.noPos(), "<init>"),
                            Terms.emptyTree()));
                    ctor.addChild(Stmts.returnStmt(Position.noPos()));
                    ctor.getChildren().forEach(t -> t.setParent(ctor));
                    tree.addChild(ctor);
                }
                return tree;
            }

            @Override
            public Tree visitMethodDef(MethodDef tree, Void aVoid) {
                super.visitMethodDef(tree, aVoid);
                if (tree.getName().asSimpleName().equals("<init>")) {
                    hasCtor = true;
                }
                return tree;
            }
        }, null);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "frontend-karaffe-postparse-defaultctor";
    }

    @Override
    public String description() {
        return "Insert default constructor";
    }

    @Override
    public boolean changed() {
        return false;
    }
}
