package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.Element;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.task.ASTTask;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;

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
        MIRVisitor visitor = new MIRVisitor();
        compilationUnit.accept(visitor, context);
        context.setIR(visitor.getIR());
        return TaskResult.SUCCESSFUL;
    }

    static class MIRVisitor extends TreeVisitorAdapter<Element, CompilerContext> {

        private IR ir = IR.newIR();

        public IR getIR() {
            return this.ir;
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
