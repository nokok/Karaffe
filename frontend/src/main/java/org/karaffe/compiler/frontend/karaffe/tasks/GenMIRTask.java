package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.mir.InstructionType;
import org.karaffe.compiler.mir.Instructions;
import org.karaffe.compiler.mir.block.Begin;
import org.karaffe.compiler.mir.block.End;
import org.karaffe.compiler.mir.util.InstructionList;
import org.karaffe.compiler.mir.util.Label;

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
        this.instructions.add(new End(rootLabel));
        return TaskResult.SUCCESS;
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
