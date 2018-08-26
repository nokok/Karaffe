package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.DefaultModule;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.ir.Module;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.ClassDef;

public class GenMIRTask extends AbstractTask implements ASTTask {

    @Override
    public String name() {
        return "frontend-karaffe-mir";
    }

    @Override
    public String description() {
        return "Generate MIR";
    }

    @Override
    public TaskResult run(Tree compilationUnit, CompilerContext context) {
        IR ir = compilationUnit.accept(new MIRVisitor(), context);
        context.setIR(ir);
        return TaskResult.SUCCESSFUL;
    }

    static class MIRVisitor extends TreeVisitorAdapter<IR, CompilerContext> {
        @Override
        public IR visitCompileUnit(Tree.CompilationUnit tree, CompilerContext context) {
            IR ir = IR.newIR();
            context.setIR(ir);
            tree.acceptChildren(this, context);
            return ir;
        }

        @Override
        public IR visitClassDef(ClassDef def, CompilerContext context) {
            IR ir = context.getIR();
            Module module = new DefaultModule(def.getName().toString());
            ir.add(module);
            return ir;
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.getCompilationUnit() != null;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
