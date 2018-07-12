package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitorAdapter;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.mir.InstructionType;
import org.karaffe.compiler.mir.Instructions;
import org.karaffe.compiler.mir.block.Begin;
import org.karaffe.compiler.mir.block.End;
import org.karaffe.compiler.mir.constant.Const;
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
            instructions.add(new Store(valLabel));
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
