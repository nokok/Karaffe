package org.karaffe.compiler.transform.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.CompilationUnitTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.frontend.karaffe.subject.ClassDefSubject;
import org.karaffe.compiler.frontend.karaffe.subject.MethodDefSubject;

public class TypeCheckTask extends AbstractTask implements CompilationUnitTask {

    @Override
    public String name() {
        return "transform-typecheck";
    }

    @Override
    public String description() {
        return "Type check of AST and MIR";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        ClassDefSubject<Void> classDefSubject = ClassDefSubject.getSubject();
        classDefSubject.onClassDef(classDef -> {

        });
        MethodDefSubject<Void> subject = MethodDefSubject.getSubject();
        subject.onMethodDef(methodDef -> {
            Path returnType = methodDef.getTypeName();
            Tree parameters = methodDef.getParameters();
            Tree methodBody = methodDef.getMethodBody();
        });
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
