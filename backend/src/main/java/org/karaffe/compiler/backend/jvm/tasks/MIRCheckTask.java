package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.MIRTask;
import org.karaffe.compiler.base.task.ReadOnlyTask;
import org.karaffe.compiler.base.task.TaskResult;

public class MIRCheckTask extends AbstractTask implements MIRTask, ReadOnlyTask {
    @Override
    public TaskResult run(DeprecatedInstructions instructions, CompilerContext context) {
        boolean hasGarbage = instructions.stream().map(DeprecatedInstruction::getInstType).anyMatch(t -> t == InstructionType.IMPORT);
        if (hasGarbage) {
            return TaskResult.FAILED;
        }
        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "backend-jvm-mircheck";
    }

    @Override
    public String description() {
        return "MIR validation";
    }

}
