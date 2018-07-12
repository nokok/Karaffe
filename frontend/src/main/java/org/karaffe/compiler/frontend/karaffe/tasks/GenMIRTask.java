package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.mir.Instruction;
import org.karaffe.compiler.mir.InstructionType;
import org.karaffe.compiler.mir.Instructions;
import org.karaffe.compiler.mir.block.Begin;
import org.karaffe.compiler.mir.block.End;
import org.karaffe.compiler.mir.constant.Const;
import org.karaffe.compiler.mir.invoke.Invoke;
import org.karaffe.compiler.mir.io.Store;
import org.karaffe.compiler.mir.util.InstructionList;
import org.karaffe.compiler.mir.util.Label;
import org.karaffe.compiler.mir.variable.ValDef;

import java.util.List;
import java.util.Objects;

public class GenMIRTask extends AbstractTask implements NoDescriptionTask {

    private Instructions instructions;

    @Override
    public String name() {
        return "gen-mir";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        this.instructions = new InstructionList();
        Label rootLabel = Label.createRootLabel();
        this.instructions.add(new Begin(InstructionType.PROGRAM, rootLabel));

        Tree compilationUnit = context.getCompilationUnit();
        Instructions generated = compilationUnit.accept(new TreeVisitor(), null);
        this.instructions.addAll(generated);

        this.instructions.add(new End(rootLabel));
        return TaskResult.SUCCESS;
    }

    private static class TreeVisitor extends TreeVisitorAdapter<Instructions, Label> {

        private int blockCount = 0;

        @Override
        public Instructions visitCompileUnit(Tree.CompilationUnit tree, Label parent) {
            Instructions instructions = new InstructionList();
            Label rootLabel = Label.createRootLabel();
            List<Instructions> accepted = tree.acceptChildren(this, rootLabel);
            accepted.stream().filter(Objects::nonNull).forEach(instructions::addAll);
            return instructions;
        }

        @Override
        public Instructions visitLetDef(LetDef simpleDef, Label parent) {
            Instructions instructions = new InstructionList();
            Label valLabel = new Label(parent, simpleDef.getName().asSimpleName());
            ValDef valDef = new ValDef(valLabel, simpleDef.getTypeName().asFullName());
            valDef.setPosition(simpleDef.getPos());
            instructions.add(valDef);
            Instructions accept = simpleDef.getBody().get(0).accept(this, valLabel);
            instructions.addAll(accept);
            Store store = new Store(valLabel);
            store.setPosition(simpleDef.getName().getPos());
            instructions.add(store);
            return instructions;
        }

        @Override
        public Instructions visitBlock(Block block, Label label) {
            Instructions instructions = new InstructionList();
            Label blockLabel = new Label(label, String.valueOf(blockCount++));
            instructions.add(new Begin(InstructionType.BLOCK, blockLabel));
            block.acceptChildren(this, blockLabel).forEach(instructions::addAll);
            instructions.add(new End(blockLabel));
            return instructions;
        }

        @Override
        public Instructions visitApply(Apply apply, Label label) {
            Instructions instructions = new InstructionList();
            List<Instructions> acceptChildren = apply.acceptChildren(this, label);
            acceptChildren.forEach(instructions::addAll);
            Instruction targetObject = instructions.get(0);
            Invoke invoke = new Invoke(apply.getName().toString());
            invoke.setPosition(apply.getPos());
            instructions.add(invoke);
            return instructions;
        }

        @Override
        public Instructions visitAtom(Atom atom, Label label) {
            Instructions instructions = new InstructionList();
            Const aConst = new Const(atom.getValue());
            aConst.setPosition(atom.getPos());
            instructions.add(aConst);
            return instructions;
        }
    }

    public Instructions getInstructions() {
        if (this.instructions == null) {
            throw new IllegalStateException();
        }
        return this.instructions;
    }

    @Override
    public boolean changed() {
        return true;
    }
}
