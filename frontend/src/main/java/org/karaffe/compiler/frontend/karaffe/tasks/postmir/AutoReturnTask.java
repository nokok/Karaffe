package org.karaffe.compiler.frontend.karaffe.tasks.postmir;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.ir.Block;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.ir.Instruction;
import org.karaffe.compiler.base.ir.Instructions;
import org.karaffe.compiler.base.ir.Operand;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.block.BeginBlock;
import org.karaffe.compiler.base.mir.instructions.block.EndBlock;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.IRTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.TaskResult;

import java.util.List;
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
    public TaskResult run(DeprecatedInstructions instructions, CompilerContext context) {
        BeginBlock beginBlock = null;
        for (DeprecatedInstruction instruction : instructions) {
            if (instruction.getInstType() == InstructionType.BEGINBLOCK) {
                beginBlock = (BeginBlock) instruction;
            }
            if (instruction.getInstType() == InstructionType.ENDBLOCK) {
                EndBlock endBlock = (EndBlock) instruction;
                int index = instructions.indexOf(instruction);
                DeprecatedInstruction lastInstruction = instructions.get(index - 1);
                if (lastInstruction.getInstType() != InstructionType.RETURN) {

                }
            }
        }
        //TODO
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public TaskResult run(IR ir, CompilerContext context) {
        ir.blockStream().forEach(b -> {
            Instructions instructions = b.getInstructions();
            Instruction instruction = instructions.get(instructions.size() - 1);
            Operand operand = instruction.getOperand();
            if (operand != null/*Return*/) {
                instructions.add(null /*Return*/);
            }
            b.setInstructions(instructions);
        });
        ir.functionStream().forEach(f -> {
            List<Block> blocks = f.getBlocks();
            Block lastBlock = blocks.get(blocks.size() - 1);
            f.setBlocks(blocks);
        });
        return TaskResult.SUCCESSFUL;
    }
}
