package org.karaffe.compiler.frontend.karaffe.tasks.postparse;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.CompilationUnitTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.stmt.Stmts;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.frontend.karaffe.subject.MethodDefSubject;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutoReturnTask extends AbstractTask implements CompilationUnitTask {

    private AtomicBoolean hasChanged = new AtomicBoolean(false);

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        MethodDefSubject<Object> subject = MethodDefSubject.getSubject();
        subject.onMethodDef(def -> {
            List<Tree> children = def.getChildren();
            Tree lastTree = children.get(children.size() - 1);
            if (lastTree.getKind() == TreeKind.RETURN) {
                return;
            }
            hasChanged.set(true);
            Path returnTypeName = def.getTypeName();
            if (returnTypeName.asSimpleName().equals("void")) {
                def.addChild(Stmts.returnStmt(lastTree.getPos()));
            } else {
                def.addChild(Stmts.returnStmt(lastTree.getPos(), lastTree));
            }
        });
        compilationUnit.accept(subject.asVisitor(), null);
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "frontend-karaffe-autoreturn";
    }

    @Override
    public String description() {
        return "Insert a return statement at the end of method instructions";
    }

    @Override
    public boolean changed() {
        return this.hasChanged.get();
    }
}
