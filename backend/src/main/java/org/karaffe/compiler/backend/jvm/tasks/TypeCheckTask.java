package org.karaffe.compiler.backend.jvm.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.util.InstructionList;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.BackendTask;
import org.karaffe.compiler.base.task.NoDescriptionTask;
import org.karaffe.compiler.base.task.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeCheckTask extends AbstractTask implements BackendTask, NoDescriptionTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeCheckTask.class);

    private final List<String> defaultImportPackages = new ArrayList<>(Arrays.asList(
            Object.class.getPackage().getName()
    ));

    @Override
    public String name() {
        return "jvm-backend-typechecker";
    }

    @Override
    public boolean changed() {
        return false;
    }

    @Override
    public TaskResult run(Instructions instructions, CompilerContext context) {
        Instructions dest = new InstructionList();
        dest.addAll(instructions);
        for (int index = 0; index < instructions.size(); index++) {
            Instruction instruction = instructions.get(index);
            if (instruction.getInstType() == InstructionType.CONST) {

            }
        }
        context.setInstructions(dest);
        return TaskResult.SUCCESS;
    }
}