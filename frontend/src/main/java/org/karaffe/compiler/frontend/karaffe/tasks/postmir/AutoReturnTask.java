package org.karaffe.compiler.frontend.karaffe.tasks.postmir;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.instructions.IR;
import org.karaffe.compiler.base.mir.instructions.Instruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.block.BeginBlock;
import org.karaffe.compiler.base.mir.instructions.block.EndBlock;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.IRTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.util.concurrent.atomic.AtomicBoolean;

public class AutoReturnTask extends AbstractTask implements MIRTask, IRTask {

    private AtomicBoolean hasChanged = new AtomicBoolean(false);

    @Override
    public String name() {
        return "frontend-karaffe-postmir-autoreturn";
    }

    @Override
    public String description() {
        return "Insert a return statement at the end of method instructions";
    }

    @Override
    public boolean changed() {
        return this.hasChanged.get();
    }

    @Override
    public TaskResult run(IR instructions, CompilerContext context) {
        BeginBlock beginBlock = null;
        for (Instruction instruction : instructions) {
            if (instruction.getInstType() == InstructionType.BEGINBLOCK) {
                beginBlock = (BeginBlock) instruction;
            }
            if (instruction.getInstType() == InstructionType.ENDBLOCK) {
                EndBlock endBlock = (EndBlock) instruction;
                int index = instructions.indexOf(instruction);
                Instruction lastInstruction = instructions.get(index - 1);
                if (lastInstruction.getInstType() != InstructionType.RETURN) {

                }
            }
        }
        //TODO
        return TaskResult.SUCCESSFUL;
    }

}
